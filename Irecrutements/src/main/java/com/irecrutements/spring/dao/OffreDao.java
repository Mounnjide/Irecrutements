package com.irecrutements.spring.dao;

import java.util.Date;
import java.util.List;

import com.irecrutements.spring.models.Candidature;
import com.irecrutements.spring.models.Domaine;
import com.irecrutements.spring.models.Offre;
import com.irecrutements.spring.models.Ville;

public interface OffreDao {
	public int insert(Offre c);
	public int update(Offre c);
	public int delete(Offre c);
	public List<Offre> selectAll();
	public List<Offre> selectAllByOrder();
	public Offre selectById(int id);
	public List<Offre> selectByIntituleOffre(String intituleOffre);
	public List<Offre> selectByTypeOffre(String typeOffre);
	public List<Offre> selectByDescriptionOffre(String descriptionOffre);
	public List<Offre> selectByDateDebutOffre(Date dateDebutOffre);
	public List<Offre> selectByDateFinOffre(Date dateFinOffre);
	public List<Offre> selectByDureeOffre(String dureeOffre);
	public List<Offre> selectByExperienceRequise(String experienceRequise);
	public List<Offre> selectByDomaine(Domaine domaine);
	
	public List<Offre> selectByFiltre(String motCle, String categorie, Ville villeOffre, Domaine domaine);

}
