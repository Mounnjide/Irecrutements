package com.irecrutements.spring.business;

import java.util.List;

import com.irecrutements.spring.models.Candidature;
import com.irecrutements.spring.models.Document;
import com.irecrutements.spring.models.Domaine;
import com.irecrutements.spring.models.Ville;

public interface CandidatureManager {
	public int add(Candidature c);
	public int update(Candidature c);
	public int delete(Candidature c);
	public List<Candidature> selectAll();
	public List<Candidature> selectAllByOrder();
	public Candidature searchById(int id);
	public List<Candidature> searchByIntituleCandidature(String intituleCandidature);
	public List<Candidature> searchByTypeCandidature(String typeCandidature);
	public List<Candidature> searchByEtatCandidature(String etatCandidature);
	public List<Candidature> searchByExperienceCandidature(String experienceCandidature);
	public List<Candidature> searchByDomaine(Domaine domaine);
	public List<Candidature> searchByCv(Document cv);
	public List<Candidature> searchByMotivation(Document motivation);
	public List<Candidature> searchByVilleCandidature(Ville villeCandidature);

	public List<Candidature> selectByFiltre(String motCle, String categorie, Ville villeCandidature, Domaine domaine);
}
