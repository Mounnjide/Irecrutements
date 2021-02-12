package com.irecrutements.spring.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irecrutements.spring.dao.CandidatureDao;
import com.irecrutements.spring.models.Candidature;
import com.irecrutements.spring.models.Document;
import com.irecrutements.spring.models.Domaine;
import com.irecrutements.spring.models.Ville;
@Service
public class CandidatureManagerDefault implements CandidatureManager{
	@Autowired
	private CandidatureDao dao;
	public CandidatureManagerDefault() {
		// TODO Auto-generated constructor stub
	}
	
	public CandidatureManagerDefault(CandidatureDao dao) {
		super();
		this.dao = dao;
	}

	public CandidatureDao getDao() {
		return dao;
	}

	public void setDao(CandidatureDao dao) {
		this.dao = dao;
	}

	public int add(Candidature c) {
		return dao.insert(c);
	}

	public int update(Candidature c) {
		return dao.update(c);
	}

	public int delete(Candidature c) {
		return dao.delete(c);
	}

	public List<Candidature> selectAll() {
		return dao.selectAll();
	}
	
	public List<Candidature> selectAllByOrder() {
		return dao.selectAllByOrdre();
	}
	public Candidature searchById(int id) {
		return dao.selectById(id);
	}

	public List<Candidature> searchByIntituleCandidature(String intituleCandidature) {
		return dao.selectByIntituleCandidature(intituleCandidature);
	}

	public List<Candidature> searchByTypeCandidature(String typeCandidature) {
		return dao.selectByTypeCandidature(typeCandidature);
	}

	public List<Candidature> searchByEtatCandidature(String etatCandidature) {
		return dao.selectByEtatCandidature(etatCandidature);
	}

	public List<Candidature> searchByExperienceCandidature(String experienceCandidature) {
		return dao.selectByExperienceCandidature(experienceCandidature);
	}

	public List<Candidature> searchByDomaine(Domaine domaine) {
		return dao.selectByDomaine(domaine);
	}

	public List<Candidature> searchByCv(Document cv) {
		return dao.selectByCv(cv);
	}

	public List<Candidature> searchByMotivation(Document motivation) {
		return dao.selectByMotivation(motivation);
	}

	public List<Candidature> searchByVilleCandidature(Ville villeCandidature) {
		return dao.selectByVilleCandidature(villeCandidature);
	}

	public List<Candidature> selectByFiltre(String motCle, String categorie, Ville villeCandidature, Domaine domaine) {
		return dao.selectByFiltre(motCle, categorie, villeCandidature, domaine);
	}
}
