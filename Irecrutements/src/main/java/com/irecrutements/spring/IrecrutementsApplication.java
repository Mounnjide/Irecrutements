package com.irecrutements.spring;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.irecrutements.spring.business.CandidatManager;
import com.irecrutements.spring.business.CandidatureManager;
import com.irecrutements.spring.business.DocumentManager;
import com.irecrutements.spring.business.OffreManager;
import com.irecrutements.spring.business.UtilisateurManager;
import com.irecrutements.spring.dao.jpa.RoleRepository;
import com.irecrutements.spring.models.Candidat;
import com.irecrutements.spring.models.Candidature;
import com.irecrutements.spring.models.Document;
import com.irecrutements.spring.models.Offre;
import com.irecrutements.spring.models.Utilisateur;

@SpringBootApplication
public class IrecrutementsApplication implements CommandLineRunner{
//	@Autowired
//	private RoleRepository roleRepository;
	
	@Autowired
	private DocumentManager documentManager;
	
//	private static SessionFactory sessionFactory = null;
//	private static Session session = null;
	
	public static void main(String[] args) {
		SpringApplication.run(IrecrutementsApplication.class, args);
	}
	
	
	public void run(String... arg0) throws Exception {
		
		System.out.println("avant");
		org.springframework.security.authentication.encoding.PasswordEncoder pwencoder = new Md5PasswordEncoder();
		String hashed = pwencoder.encodePassword("123456", null);
//		
//		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String hashed = passwordEncoder.encode("123456");
		System.out.println(hashed);
//		
//		roleRepository.save(new Role("ADMIN"));
//		roleRepository.save(new Role("USER"));
		
//		export();
		
//		importerImage();
//		sauvegarderImage();
		 
//		test();
//		test2();
		
		System.out.println("ok");
		   
	}
	public void importerImage(){
		Document d = documentManager.selectById(2);
		byte[] bAvatar = d.getContenu();
		
		try{
            FileOutputStream fos = new FileOutputStream("E:\\Stage 4eme annee\\photos\\imageCopie.jpg");
            fos.write(bAvatar);
            fos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
		
		
	}
	public void sauvegarderImage(){
		File file = new File("E:\\Spring Stage\\Irecrutements\\src\\main\\resources\\static\\assets\\img\\default-avatar.png");
		byte[] bFile = new byte[(int) file.length()];
		 try {
		     FileInputStream fileInputStream = new FileInputStream(file);
		     //convert file into array of bytes
		     fileInputStream.read(bFile);
		     fileInputStream.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
		Document d = new Document();
		d.setTitreDocument("DEFAULT_AVATAR");
		d.setDateChargement(new Date());
		d.setTypeDocument("image");
		d.setContenu(bFile);
		
		documentManager.add(d);
	}
	
	
	
	@Autowired
	private OffreManager offreManager;
	@Autowired 
	private CandidatureManager candidatureManager;
	@Autowired 
	private CandidatManager candidatManager;
	
	public void test(){
		Offre offre = new Offre();
		offre.setIntituleOffre("intituler offre");
		
		Candidature candidature = new Candidature();
		candidature.setIntituleCandidature("intituler candidature");
		candidature.setTypeCandidature("type");
		
		Candidat candidat = new Candidat();
		candidat.setNomUtilisateur("Eddah bani");
		Document doc = new Document();
		doc.setDateChargement(new Date());
		candidat.setPhoto(doc);
		candidat.setCiviliteUtilisateur("me");
		candidat.setEmailUtilisateur("ana@ana");
		candidat.setPwdUtilisateur("zzz");
		
		candidatManager.add(candidat);
		
		candidat.addOffreInteressantes(offre);
		offre.addCandidature(candidat.getIdUtilisateur(), candidature);
		
		candidatureManager.add(candidature);
		offreManager.add(offre);
		candidatManager.update(candidat);
		
		
	}
	public void test2(){
		Candidat candidat = candidatManager.searchById(1);
		System.out.println(candidat);
		for (Offre offre : candidat.getOffresInteressantes()) {
//			Candidature candidature = new Candidature();
//			candidature.setIntituleCandidature("intituler 2");
//			candidature.setTypeCandidature("2");
//			
//			offre.addCandidature(2, candidature);
			
			
//			candidatureManager.add(candidature);
//			offreManager.update(offre);
//			candidatManager.update(candidat);
//			
			System.out.println(offre);
			
			
			System.out.println(offre.getCandidatures().get(2));
		}
	}
}
