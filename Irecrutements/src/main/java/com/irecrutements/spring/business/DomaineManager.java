package com.irecrutements.spring.business;

import java.util.List;

import com.irecrutements.spring.models.Domaine;

public interface DomaineManager {
	public int add(Domaine c);
	public int update(Domaine c);
	public int delete(Domaine c);
	public List<Domaine> selectAll();
	public Domaine searchById(int id);
	public List<Domaine> searchByNomDomaine(String nomDomaine);
	public Domaine searchByNomDomaineExact(String nomDomaine);
	public List<Domaine> searchByDescriptionDomaine(String descriptionDomaine);
	
}
