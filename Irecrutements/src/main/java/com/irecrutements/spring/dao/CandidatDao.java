package com.irecrutements.spring.dao;

import java.util.Date;
import java.util.List;

import com.irecrutements.spring.models.Candidat;
import com.irecrutements.spring.models.Candidature;
import com.irecrutements.spring.models.Utilisateur;

public interface CandidatDao {
	public int insert(Candidat c);
	public int update(Candidat c);
	public int delete(Candidat c);
	public List<Candidat> selectAll();
	public Candidat selectById(int id);
	public Candidat findByEmailUtilisateur(String emailUtilisateur);
	public List<Candidat> selectByDateNaissance(Date date);
	public List<Candidat> selectByEtablissementEtude(String etablissementEtude);
	public List<Candidat> selectByNiveauEtude(String niveauEtude);
	public Candidat selectByCandidature(Candidature candidature);

	public Utilisateur emailExiste(String email);
	//autres methodes
//	public void deleteImage();
}
