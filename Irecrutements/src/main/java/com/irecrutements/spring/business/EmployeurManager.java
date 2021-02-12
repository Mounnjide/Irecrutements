package com.irecrutements.spring.business;

import java.util.List;

import com.irecrutements.spring.models.Employeur;
import com.irecrutements.spring.models.Offre;
import com.irecrutements.spring.models.Utilisateur;

public interface EmployeurManager {
	public int add(Employeur c);
	public int update(Employeur c);
	public int delete(Employeur c);
	public List<Employeur> selectAll();
	public Employeur searchById(int id);
	public Employeur searchByEmailUtilisateur(String emailUtilisateur);
	public List<Employeur> searchByInfoEntreprise(String infoEntreprise);
	public List<Employeur> searchBySiteWeb(String siteWeb);
	public List<Employeur> searchByFacebookEntreprise(String facebookEntreprise);
	public List<Employeur> searchByLinkedinEntreprise(String linkedinEntreprise);
	public Employeur searchByOffre(Offre offre);
	public Utilisateur emailExiste(String email);
}
