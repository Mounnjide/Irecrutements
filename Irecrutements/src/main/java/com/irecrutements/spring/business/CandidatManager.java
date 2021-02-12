package com.irecrutements.spring.business;

import java.util.Date;
import java.util.List;

import com.irecrutements.spring.models.Candidat;
import com.irecrutements.spring.models.Candidature;
import com.irecrutements.spring.models.Utilisateur;

public interface CandidatManager {
	public int add(Candidat c);
	public int update(Candidat c);
	public int delete(Candidat c);
	public List<Candidat> selectAll();
	public Candidat searchById(int id);
	public Candidat searchByEmailUtilisateur(String emailUtilisateur);
	public List<Candidat> searchByDateNaissance(Date date);
	public List<Candidat> searchByEtablissementEtude(String etablissementEtude);
	public List<Candidat> searchByNiveauEtude(String niveauEtude);
	public Candidat searchByCandidature(Candidature candidature);

	public Utilisateur emailExiste(String email);
}
