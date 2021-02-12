package com.irecrutements.spring.business;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irecrutements.spring.dao.CandidatDao;
import com.irecrutements.spring.models.Candidat;
import com.irecrutements.spring.models.Candidature;
import com.irecrutements.spring.models.Utilisateur;
@Service
public class CandidatManagerDefault implements CandidatManager{
	@Autowired
	private CandidatDao dao;
	public CandidatManagerDefault() {
		// TODO Auto-generated constructor stub
	}
	
	public CandidatManagerDefault(CandidatDao dao) {
		this.dao = dao;
	}

	public CandidatDao getDao() {
		return dao;
	}

	public void setDao(CandidatDao dao) {
		this.dao = dao;
	}

	public int add(Candidat c) {
		return dao.insert(c);
	}

	public int update(Candidat c) {
		return dao.update(c);
	}

	public int delete(Candidat c) {
		return dao.delete(c);
	}

	public List<Candidat> selectAll() {
		return dao.selectAll();
	}

	public Candidat searchById(int id) {
		return dao.selectById(id);
	}
	
	public Candidat searchByEmailUtilisateur(String emailUtilisateur) {
		return dao.findByEmailUtilisateur(emailUtilisateur);
	}
	
	public List<Candidat> searchByDateNaissance(Date date) {
		return dao.selectByDateNaissance(date);
	}

	public List<Candidat> searchByEtablissementEtude(String etablissementEtude) {
		return dao.selectByEtablissementEtude(etablissementEtude);
	}

	public List<Candidat> searchByNiveauEtude(String niveauEtude) {
		return dao.selectByNiveauEtude(niveauEtude);
	}

	public Candidat searchByCandidature(Candidature candidature) {
		return dao.selectByCandidature(candidature);
	}

	public Utilisateur emailExiste(String email) {
		return dao.emailExiste(email);
	}
}
