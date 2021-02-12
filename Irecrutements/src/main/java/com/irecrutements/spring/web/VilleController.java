package com.irecrutements.spring.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.irecrutements.spring.business.VilleManager;
import com.irecrutements.spring.dao.jpa.VilleRepository;
import com.irecrutements.spring.models.Ville;

@Controller
public class VilleController {
	@Autowired
	private VilleManager villeManager;

	/*@RequestMapping(value="/villes", method=RequestMethod.GET)
	public String index(Model model){
		List<Ville> villes = villeManager.selectAll();
		
		model.addAttribute("listVilles", villes);
		return "Villes";
	}*/
	
//	@Autowired
//	private VilleRepository villeRepository;

	/*@RequestMapping(value="/villes", method=RequestMethod.GET)
	public String villes(Model model, 
			@RequestParam(name="motCle", defaultValue="") String motCle,
			@RequestParam(name="page", defaultValue="0") int p, 
			@RequestParam(name="size", defaultValue="4")int s){
//		Page<Ville> pageVilles = villeRepository.findAll(new PageRequest(p, s));
//		Page<Ville> pageVilles = villeRepository.chercher("%"+motCle + "%", new PageRequest(p, s));
//		List<Ville> pageVilles = villeManager.selectAll().subList(p, s);
		List<Ville> villes = villeManager.selectAll();
		int nb = (p+1)*s;
		if(nb>villes.size())nb=villes.size();
		List<Ville> pageVilles = villes.subList(p*s, nb);
		
//		model.addAttribute("listVilles", pageVilles.getContent());
//		int[] pages = new int[pageVilles.getTotalPages()];
		model.addAttribute("listVilles", pageVilles);
		int nblist = villes.size()/s;
		if(villes.size()%s != 0)nblist++;
		int[] pages = new int[nblist];
		
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", motCle);
		
		return "Villes";
	}*/
	@RequestMapping(value="/villes", method=RequestMethod.GET)
	public String index(Model model, 
			@RequestParam(name="page", defaultValue="0") int p, 
			@RequestParam(name="size", defaultValue="4")int s,
			@RequestParam(name="nom", defaultValue="") String nom,
			@RequestParam(name="pays", defaultValue="") String pays){
//		Page<Ville> pageVilles = villeRepository.findAll(new PageRequest(p, s));
//		Page<Ville> pageVilles = villeRepository.chercher("%"+motCle + "%", new PageRequest(p, s));
//		List<Ville> pageVilles = villeManager.selectAll().subList(p, s);
		List<Ville> villes = villeManager.selectByNomPays(nom, pays);
		int nb = (p+1)*s;
		if(nb>villes.size())nb=villes.size();
		List<Ville> pageVilles = villes.subList(p*s, nb);
		
//		model.addAttribute("listVilles", pageVilles.getContent());
//		int[] pages = new int[pageVilles.getTotalPages()];
		model.addAttribute("listVilles", pageVilles);
		int nblist = villes.size()/s;
		if(villes.size()%s != 0)nblist++;
		int[] pages = new int[nblist];
		
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", p);
		model.addAttribute("nom", nom);
		model.addAttribute("pays", pays);
		
		return "Villes";
	}
	
	/*@RequestMapping(value="/villesParPays", method=RequestMethod.GET)
	public String index(Model model, 
			@RequestParam(name="motCle", defaultValue="") String motCle,
			@RequestParam(name="page", defaultValue="0") int p, 
			@RequestParam(name="size", defaultValue="4")int s){
//		Page<Ville> pageVilles = villeRepository.findAll(new PageRequest(p, s));
//		Page<Ville> pageVilles = villeRepository.chercher("%"+motCle + "%", new PageRequest(p, s));
//		List<Ville> pageVilles = villeManager.selectAll().subList(p, s);
		List<Ville> villes = villeManager.selectAll();
		int nb = (p+1)*s;
		if(nb>villes.size())nb=villes.size();
		List<Ville> pageVilles = villes.subList(p*s, nb);
		
//		model.addAttribute("listVilles", pageVilles.getContent());
//		int[] pages = new int[pageVilles.getTotalPages()];
		model.addAttribute("listVilles", pageVilles);
		int nblist = villes.size()/s;
		if(villes.size()%s != 0)nblist++;
		int[] pages = new int[nblist];
		
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", motCle);
		
		return "Villes";
	}*/
	
//	@RequestMapping(value="/chercher")
//	public String chercher(Model model, String motCle,
//			@RequestParam(name="page", defaultValue="0") int p, 
//			@RequestParam(name="size", defaultValue="4")int s){
//		Page<Ville> pageVilles = villeRepository.chercher("%" + motCle + "%", new PageRequest(p, s));
//		
//		
//		return "villes";
//	}
}
