package com.irecrutements.spring.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.irecrutements.spring.business.CandidatManager;
import com.irecrutements.spring.business.DocumentManager;
import com.irecrutements.spring.business.UtilisateurManager;
import com.irecrutements.spring.models.Candidat;
import com.irecrutements.spring.models.Candidature;
import com.irecrutements.spring.models.Document;

@Controller
public class DocumentController {
	@Autowired
	private DocumentManager documentManager;
	
	
	@RequestMapping(value="/downloadDoc", method=RequestMethod.GET)
	public String consulterCandidature(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model, @RequestParam("id") int idDocument, @RequestParam("titre") String titreDocument) throws IOException {
		
		System.out.println(idDocument + "+" + titreDocument);
		
		Document document = documentManager.selectById(idDocument);
		
		if(document != null && document.getTitreDocument().equals(titreDocument)){
			httpServletResponse.setContentLength(document.getContenu().length);
			httpServletResponse.setHeader("Content-Disposition","attachment; filename=\"" + document.getTitreDocument() +"\"");				
			
			FileCopyUtils.copy(document.getContenu(), httpServletResponse.getOutputStream());
		}
		
		return "redirect:"+httpServletRequest.getHeader("Referer");
	}
}
