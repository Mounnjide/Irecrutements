package com.irecrutements.spring.business;

import java.util.Date;
import java.util.List;

import com.irecrutements.spring.models.Candidat;
import com.irecrutements.spring.models.Document;
import com.irecrutements.spring.models.Utilisateur;

public interface UtilisateurManager {
	public int add(Utilisateur c);
	public int update(Utilisateur c);
	public int delete(Utilisateur c);
	public List<Utilisateur> selectAll();
	public Utilisateur searchById(int id);
	public List<Utilisateur> searchByNomPrenomUtilisateur(String nomPrenomUtilisateur);
	public Utilisateur findByEmailUtilisateur(String emailUtilisateur);
	public List<Utilisateur> searchByEmailUtilisateur(String emailUtilisateur);
	public List<Utilisateur> searchByPwdUtilisateur(String pwdUtilisateur);
	public List<Utilisateur> searchByCiviliteUtilisateur(String civiliteUtilisateur);
	public List<Utilisateur> searchByAdresse(String adresse);
	public List<Utilisateur> searchByTelephone(String telephone);
	public List<Utilisateur> searchByDateInscription(Date dateInscription);
	public List<Utilisateur> searchByPhoto(Document photo);
	
}
