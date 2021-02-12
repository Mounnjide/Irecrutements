package com.irecrutements.spring.business;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irecrutements.spring.dao.OffreDao;
import com.irecrutements.spring.models.Domaine;
import com.irecrutements.spring.models.Offre;
import com.irecrutements.spring.models.Ville;
@Service
public class OffreManagerDefault implements OffreManager{
	@Autowired
	private OffreDao dao;
	public OffreManagerDefault() {
		// TODO Auto-generated constructor stub
	}
	
	public OffreManagerDefault(OffreDao dao) {
		super();
		this.dao = dao;
	}

	public OffreDao getDao() {
		return dao;
	}

	public void setDao(OffreDao dao) {
		this.dao = dao;
	}

	public int add(Offre c) {
		return dao.insert(c);
	}

	public int update(Offre c) {
		return dao.update(c);
	}

	public int delete(Offre c) {
		return dao.delete(c);
	}

	public List<Offre> selectAll() {
		return dao.selectAll();
	}

	public List<Offre> selectAllByOrdre() {
		return dao.selectAllByOrder();
	}
	public Offre searchById(int id) {
		return dao.selectById(id);
	}

	public List<Offre> searchByIntituleOffre(String intituleOffre) {
		return dao.selectByIntituleOffre(intituleOffre);
	}

	public List<Offre> searchByTypeOffre(String typeOffre) {
		return dao.selectByTypeOffre(typeOffre);
	}

	public List<Offre> searchByDescriptionOffre(String descriptionOffre) {
		return dao.selectByDescriptionOffre(descriptionOffre);
	}

	public List<Offre> searchByDateDebutOffre(Date dateDebutOffre) {
		return dao.selectByDateDebutOffre(dateDebutOffre);
	}

	public List<Offre> searchByDateFinOffre(Date dateFinOffre) {
		return dao.selectByDateFinOffre(dateFinOffre);
	}

	public List<Offre> searchByDureeOffre(String dureeOffre) {
		return dao.selectByDureeOffre(dureeOffre);
	}

	public List<Offre> searchByExperienceRequise(String experienceRequise) {
		return dao.selectByExperienceRequise(experienceRequise);
	}

	public List<Offre> searchByDomaine(Domaine domaine) {
		return dao.selectByDomaine(domaine);
	}
	@Override
	public List<Offre> selectByFiltre(String motCle, String categorie, Ville villeOffre, Domaine domaine) {
		return dao.selectByFiltre(motCle, categorie, villeOffre, domaine);
	}
}
