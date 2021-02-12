package com.irecrutements.spring.web;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.irecrutements.spring.business.CandidatManager;
import com.irecrutements.spring.business.DocumentManager;
import com.irecrutements.spring.business.DomaineManager;
import com.irecrutements.spring.business.OffreManager;
import com.irecrutements.spring.models.Candidat;
import com.irecrutements.spring.models.Candidature;
import com.irecrutements.spring.models.Document;
import com.irecrutements.spring.models.Domaine;
import com.irecrutements.spring.models.Employeur;
import com.irecrutements.spring.models.Offre;
import com.irecrutements.spring.models.Ville;

@Controller
public class CandidatController {
	@Autowired
	private CandidatManager candidatManager;
	
	@Autowired
    private DocumentManager documentManager;
	
	@Autowired 
	private OffreManager offreManager; //////////
	
	
	@RequestMapping(value="/inscriptionCandidat", method=RequestMethod.GET)
	public String inscriptionCandidat(Model model){
		model.addAttribute("candidat", new Candidat());
		
		return "inscriptionCandidat";
	}
	@RequestMapping(value="/saveCandidat", method=RequestMethod.POST)
	public String saveCandidat(Model model,@Valid Candidat candidat,
			BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			
			return "inscriptionCandidat";
		}
		if(candidatManager.emailExiste(candidat.getEmailUtilisateur()) != null){
			String emailExiste = "Votre adresse e-mail existe déjà, merci d'en choisir une autre.";
			model.addAttribute("emailExiste", emailExiste);
			return "inscriptionCandidat";
		}
		
		System.out.println(candidat);
		
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		candidat.setPwdUtilisateur(passwordEncoder.encode(candidat.getPwdUtilisateur()));
		Document photo = documentManager.searchByTitreDocument("DEFAULT_AVATAR").get(0);
		candidat.setPhoto(photo);
		candidat.setActive(true);
		
		candidatManager.add(candidat);
		if(candidat.getIdUtilisateur()<=0)return "inscriptionCandidat";
		
		System.out.println("Inscription validé");
		return "redirect:/login";
	}

	
	@RequestMapping(value="/candidatProfil")
	public String candidatProfil(HttpServletRequest httpServletRequest,  Model model,@RequestParam(name="imgEmpty", defaultValue="") String imgEmpty){
		Candidat candidat = getAuthentifiedCandidat(httpServletRequest);
		
		System.out.println(candidat.getNomUtilisateur() + candidat.getEtablissementEtude());
		
		model.addAttribute("candidat", candidat);
		
		if(! imgEmpty.equals(""))model.addAttribute("imgEmpty", imgEmpty);
		
		
		return "ProfilCandidat";
	}
	
	@RequestMapping(value="/updateCandidat", method=RequestMethod.POST)
	public String doEditProfil(Model model,@Valid Candidat candidat,
			BindingResult bindingResult, HttpServletRequest httpServletRequest){
		if(bindingResult.hasErrors()){
			
			return "EditProfilCandidat";
		}
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		candidat.setPwdUtilisateur(passwordEncoder.encode(candidat.getPwdUtilisateur()));
		
		Candidat oldCandidat = getAuthentifiedCandidat(httpServletRequest);
		
		System.out.println(oldCandidat);
		
		oldCandidat.setNomUtilisateur(candidat.getNomUtilisateur());
		oldCandidat.setPrenomUtilisateur(candidat.getPrenomUtilisateur());
		oldCandidat.setEmailUtilisateur(candidat.getEmailUtilisateur());
		oldCandidat.setPwdUtilisateur(candidat.getPwdUtilisateur());
		oldCandidat.setCiviliteUtilisateur(candidat.getCiviliteUtilisateur());
		oldCandidat.setAdresse(candidat.getAdresse());
		oldCandidat.setTelephone(candidat.getTelephone());
		oldCandidat.setDateNaissance(candidat.getDateNaissance());
		oldCandidat.setEtablissementEtude(candidat.getAdresse());
		oldCandidat.setNiveauEtude(candidat.getNiveauEtude());
		System.out.println(oldCandidat);

		
		
		if(candidatManager.update(oldCandidat) != 0){
        	String imgEmpty="Erreur d'enregistrement des modification";
        	return "redirect:"+httpServletRequest.getHeader("Referer") + "?imgEmpty=" + imgEmpty;
        }
		
		model.addAttribute("candidat", oldCandidat);
		
		
		return "ProfilCandidat";
	}

	@RequestMapping(value="/offreAuFavoris")
	public String offreAuFavoris(HttpServletRequest httpServletRequest, Model model,@RequestParam("id") int idOffre){

		Candidat candidat = getAuthentifiedCandidat(httpServletRequest);
		
		Offre offre = offreManager.searchById(idOffre);
		if(offre != null){
			candidat.addOffreInteressantes(offre);
			
			if(candidatManager.update(candidat) != 0){
	        	String imgEmpty="Erreur d'enregistrement de l'offre";
	        	return "redirect:"+httpServletRequest.getHeader("Referer") + "?imgEmpty=" + imgEmpty;
	        }
			
			return "redirect:/";
		}
		
		
		return "redirect:"+httpServletRequest.getHeader("Referer");
	}

	@RequestMapping(value="/supprimerOffreInteressante")
	public String supprimerOffreInteressante(HttpServletRequest httpServletRequest, Model model,@RequestParam("id") int idOffre){

		Candidat candidat = getAuthentifiedCandidat(httpServletRequest);
		
		Offre offre = offreManager.searchById(idOffre);
		if(offre != null) candidat.deleteOffreInteressantes(offre);
		
		if(candidatManager.update(candidat) != 0){
        	String imgEmpty="Erreur de suppression de l'offre";
        	return "redirect:"+httpServletRequest.getHeader("Referer") + "?imgEmpty=" + imgEmpty;
        }
		
		return "redirect:"+httpServletRequest.getHeader("Referer");
	}
	@RequestMapping(value="/postuler")
	public String postuler(HttpServletRequest httpServletRequest, Model model,@RequestParam("id") int idOffre){
		
		Offre offre = offreManager.searchById(idOffre);
		Candidat candidat = getAuthentifiedCandidat(httpServletRequest);
		
		if(offre != null) {
			model.addAttribute("offre", offre);
			model.addAttribute("emailUtilisateur", candidat.getEmailUtilisateur());
			return "Postuler";
		}
		
		return "Offre est supprimee";
	}
	
	private Candidat getAuthentifiedCandidat(HttpServletRequest httpServletRequest){
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext = (SecurityContext)httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username = securityContext.getAuthentication().getName();
		
		return candidatManager.searchByEmailUtilisateur(username);
	}
	
}
