package com.irecrutements.spring.dao;

import java.util.Date;
import java.util.List;

import com.irecrutements.spring.models.Document;
import com.irecrutements.spring.models.Utilisateur;

public interface UtilisateurDao {
	public int insert(Utilisateur c);
	public int update(Utilisateur c);
	public int delete(Utilisateur c);
	public List<Utilisateur> selectAll();
	public Utilisateur selectById(int id);
	public List<Utilisateur> selectByNomPrenomUtilisateur(String nomPrenomUtilisateur);
	public List<Utilisateur> selectByEmailUtilisateur(String emailUtilisateur);
	public Utilisateur findByEmailUtilisateur(String emailUtilisateur);
	public List<Utilisateur> selectByPwdUtilisateur(String pwdUtilisateur);
	public List<Utilisateur> selectByCiviliteUtilisateur(String civiliteUtilisateur);
	public List<Utilisateur> selectByAdresse(String adresse);
	public List<Utilisateur> selectByTelephone(String telephone);
	public List<Utilisateur> selectByDateInscription(Date dateInscription);
	public List<Utilisateur> selectByPhoto(Document photo);
	
	
}
