package com.irecrutements.spring.dao;

import java.util.List;

import com.irecrutements.spring.models.Domaine;


public interface DomaineDao {
	public int insert(Domaine c);
	public int update(Domaine c);
	public int delete(Domaine c);
	public List<Domaine> selectAll();
	public Domaine selectById(int id);
	public List<Domaine> selectByNomDomaine(String nomDomaine);
	public Domaine searchByNomDomaineExact(String nomDomaine);
	public List<Domaine> selectByDescriptionDomaine(String descriptionDomaine);
	
}
