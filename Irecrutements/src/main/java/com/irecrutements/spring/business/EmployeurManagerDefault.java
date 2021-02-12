package com.irecrutements.spring.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irecrutements.spring.dao.EmployeurDao;
import com.irecrutements.spring.models.Employeur;
import com.irecrutements.spring.models.Offre;
import com.irecrutements.spring.models.Utilisateur;
@Service
public class EmployeurManagerDefault implements EmployeurManager{
	@Autowired
	private EmployeurDao dao;
	public EmployeurManagerDefault() {
		// TODO Auto-generated constructor stub
	}
	
	public EmployeurManagerDefault(EmployeurDao dao) {
		super();
		this.dao = dao;
	}

	public EmployeurDao getDao() {
		return dao;
	}

	public void setDao(EmployeurDao dao) {
		this.dao = dao;
	}

	public int add(Employeur c) {
		return dao.insert(c);
	}

	public int update(Employeur c) {
		return dao.update(c);
	}

	public int delete(Employeur c) {
		return dao.delete(c);
	}

	public List<Employeur> selectAll() {
		return dao.selectAll();
	}

	public Employeur searchById(int id) {
		return dao.selectById(id);
	}

	public Employeur searchByEmailUtilisateur(String emailUtilisateur) {
		return dao.findByEmailUtilisateur(emailUtilisateur);
	}
	
	public List<Employeur> searchByInfoEntreprise(String infoEntreprise) {
		return dao.selectByInfoEntreprise(infoEntreprise);
	}

	public List<Employeur> searchBySiteWeb(String siteWeb) {
		return dao.selectBySiteWeb(siteWeb);
	}

	public List<Employeur> searchByFacebookEntreprise(String facebookEntreprise) {
		return dao.selectByFacebookEntreprise(facebookEntreprise);
	}

	public List<Employeur> searchByLinkedinEntreprise(String linkedinEntreprise) {
		return dao.selectByLinkedinEntreprise(linkedinEntreprise);
	}

	public Employeur searchByOffre(Offre offre) {
		return dao.selectByOffre(offre);
	}
	
	public Utilisateur emailExiste(String email) {
		return dao.emailExiste(email);
	}
}
