package com.irecrutements.spring.business;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irecrutements.spring.dao.UtilisateurDao;
import com.irecrutements.spring.models.Document;
import com.irecrutements.spring.models.Utilisateur;
@Service
public class UtilisateurManagerDefault implements UtilisateurManager{
	@Autowired
	private UtilisateurDao dao;
	public UtilisateurManagerDefault() {
		// TODO Auto-generated constructor stub
	}
	
	public UtilisateurManagerDefault(UtilisateurDao dao) {
		super();
		this.dao = dao;
	}

	public UtilisateurDao getDao() {
		return dao;
	}

	public void setDao(UtilisateurDao dao) {
		this.dao = dao;
	}

	public int add(Utilisateur c) {
		return dao.insert(c);
	}

	public int update(Utilisateur c) {
		return dao.update(c);
	}

	public int delete(Utilisateur c) {
		return dao.delete(c);
	}

	public List<Utilisateur> selectAll() {
		return dao.selectAll();
	}

	public Utilisateur searchById(int id) {
		return dao.selectById(id);
	}

	public List<Utilisateur> searchByNomPrenomUtilisateur(String nomPrenomUtilisateur) {
		return dao.selectByNomPrenomUtilisateur(nomPrenomUtilisateur);
	}

	public List<Utilisateur> searchByEmailUtilisateur(String emailUtilisateur) {
		return dao.selectByEmailUtilisateur(emailUtilisateur);
	}
	
	public Utilisateur findByEmailUtilisateur(String emailUtilisateur) {
		return dao.findByEmailUtilisateur(emailUtilisateur);
	}

	public List<Utilisateur> searchByPwdUtilisateur(String pwdUtilisateur) {
		return dao.selectByPwdUtilisateur(pwdUtilisateur);
	}

	public List<Utilisateur> searchByCiviliteUtilisateur(String civiliteUtilisateur) {
		return dao.selectByCiviliteUtilisateur(civiliteUtilisateur);
	}

	public List<Utilisateur> searchByAdresse(String adresse) {
		return dao.selectByAdresse(adresse);
	}

	public List<Utilisateur> searchByTelephone(String telephone) {
		return dao.selectByTelephone(telephone);
	}

	public List<Utilisateur> searchByDateInscription(Date dateInscription) {
		return dao.selectByDateInscription(dateInscription);
	}

	public List<Utilisateur> searchByPhoto(Document photo) {
		return dao.selectByPhoto(photo);
	}

}
