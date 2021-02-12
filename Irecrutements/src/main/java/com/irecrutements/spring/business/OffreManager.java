package com.irecrutements.spring.business;

import java.util.Date;
import java.util.List;

import com.irecrutements.spring.models.Candidature;
import com.irecrutements.spring.models.Domaine;
import com.irecrutements.spring.models.Offre;
import com.irecrutements.spring.models.Ville;

public interface OffreManager {
	public int add(Offre c);
	public int update(Offre c);
	public int delete(Offre c);
	public List<Offre> selectAll();
	public List<Offre> selectAllByOrdre();
	public Offre searchById(int id);
	public List<Offre> searchByIntituleOffre(String intituleOffre);
	public List<Offre> searchByTypeOffre(String typeOffre);
	public List<Offre> searchByDescriptionOffre(String descriptionOffre);
	public List<Offre> searchByDateDebutOffre(Date dateDebutOffre);
	public List<Offre> searchByDateFinOffre(Date dateFinOffre);
	public List<Offre> searchByDureeOffre(String dureeOffre);
	public List<Offre> searchByExperienceRequise(String experienceRequise);
	public List<Offre> searchByDomaine(Domaine domaine);
	
	public List<Offre> selectByFiltre(String motCle, String categorie, Ville villeOffre, Domaine domaine);
}
