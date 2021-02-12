package com.irecrutements.spring.dao;

import java.util.List;

import com.irecrutements.spring.models.Candidature;
import com.irecrutements.spring.models.Document;
import com.irecrutements.spring.models.Domaine;
import com.irecrutements.spring.models.Ville;

public interface CandidatureDao {
	public int insert(Candidature c);
	public int update(Candidature c);
	public int delete(Candidature c);
	public List<Candidature> selectAll();
	public List<Candidature> selectAllByOrdre();
	public Candidature selectById(int id);
	public List<Candidature> selectByIntituleCandidature(String intituleCandidature);
	public List<Candidature> selectByTypeCandidature(String typeCandidature);
	public List<Candidature> selectByEtatCandidature(String etatCandidature);
	public List<Candidature> selectByExperienceCandidature(String experienceCandidature);
	public List<Candidature> selectByDomaine(Domaine domaine);
	public List<Candidature> selectByCv(Document cv);
	public List<Candidature> selectByMotivation(Document motivation);
	public List<Candidature> selectByVilleCandidature(Ville villeCandidature);
	
	public List<Candidature> selectByFiltre(String motCle, String categorie, Ville villeCandidature, Domaine domaine);
}
