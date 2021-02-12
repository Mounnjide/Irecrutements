package com.irecrutements.spring.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.irecrutements.spring.business.DomaineManager;
import com.irecrutements.spring.business.EmployeurManager;
import com.irecrutements.spring.business.OffreManager;
import com.irecrutements.spring.business.VilleManager;
import com.irecrutements.spring.models.Candidat;
import com.irecrutements.spring.models.Candidature;
import com.irecrutements.spring.models.Document;
import com.irecrutements.spring.models.Domaine;
import com.irecrutements.spring.models.Employeur;
import com.irecrutements.spring.models.Offre;
import com.irecrutements.spring.models.Ville;

@Controller
public class OffreController {
	@Autowired
	private OffreManager offreManager;
	
	@Autowired
	private EmployeurManager employeurManager;
	
	@Autowired
	private DomaineManager domaineManager;
	
	@Autowired
	private VilleManager villeManager;
	
	@RequestMapping(value="/ajouterOffre", method=RequestMethod.GET)
	public String ajouterOffre(Model model){
		model.addAttribute("offre", new Offre());
		
		List<Domaine> domaines = domaineManager.selectAll();
		model.addAttribute("domaines", domaines);
		
		List<Ville> villes = villeManager.selectAll();
		model.addAttribute("villes", villes);
		
		return "ajouterOffre";
	}

	@RequestMapping(value="/previsualiserOffre", method=RequestMethod.POST)
	public String saveOffre(HttpServletRequest httpServletRequest, Model model,
			@Valid Offre offre, BindingResult bindingResult,
			@RequestParam(value = "domaines", required = false) List<Integer> domaines,
			@RequestParam(value = "villeOffre", required=false) int idVille){
		
		
		if(domaines != null){
			for (int idDomaine : domaines) {
				Domaine domaine = domaineManager.searchById(idDomaine);
				if(domaine != null) offre.addDomaine(domaine);
			}
		}
		
		
		Ville ville = villeManager.searchById(idVille);
		if(ville != null) offre.setVilleOffre(ville);
		
		
		Employeur employeur = getAuthentifiedEmployeur(httpServletRequest);
		
		employeur.addOffre(offre);
		
		if(employeurManager.update(employeur) == -1) return "redirect:"+httpServletRequest.getHeader("Referer");
		
		model.addAttribute("offre", offre);

		
		return "ConsulterOffre";
	}

	@RequestMapping(value="/consulterOffre")
	public String consulterOffre(HttpServletRequest httpServletRequest, Model model,@RequestParam("id") int idOffre){

		Employeur employeur = getAuthentifiedEmployeur(httpServletRequest);
		
		for (Offre offre : employeur.getOffres()) {
			if(offre.getIdOffre() == idOffre) {
				model.addAttribute("offre", offre);
				
				return "ConsulterOffre";
			}
		}
		
		return "redirect:"+httpServletRequest.getHeader("Referer");
	}

	@RequestMapping(value="/EditOffre")
	public String EditOffre(HttpServletRequest httpServletRequest, Model model,@RequestParam("id") int idOffre){
		Employeur employeur = getAuthentifiedEmployeur(httpServletRequest);
		
		List<Domaine> domaines = domaineManager.selectAll();
		model.addAttribute("domaines", domaines);
		
		List<Ville> villes = villeManager.selectAll();
		model.addAttribute("villes", villes);
		
		for (Offre offre : employeur.getOffres()) {
			if(offre.getIdOffre() == idOffre) {
				model.addAttribute("offre", offre);
				
				return "ModifierOffre";
			}
		}
		
		return "redirect:"+httpServletRequest.getHeader("Referer");
	}
	

	@RequestMapping(value="/updateOffre", method=RequestMethod.POST)
	public String updateOffre(HttpServletRequest httpServletRequest, Model model,
			@RequestParam("id") int idOffre,
			@Valid Offre offre, BindingResult bindingResult,
			@RequestParam(value = "domaines", required=false) List<Integer> domaines,
			@RequestParam(value = "villeOffre", required=false) int idVille ){
		
		Employeur employeur = getAuthentifiedEmployeur(httpServletRequest);
		
		int pos = -1;
		for (int i = 0; i < employeur.getOffres().size(); i++) {
			if(employeur.getOffres().get(i).getIdOffre() == idOffre)pos = i;
		}
		
		if(pos == -1) return "redirect:"+httpServletRequest.getHeader("Referer");
		
		employeur.getOffres().get(pos).setTypeOffre(offre.getTypeOffre());
		employeur.getOffres().get(pos).setIntituleOffre(offre.getIntituleOffre());
		employeur.getOffres().get(pos).setDescriptionOffre(offre.getDescriptionOffre());
		employeur.getOffres().get(pos).setDateDebutOffre(offre.getDateDebutOffre());
		employeur.getOffres().get(pos).setDateFinOffre(offre.getDateFinOffre());
		employeur.getOffres().get(pos).setDureeOffre(offre.getDureeOffre());
		employeur.getOffres().get(pos).setExperienceRequise(offre.getExperienceRequise());
		
		employeur.getOffres().get(pos).getDomaines().clear();
		System.out.println(employeur.getOffres().get(pos).getDateDebutOffre());

		if(domaines != null){
			for (int idDomaine : domaines) {
				Domaine domaine = domaineManager.searchById(idDomaine);
				if(domaine != null) employeur.getOffres().get(pos).addDomaine(domaine);
			}
		}
		
		
		Ville ville = villeManager.searchById(idVille);
		if(ville != null) employeur.getOffres().get(pos).setVilleOffre(ville);
		
		
		if(employeurManager.update(employeur) == -1) return "redirect:"+httpServletRequest.getHeader("Referer");
		
		
		model.addAttribute("offre", employeur.getOffres().get(pos));
		
		return "ConsulterOffre";
	}

	@RequestMapping(value="/SupprimerOffre")
	public String SupprimerOffre(HttpServletRequest httpServletRequest, Model model,
			@RequestParam("id") int idOffre){
		Employeur employeur = getAuthentifiedEmployeur(httpServletRequest);
		
		
		for (Offre offre : employeur.getOffres()) {
			if(offre.getIdOffre() == idOffre) {
				model.addAttribute("offre", offre);
				
				return "SupprimerOffre";
			}
		}
		return "redirect:"+httpServletRequest.getHeader("Referer");
	}

	@RequestMapping(value="/deleteOffre")
	public String deleteOffre(HttpServletRequest httpServletRequest, Model model, @RequestParam("id") int idOffre){
		Employeur employeur = getAuthentifiedEmployeur(httpServletRequest);
		
		int pos = -1;
		for (int i = 0; i < employeur.getOffres().size(); i++) {
			if(employeur.getOffres().get(i).getIdOffre() == idOffre)pos = i;
		}
		
		if(pos == -1) return "redirect:"+httpServletRequest.getHeader("Referer");
		
		
		if(offreManager.delete(employeur.getOffres().get(pos)) == -1) return "redirect:"+httpServletRequest.getHeader("Referer");
		
		employeur.getOffres().remove(pos);
		
//		if(candidatManager.update(candidat) == -1) return "redirect:"+httpServletRequest.getHeader("Referer");
		if(employeurManager.update(employeur) == -1) return "redirect:"+httpServletRequest.getHeader("Referer");
		
		model.addAttribute("employeur", employeur);
		
		return "ProfilEmployeur";
	}
	@RequestMapping(value="/voirOffre")
	public String voirOffre(HttpServletRequest httpServletRequest, Model model,@RequestParam(value = "id") int idOffre){
		Offre offre = offreManager.searchById(idOffre);
		
		if(offre != null) {
			model.addAttribute("offre", offre);
			return "VoirOffre";
		}
		
		return "Offre introuvable 404"; // TODO
	}

	
	@RequestMapping(value="/rechercherOffres", method=RequestMethod.POST)
	public String rechercherOffres(Model model, 
			@RequestParam(name="page", defaultValue="0")int page,
			@RequestParam(name="size", defaultValue="6") int size,
			@RequestParam(name="typeOffre", defaultValue="N'importe quelle catégorie")String typeOffre,
			@RequestParam(name="villeOffre", defaultValue="0")int villeOffre,
			@RequestParam(name="domaine", defaultValue="0")int domaine,
			@RequestParam(name="motCle", defaultValue="")String motCle
			){
		
		List<Domaine> domaines = domaineManager.selectAll();
		model.addAttribute("domaines", domaines);
		
		List<Ville> villes = villeManager.selectAll();
		model.addAttribute("villes", villes);
		
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("typeOffre", typeOffre);
		model.addAttribute("villeOffre", villeOffre);
		model.addAttribute("domaine", domaine);
		model.addAttribute("motCle", motCle);
		
		
		try {
			
			Domaine dom = domaineManager.searchById(domaine);
			Ville ville = villeManager.searchById(villeOffre);
			if(typeOffre.equals("N'importe quelle catégorie")) typeOffre = "";
			System.out.println(typeOffre + " " + " " + ville + " " + dom + " " + motCle);
			
			List<Offre> list = offreManager.selectByFiltre(motCle, typeOffre, ville, dom);
			
			List<Offre> offres = new ArrayList<>();
			for (Offre offre : list) {
				if(! existeInList(offres, offre)){
					offres.add(offre);
				}
			}
			
			
			int nb = (page+1)*size;
			if(nb>offres.size())nb=offres.size();
			List<Offre> pageOffres = offres.subList(page*size, nb);
			
			for (Offre offre : offres) {
				System.out.println(offre.getIdOffre());
			}
			
			
			model.addAttribute("offres", pageOffres);
			int nblist = offres.size()/size;
			if(offres.size()%size != 0)nblist++;
			int[] pages = new int[nblist];
			
			model.addAttribute("pages", pages);
			model.addAttribute("pageCourante", page);
			
		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		
		
		return "Offres";
	}

	@RequestMapping(value="/rechercherOffres", method=RequestMethod.GET)
	public String rechercherOffres(Model model, 
			@RequestParam(name="page", defaultValue="0")int page,
			@RequestParam(name="size", defaultValue="6") int size){
		List<Domaine> domaines = domaineManager.selectAll();
		model.addAttribute("domaines", domaines);
		
		List<Ville> villes = villeManager.selectAll();
		model.addAttribute("villes", villes);
		
		model.addAttribute("size", size);
		String typeOffre = "";
		model.addAttribute("typeOffre", typeOffre);
		int villeOffre = 0;
		model.addAttribute("villeOffre", villeOffre);
		int domaine = 0;
		model.addAttribute("domaine", domaine);
		String motCle = "";
		model.addAttribute("motCle", motCle);
		
		try {
			List<Offre> offres = offreManager.selectAllByOrdre();
			int nb = (page+1)*size;
			if(nb>offres.size())nb=offres.size();
			List<Offre> pageOffres = offres.subList(page*size, nb);
			
			
			model.addAttribute("offres", pageOffres);
			int nblist = offres.size()/size;
			if(offres.size()%size != 0)nblist++;
			int[] pages = new int[nblist];
			
			model.addAttribute("pages", pages);
			model.addAttribute("pageCourante", page);
			
		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		
		
		return "Offres";
	}

	public boolean existeInList(List<Offre> offres, Offre offre){
		for (Offre off : offres) {
			if(off.getIdOffre() == offre.getIdOffre()) return true;
		}
		return false;
	}
	
	
	private Employeur getAuthentifiedEmployeur(HttpServletRequest httpServletRequest){
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext = (SecurityContext)httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username = securityContext.getAuthentication().getName();
		
		return employeurManager.searchByEmailUtilisateur(username);
	}
}
