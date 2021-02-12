package com.irecrutements.spring.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.irecrutements.spring.business.CandidatureManager;
import com.irecrutements.spring.business.DocumentManager;
import com.irecrutements.spring.business.EmployeurManager;
import com.irecrutements.spring.business.OffreManager;
import com.irecrutements.spring.business.UtilisateurManager;
import com.irecrutements.spring.models.Candidat;
import com.irecrutements.spring.models.Candidature;
import com.irecrutements.spring.models.Document;
import com.irecrutements.spring.models.Employeur;
import com.irecrutements.spring.models.Offre;
import com.irecrutements.spring.models.Utilisateur;

@Controller
public class EmployeurController {
	@Autowired
	private EmployeurManager employerManager;
	
	@Autowired
    private DocumentManager documentManager;
    
	@Autowired
	private UtilisateurManager utilisateurManager;

	@Autowired 
	private CandidatureManager candidatureManager; //////////
	
	
	@RequestMapping(value="/inscriptionEmployeur", method=RequestMethod.GET)
	public String inscriptionCandidat(Model model){
		model.addAttribute("employeur", new Employeur());
		
		return "inscriptionEmployeur";
	}
	@RequestMapping(value="/saveEmployeur", method=RequestMethod.POST)
	public String saveEmployeur(Model model,@Valid Employeur employeur,
			BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			
			return "inscriptionEmployeur";
		}
		if(employerManager.emailExiste(employeur.getEmailUtilisateur()) != null){
			String emailExiste = "Votre adresse e-mail existe déjà, merci d'en choisir une autre.";
			model.addAttribute("emailExiste", emailExiste);
			return "inscriptionEmployeur";
		}
		
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		employeur.setPwdUtilisateur(passwordEncoder.encode(employeur.getPwdUtilisateur()));
		
		Document photo = documentManager.searchByTitreDocument("DEFAULT_AVATAR").get(0);
		employeur.setPhoto(photo);
		employeur.setActive(true);

		employerManager.add(employeur);
		if(employeur.getIdUtilisateur()<=0)return "inscriptionEmployeur";
		
		System.out.println("Inscription validé");
		System.out.println(employeur);
		return "redirect:/login";
	}

	@RequestMapping(value="/employeurProfil")
	public String employeurProfil(HttpServletRequest httpServletRequest,  Model model,@RequestParam(name="imgEmpty", defaultValue="") String imgEmpty){
		Employeur employeur = getAuthentifiedEmployeur(httpServletRequest);
		
		System.out.println(employeur.getNomUtilisateur() + employeur.getInfoEntreprise());
		
		model.addAttribute("employeur", employeur);
		
		if(! imgEmpty.equals(""))model.addAttribute("imgEmpty", imgEmpty);
		
		Map<Utilisateur, Candidature> demandes = new HashMap<>();
		for (Offre offre : employeur.getOffres()) {
			Set<Integer> keys = offre.getCandidatures().keySet();
			for (Integer integer : keys) {
				Utilisateur utilisateur = utilisateurManager.searchById(integer);
				demandes.put(utilisateur, offre.getCandidatures().get(integer));
			}
		}
		model.addAttribute("demandes", demandes);
		
		return "ProfilEmployeur";
	}
	@RequestMapping(value="/updateEmployeur", method=RequestMethod.POST)
	public String doEditProfil(Model model,@Valid Employeur employeur,
			BindingResult bindingResult, HttpServletRequest httpServletRequest){
		if(bindingResult.hasErrors()){
			
			return "EditProfilEmployeur";
		}
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		employeur.setPwdUtilisateur(passwordEncoder.encode(employeur.getPwdUtilisateur()));
		
		
		Employeur oldEmployeur = getAuthentifiedEmployeur(httpServletRequest);
		
		System.out.println(oldEmployeur);
		
		oldEmployeur.setNomUtilisateur(employeur.getNomUtilisateur());
		oldEmployeur.setPrenomUtilisateur(employeur.getPrenomUtilisateur());
		oldEmployeur.setEmailUtilisateur(employeur.getEmailUtilisateur());
		oldEmployeur.setPwdUtilisateur(employeur.getPwdUtilisateur());
		oldEmployeur.setCiviliteUtilisateur(employeur.getCiviliteUtilisateur());
		oldEmployeur.setAdresse(employeur.getAdresse());
		oldEmployeur.setTelephone(employeur.getTelephone());
		oldEmployeur.setInfoEntreprise(employeur.getInfoEntreprise());
		oldEmployeur.setSiteWeb(employeur.getSiteWeb());
		oldEmployeur.setFacebookEntreprise(employeur.getFacebookEntreprise());
		oldEmployeur.setLinkedinEntreprise(employeur.getLinkedinEntreprise());
		System.out.println(oldEmployeur);

		if(employerManager.update(oldEmployeur) != 0){
        	String imgEmpty="Erreur d'enregistrement des modification";
        	return "redirect:"+httpServletRequest.getHeader("Referer") + "?imgEmpty=" + imgEmpty;
        }
		
		model.addAttribute("employeur", oldEmployeur);
		
		return "ProfilEmployeur";
	}

	@RequestMapping(value="/candidatureAuFavoris")
	public String offreAuFavoris(HttpServletRequest httpServletRequest, Model model,@RequestParam("id") int idCandidature){

		Employeur employeur = getAuthentifiedEmployeur(httpServletRequest);
		
		Candidature candidature = candidatureManager.searchById(idCandidature);
		
		if(candidature != null){
			employeur.addCandidatureInteressantes(candidature);
			
			if(employerManager.update(employeur) != 0){
	        	String imgEmpty="Erreur d'enregistrement de la candidature";
	        	return "redirect:"+httpServletRequest.getHeader("Referer") + "?imgEmpty=" + imgEmpty;
	        }
			
			return "redirect:/";
		}
		
		
		return "redirect:"+httpServletRequest.getHeader("Referer");
	}

	@RequestMapping(value="/supprimerCandidatureInteressante")
	public String supprimerOffreInteressante(HttpServletRequest httpServletRequest, Model model,@RequestParam("id") int idCandidature){

		Employeur employeur = getAuthentifiedEmployeur(httpServletRequest);
		
		Candidature candidature = candidatureManager.searchById(idCandidature);
		if(candidature != null) employeur.deleteCandidatureInteressantes(candidature);
		
		if(employerManager.update(employeur) != 0){
        	String imgEmpty="Erreur de suppression de l'offre";
        	return "redirect:"+httpServletRequest.getHeader("Referer") + "?imgEmpty=" + imgEmpty;
        }
		
		return "redirect:"+httpServletRequest.getHeader("Referer");
	}
	private Employeur getAuthentifiedEmployeur(HttpServletRequest httpServletRequest){
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext = (SecurityContext)httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username = securityContext.getAuthentication().getName();
		
		return employerManager.searchByEmailUtilisateur(username);
	}
}
