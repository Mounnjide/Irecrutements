package com.irecrutements.spring.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.irecrutements.spring.business.CandidatureManager;
import com.irecrutements.spring.business.OffreManager;
import com.irecrutements.spring.models.Candidature;
import com.irecrutements.spring.models.Offre;

@Controller
public class HomeController {
	@Autowired
	CandidatureManager candidatureManager;
	
	@Autowired
	OffreManager offreManager;
	
	
	@RequestMapping(value="/")
	public String index(Model model){
		List<Candidature> candidatures = candidatureManager.selectAllByOrder().subList(0, 6);
		model.addAttribute("candidatures", candidatures);
		
		List<Offre> offres = offreManager.selectAll().subList(0, 6);
		model.addAttribute("offres", offres);
		
		return "index";
	}
//	@RequestMapping(value="/Template1")
//	public String template1(Model model){
//
////		return "index";
//		return "Template1";
//	}
//	@RequestMapping(value="/tutorialComponents")
//	public String tutorialComponents(Model model){
//
////		return "index";
//		return "TutorialComponents";
//	}
	
}
