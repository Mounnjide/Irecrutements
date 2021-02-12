package com.irecrutements.spring.web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.irecrutements.spring.business.DocumentManager;
import com.irecrutements.spring.business.UtilisateurManager;
import com.irecrutements.spring.models.Candidat;
import com.irecrutements.spring.models.Document;
import com.irecrutements.spring.models.Employeur;
import com.irecrutements.spring.models.Utilisateur;

@Controller
public class UtilisateurController {
	@Autowired
	private UtilisateurManager utilisateurManager;

	@Autowired
    DocumentManager documentManager;
	
	@RequestMapping(value = "/imageProfil", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(HttpServletRequest httpServletRequest) throws IOException {
    	
		Utilisateur utilisateur = getAuthentifiedEmployeur(httpServletRequest);
		if(utilisateur.getPhoto() == null){
			System.out.println("img empty");
			Document photo = documentManager.searchByTitreDocument("DEFAULT_AVATAR").get(0);
			utilisateur.setPhoto(photo);
		}
		
        byte[] imageContent = utilisateur.getPhoto().getContenu();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
    }
	

	@RequestMapping(value="/saveProfilImg", method=RequestMethod.POST)
	public String saveProfilImg(@RequestParam("profilImg") MultipartFile file, RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest,  Model model
			,@RequestParam(name="imgEmpty", defaultValue="") String imgEmpty){
		
		if (file.isEmpty()) {
			if(httpServletRequest.getHeader("Referer").contains("imgEmpty")) {
				return "redirect:"+httpServletRequest.getHeader("Referer");
			}
			imgEmpty="Please select an image";
    		return "redirect:"+httpServletRequest.getHeader("Referer") + "?imgEmpty=" + imgEmpty;
        }
		Utilisateur utilisateur = getAuthentifiedEmployeur(httpServletRequest);
		
//		model.addAttribute("candidat", candidat);
		
		try {
			if(utilisateur.getPhoto() != null && ! utilisateur.getPhoto().getTitreDocument().equals("DEFAULT_AVATAR")) {
				documentManager.delete(utilisateur.getPhoto());
			}
			
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Document photo = new Document();
            photo.setDateChargement(new Date());
            photo.setContenu(bytes);
            photo.setTypeDocument("image");
            photo.setTitreDocument(file.getOriginalFilename());
            
            utilisateur.setPhoto(photo);
            
            if(utilisateurManager.update(utilisateur) != 0){
            	imgEmpty="La taille de l'image est tres grande";
            	return "redirect:"+httpServletRequest.getHeader("Referer") + "?imgEmpty=" + imgEmpty;
            }
            
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
            System.out.println("You successfully uploaded '" + file.getOriginalFilename() + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		if(httpServletRequest.getHeader("Referer").contains("imgEmpty")) {
			String url = httpServletRequest.getHeader("Referer").substring(0, httpServletRequest.getHeader("Referer").indexOf("?"));
			return "redirect:"+url;
		}
		return "redirect:"+httpServletRequest.getHeader("Referer");
	}
	@RequestMapping(value="/editProfil", method=RequestMethod.GET)
	public String editProfil(HttpServletRequest httpServletRequest, Model model,@RequestParam(name="imgEmpty", defaultValue="") String imgEmpty){
		
		Utilisateur utilisateur = getAuthentifiedEmployeur(httpServletRequest);
		
//		System.out.println(candidat.getNomUtilisateur() + candidat.getEtablissementEtude());
		utilisateur.setPwdUtilisateur("");
		
		
		
		if(! imgEmpty.equals(""))model.addAttribute("imgEmpty", imgEmpty);
		
		if(utilisateur instanceof Employeur){
			model.addAttribute("employeur", utilisateur);
			return "EditProfilEmployeur";
		}
		else if(utilisateur instanceof Candidat){
			model.addAttribute("candidat", utilisateur);
			return "EditProfilCandidat";
		}
		
		return "redirect:"+httpServletRequest.getHeader("Referer");
	}
	
	private Utilisateur getAuthentifiedEmployeur(HttpServletRequest httpServletRequest){
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext = (SecurityContext)httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username = securityContext.getAuthentication().getName();
		
		return utilisateurManager.findByEmailUtilisateur(username);
	}
}
