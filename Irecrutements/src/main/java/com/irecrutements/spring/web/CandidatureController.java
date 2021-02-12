package com.irecrutements.spring.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Vector;

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

import com.irecrutements.spring.business.CandidatManager;
import com.irecrutements.spring.business.CandidatureManager;
import com.irecrutements.spring.business.DomaineManager;
import com.irecrutements.spring.business.OffreManager;
import com.irecrutements.spring.business.VilleManager;
import com.irecrutements.spring.models.Candidat;
import com.irecrutements.spring.models.Candidature;
import com.irecrutements.spring.models.Document;
import com.irecrutements.spring.models.Domaine;
import com.irecrutements.spring.models.Offre;
import com.irecrutements.spring.models.Ville;

@Controller
public class CandidatureController {
	@Autowired
	private CandidatureManager candidatureManager;

	@Autowired 
	private OffreManager offreManager;
	
	@Autowired
	private DomaineManager domaineManager;
	
	@Autowired
	private VilleManager villeManager;
	

	@Autowired
	private CandidatManager candidatManager;
	
	
	@RequestMapping(value="/ajouterCandidature", method=RequestMethod.GET)
	public String ajouterCandidature(Model model){
		model.addAttribute("candidature", new Candidature());
		
		List<Domaine> domaines = domaineManager.selectAll();
		model.addAttribute("domaines", domaines);
		
		List<Ville> villes = villeManager.selectAll();
		model.addAttribute("villes", villes);
		
		return "ajouterCandidature";
	}
	
	

	@RequestMapping(value="/previsualiserCandidature",headers=("content-type=multipart/*"), method=RequestMethod.POST)
	public String saveCandidature(HttpServletRequest httpServletRequest, Model model,
			@Valid Candidature candidature, BindingResult bindingResult,
			@RequestParam(value = "domaines", required=false ) List<Integer> domaines,
			@RequestParam(value = "villeCandidature", required=false ) int idVille,
			@RequestParam("cvFile") MultipartFile file1, RedirectAttributes redirectAttributes,
			@RequestParam("motivationFile") MultipartFile file2
			){
		
		candidature.setEtatCandidature("Nouveau");
		
		if(domaines != null){
			for (int idDomaine : domaines) {
				Domaine domaine = domaineManager.searchById(idDomaine);
				if(domaine != null) candidature.addDomaine(domaine);
			}
		}
		
		
		Ville ville = villeManager.searchById(idVille);
		if(ville != null) candidature.setVilleCandidature(ville);
		
		if(! file1.isEmpty()){
			try{
				System.out.println("cv not empty");
				byte[] bytes = file1.getBytes();
	            Document cv = new Document();
	            cv.setDateChargement(new Date());
	            cv.setContenu(bytes);
	            cv.setTypeDocument("document");
	            cv.setTitreDocument(file1.getOriginalFilename());
	            
	            candidature.setCv(cv);
			}catch (IOException e) {
	            e.printStackTrace();
	        }
		}		
		if(! file2.isEmpty()){
			try{
				System.out.println("motivation not empty");
				byte[] bytes = file2.getBytes();
	            Document motivation = new Document();
	            motivation.setDateChargement(new Date());
	            motivation.setContenu(bytes);
	            motivation.setTypeDocument("document");
	            motivation.setTitreDocument(file2.getOriginalFilename());
	            
	            candidature.setMotivation(motivation);
			}catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		Candidat candidat = getAuthentifiedCandidat(httpServletRequest);
		
		candidat.addCandidature(candidature);
		
//		candidatManager.update(candidat);
		if(candidatManager.update(candidat) == -1) return "redirect:"+httpServletRequest.getHeader("Referer");
		
		model.addAttribute("candidature", candidature);

		
		return "ConsulterCandidature";
	}


	@RequestMapping(value="/consulterCandidature")
	public String consulterCandidature(HttpServletRequest httpServletRequest, Model model,@RequestParam("id") int idCandidature){
		Candidat candidat = getAuthentifiedCandidat(httpServletRequest);

		for (Candidature candidature : candidat.getCandidatures()) {
			if(candidature.getIdCandidature() == idCandidature) {
				model.addAttribute("candidature", candidature);
				
				return "ConsulterCandidature";
			}
		}
		for (Offre offre : candidat.getOffresInteressantes()) {
			if(offre.getCandidatures().get(candidat.getIdUtilisateur()).getIdCandidature() == idCandidature) {
				model.addAttribute("candidature", offre.getCandidatures().get(candidat.getIdUtilisateur()));
				
				return "ConsulterCandidature";
			}
		}
		
		return "redirect:"+httpServletRequest.getHeader("Referer");
	}

	
	@RequestMapping(value="/EditCandidature")
	public String editCandidature(HttpServletRequest httpServletRequest, Model model,@RequestParam("id") int idCandidature){
		Candidat candidat = getAuthentifiedCandidat(httpServletRequest);
		
		List<Domaine> domaines = domaineManager.selectAll();
		model.addAttribute("domaines", domaines);
		
		List<Ville> villes = villeManager.selectAll();
		model.addAttribute("villes", villes);
		
		for (Candidature candidature : candidat.getCandidatures()) {
			if(candidature.getIdCandidature() == idCandidature) {
				model.addAttribute("candidature", candidature);
				
				return "ModifierCandidature";
			}
		}
		
		return "redirect:"+httpServletRequest.getHeader("Referer");
	}
	
	@RequestMapping(value="/updateCandidature",headers=("content-type=multipart/*"), method=RequestMethod.POST)
	public String updateCandidature(HttpServletRequest httpServletRequest, Model model,
			@RequestParam("id") int idCandidature,
			@Valid Candidature candidature, BindingResult bindingResult,
			@RequestParam(value = "domaines", required=false ) List<Integer> domaines,
			@RequestParam(value = "villeCandidature" , required=false) int idVille,
			@RequestParam("cvFile") MultipartFile file1, RedirectAttributes redirectAttributes,
			@RequestParam("motivationFile") MultipartFile file2
			){
		
		System.out.println(idCandidature);
		Candidat candidat = getAuthentifiedCandidat(httpServletRequest);
		
		int pos = -1;
		for (int i = 0; i < candidat.getCandidatures().size(); i++) {
			if(candidat.getCandidatures().get(i).getIdCandidature() == idCandidature)pos = i;
		}
		
		if(pos == -1) return "redirect:"+httpServletRequest.getHeader("Referer");
		
		candidat.getCandidatures().get(pos).setTypeCandidature(candidature.getTypeCandidature());
		candidat.getCandidatures().get(pos).setIntituleCandidature(candidature.getIntituleCandidature());
		candidat.getCandidatures().get(pos).setExperienceCandidature(candidature.getExperienceCandidature());
		
		
		candidat.getCandidatures().get(pos).getDomaines().clear();

		if(domaines != null){
			for (int idDomaine : domaines) {
				Domaine domaine = domaineManager.searchById(idDomaine);
				if(domaine != null) candidat.getCandidatures().get(pos).addDomaine(domaine);
			}
		}
		
		
		Ville ville = villeManager.searchById(idVille);
		if(ville != null) candidat.getCandidatures().get(pos).setVilleCandidature(ville);
		
		if(! file1.isEmpty()){
			try{
				System.out.println("cv not empty");
				byte[] bytes = file1.getBytes();
	            Document cv = new Document();
	            cv.setDateChargement(new Date());
	            cv.setContenu(bytes);
	            cv.setTypeDocument("document");
	            cv.setTitreDocument(file1.getOriginalFilename());
	            
	            candidat.getCandidatures().get(pos).setCv(cv);
			}catch (IOException e) {
	            e.printStackTrace();
	        }
		}		
		if(! file2.isEmpty()){
			try{
				System.out.println("motivation not empty");
				byte[] bytes = file2.getBytes();
	            Document motivation = new Document();
	            motivation.setDateChargement(new Date());
	            motivation.setContenu(bytes);
	            motivation.setTypeDocument("document");
	            motivation.setTitreDocument(file2.getOriginalFilename());
	            
	            candidat.getCandidatures().get(pos).setMotivation(motivation);
			}catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		System.out.println(candidat.getCandidatures().get(pos));
		
		
		if(candidatManager.update(candidat) == -1) return "redirect:"+httpServletRequest.getHeader("Referer");
				
		model.addAttribute("candidature", candidat.getCandidatures().get(pos));

		
		return "ConsulterCandidature";
	}

	@RequestMapping(value="/SupprimerCandidature")
	public String supprimerCandidature(HttpServletRequest httpServletRequest, Model model,
			@RequestParam("id") int idCandidature){
		Candidat candidat = getAuthentifiedCandidat(httpServletRequest);
		
		
		for (Candidature candidature : candidat.getCandidatures()) {
			if(candidature.getIdCandidature() == idCandidature) {
				model.addAttribute("candidature", candidature);
				
				return "SupprimerCandidature";
			}
		}
		for (Offre offre : candidat.getOffresInteressantes()) {
			if(offre.getCandidatures().get(candidat.getIdUtilisateur()).getIdCandidature() == idCandidature) {
				model.addAttribute("candidature", offre.getCandidatures().get(candidat.getIdUtilisateur()));
				model.addAttribute("Edit", "Edit");
				return "SupprimerCandidature";
			}
		}
		
		return "redirect:"+httpServletRequest.getHeader("Referer");
	}
	

	@RequestMapping(value="/etatCandidature")
	public String etatCandidature(HttpServletRequest httpServletRequest, Model model,@RequestParam("id") int idCandidature){
		
		Candidature candidature = candidatureManager.searchById(idCandidature);
		if(candidature != null){
			model.addAttribute("candidature", candidature);
			
			return "EtatCandidature";
		}
		
		return "redirect:"+httpServletRequest.getHeader("Referer");
	}
	
	@RequestMapping(value="/updateEtatCandidature", method=RequestMethod.POST)
	public String updateEtatCandidature(HttpServletRequest httpServletRequest, Model model,
			@RequestParam("id") int idCandidature,
			@RequestParam(value = "etatCandidature") String etatCandidature){
		
		Candidature candidature = candidatureManager.searchById(idCandidature);
		if(candidature != null){
			candidature.setEtatCandidature(etatCandidature);
			candidatureManager.update(candidature);
			
			
			return "redirect:/employeurProfil";
		}
		return "redirect:"+httpServletRequest.getHeader("Referer");
	}
	
	@RequestMapping(value="/deleteCandidature")
	public String deleteCandidature(HttpServletRequest httpServletRequest, Model model, @RequestParam("id") int idCandidature){
		Candidat candidat = getAuthentifiedCandidat(httpServletRequest);
		
		int pos = -1;
		for (int i = 0; i < candidat.getCandidatures().size(); i++) {
			if(candidat.getCandidatures().get(i).getIdCandidature() == idCandidature)pos = i;
		}
		
		if(pos == -1){
			for (Offre offre : candidat.getOffresInteressantes()) {
				if(offre.getCandidatures().size()>0 && offre.getCandidatures().get(candidat.getIdUtilisateur()).getIdCandidature() == idCandidature){
					if(candidatureManager.delete(offre.getCandidatures().get(candidat.getIdUtilisateur())) == -1) return "redirect:"+httpServletRequest.getHeader("Referer");
					offre.getCandidatures().remove(candidat.getIdUtilisateur());
					
					if(offreManager.update(offre) == -1) return "redirect:"+httpServletRequest.getHeader("Referer");
				}
			}
			
			if(candidatManager.update(candidat) == -1) return "redirect:"+httpServletRequest.getHeader("Referer");
			model.addAttribute("candidat", candidat);
			
			return "ProfilCandidat";
		}
		
		
		if(candidatureManager.delete(candidat.getCandidatures().get(pos)) == -1) return "redirect:"+httpServletRequest.getHeader("Referer");
		
		candidat.getCandidatures().remove(pos);
		
		if(candidatManager.update(candidat) == -1) return "redirect:"+httpServletRequest.getHeader("Referer");

		model.addAttribute("candidat", candidat);
		
		return "ProfilCandidat";
	}
	
	@RequestMapping(value="/voirCandidature")
	public String voirCandidature(HttpServletRequest httpServletRequest, Model model,@RequestParam(value = "id", required=true) int idCandidature){
		Candidature candidature = candidatureManager.searchById(idCandidature);
		
		if(candidature != null) {
			model.addAttribute("candidature", candidature);
			
			return "VoirCandidature";
		}
		
		return "Candidature introuvable 404"; // TODO
	}
	@RequestMapping(value="/postulerCandidature",headers=("content-type=multipart/*"), method=RequestMethod.POST)
	public String postulerCandidature(HttpServletRequest httpServletRequest, Model model,@RequestParam("id") int idOffre,
			@RequestParam("cvFile") MultipartFile file1, RedirectAttributes redirectAttributes,
			@RequestParam("motivationFile") MultipartFile file2
			){
		Offre offre = offreManager.searchById(idOffre);
		
		Candidature candidature = new Candidature();
		candidature.setIntituleCandidature(offre.getIntituleOffre());
		candidature.setTypeCandidature(offre.getTypeOffre());
		candidature.setEtatCandidature("Nouveau");
		candidature.setExperienceCandidature(offre.getExperienceRequise());
//		candidature.setDomaines(offre.getDomaines());
		for (Domaine domaine : offre.getDomaines()) {
			candidature.addDomaine(domaine);
		}
		candidature.setVilleCandidature(offre.getVilleOffre());
		
		
		if(! file1.isEmpty()){
			try{
				System.out.println("cv not empty");
				byte[] bytes = file1.getBytes();
	            Document cv = new Document();
	            cv.setDateChargement(new Date());
	            cv.setContenu(bytes);
	            cv.setTypeDocument("document");
	            cv.setTitreDocument(file1.getOriginalFilename());
	            
	            candidature.setCv(cv);
			}catch (IOException e) {
	            e.printStackTrace();
	        }
		}		
		if(! file2.isEmpty()){
			try{
				System.out.println("motivation not empty");
				byte[] bytes = file2.getBytes();
	            Document motivation = new Document();
	            motivation.setDateChargement(new Date());
	            motivation.setContenu(bytes);
	            motivation.setTypeDocument("document");
	            motivation.setTitreDocument(file2.getOriginalFilename());
	            
	            candidature.setMotivation(motivation);
			}catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		
		Candidat candidat = getAuthentifiedCandidat(httpServletRequest);
		
		offre.addCandidature(candidat.getIdUtilisateur(), candidature);
		
		candidatureManager.add(candidature);
		
		if(offreManager.update(offre) == -1) return "redirect:"+httpServletRequest.getHeader("Referer");
		
		
		
		candidat.addOffreInteressantes(offre);
		
		if(candidatManager.update(candidat) == -1) return "redirect:"+httpServletRequest.getHeader("Referer");
		
		model.addAttribute("candidature", candidature);

		
		return "ConsulterCandidature";
	}

	@RequestMapping(value="/rechercherCandidatures", method=RequestMethod.POST)
	public String rechercherCandidatures(Model model, 
			@RequestParam(name="page", defaultValue="0")int page,
			@RequestParam(name="size", defaultValue="6") int size,
			@RequestParam(name="typeCandidature", defaultValue="N'importe quelle catégorie")String typeCandidature,
			@RequestParam(name="villeCandidature", defaultValue="0")int villeCandidature,
			@RequestParam(name="domaine", defaultValue="0")int domaine,
			@RequestParam(name="motCle", defaultValue="")String motCle
			){
		
		List<Domaine> domaines = domaineManager.selectAll();
		model.addAttribute("domaines", domaines);
		
		List<Ville> villes = villeManager.selectAll();
		model.addAttribute("villes", villes);
		
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("typeCandidature", typeCandidature);
		model.addAttribute("villeCandidature", villeCandidature);
		model.addAttribute("domaine", domaine);
		model.addAttribute("motCle", motCle);
		
		
		try {
			
			Domaine dom = domaineManager.searchById(domaine);
			Ville ville = villeManager.searchById(villeCandidature);
			if(typeCandidature.equals("N'importe quelle catégorie")) typeCandidature = "";
			System.out.println(typeCandidature + " " + " " + ville + " " + dom + " " + motCle);
			
			List<Candidature> list = candidatureManager.selectByFiltre(motCle, typeCandidature, ville, dom);
			
			List<Candidature> candidatures = new ArrayList<>();
			for (Candidature candidature : list) {
				if(! existeInList(candidatures, candidature)){
					candidatures.add(candidature);
				}
			}
			
			
			int nb = (page+1)*size;
			if(nb>candidatures.size())nb=candidatures.size();
			List<Candidature> pageCandidatures = candidatures.subList(page*size, nb);
			
			for (Candidature candidature : candidatures) {
				System.out.println(candidature.getIdCandidature());
			}
			
			
			model.addAttribute("candidatures", pageCandidatures);
			int nblist = candidatures.size()/size;
			if(candidatures.size()%size != 0)nblist++;
			int[] pages = new int[nblist];
			
			model.addAttribute("pages", pages);
			model.addAttribute("pageCourante", page);
			
		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		
		
		return "Candidatures";
	}

	@RequestMapping(value="/rechercherCandidatures", method=RequestMethod.GET)
	public String rechercherCandidatures(Model model, 
			@RequestParam(name="page", defaultValue="0")int page,
			@RequestParam(name="size", defaultValue="6") int size){
		List<Domaine> domaines = domaineManager.selectAll();
		model.addAttribute("domaines", domaines);
		
		List<Ville> villes = villeManager.selectAll();
		model.addAttribute("villes", villes);
		
		model.addAttribute("size", size);
		String typeCandidature = "";
		model.addAttribute("typeCandidature", typeCandidature);
		int villeCandidature = 0;
		model.addAttribute("villeCandidature", villeCandidature);
		int domaine = 0;
		model.addAttribute("domaine", domaine);
		String motCle = "";
		model.addAttribute("motCle", motCle);
		
		try {
			List<Candidature> candidatures = candidatureManager.selectAllByOrder();
			int nb = (page+1)*size;
			if(nb>candidatures.size())nb=candidatures.size();
			List<Candidature> pageCandidatures = candidatures.subList(page*size, nb);
			
			
			model.addAttribute("candidatures", pageCandidatures);
			int nblist = candidatures.size()/size;
			if(candidatures.size()%size != 0)nblist++;
			int[] pages = new int[nblist];
			
			model.addAttribute("pages", pages);
			model.addAttribute("pageCourante", page);
			
		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		
		
		return "Candidatures";
	}

	public boolean existeInList(List<Candidature> candidatures, Candidature candidature){
		for (Candidature cand : candidatures) {
			if(cand.getIdCandidature() == candidature.getIdCandidature()) return true;
		}
		return false;
	}
	
	
	private Candidat getAuthentifiedCandidat(HttpServletRequest httpServletRequest){
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext = (SecurityContext)httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username = securityContext.getAuthentication().getName();
		
		return candidatManager.searchByEmailUtilisateur(username);
	}
}
