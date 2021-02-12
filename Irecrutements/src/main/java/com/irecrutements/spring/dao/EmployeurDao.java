package com.irecrutements.spring.dao;

import java.util.List;

import com.irecrutements.spring.models.Employeur;
import com.irecrutements.spring.models.Offre;
import com.irecrutements.spring.models.Utilisateur;

public interface EmployeurDao {
	public int insert(Employeur c);
	public int update(Employeur c);
	public int delete(Employeur c);
	public List<Employeur> selectAll();
	public Employeur selectById(int id);
	public Employeur findByEmailUtilisateur(String emailUtilisateur);
	public List<Employeur> selectByInfoEntreprise(String infoEntreprise);
	public List<Employeur> selectBySiteWeb(String siteWeb);
	public List<Employeur> selectByFacebookEntreprise(String facebookEntreprise);
	public List<Employeur> selectByLinkedinEntreprise(String linkedinEntreprise);
	public Employeur selectByOffre(Offre offre);
	public Utilisateur emailExiste(String email);
}
