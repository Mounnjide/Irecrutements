package com.irecrutements.spring.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irecrutements.spring.dao.DomaineDao;
import com.irecrutements.spring.models.Domaine;
@Service
public class DomaineManagerDefault implements DomaineManager{
	@Autowired
	private DomaineDao dao;
//	public DomaineManagerDefault() {
//		// TODO Auto-generated constructor stub
//	}
//	
//	public DomaineManagerDefault(DomaineDao dao) {
//		super();
//		this.dao = dao;
//	}
//
//	public DomaineDao getDao() {
//		return dao;
//	}
//
//	public void setDao(DomaineDao dao) {
//		this.dao = dao;
//	}

	public int add(Domaine c) {
		return dao.insert(c);
	}

	public int update(Domaine c) {
		return dao.update(c);
	}

	public int delete(Domaine c) {
		return dao.delete(c);
	}

	public List<Domaine> selectAll() {
		return dao.selectAll();
	}

	public Domaine searchById(int id) {
		return dao.selectById(id);
	}

	public List<Domaine> searchByNomDomaine(String nomDomaine) {
		return dao.selectByNomDomaine(nomDomaine);
	}
	@Override
	public Domaine searchByNomDomaineExact(String nomDomaine) {
		return dao.searchByNomDomaineExact(nomDomaine);
	}
	public List<Domaine> searchByDescriptionDomaine(String descriptionDomaine) {
		return dao.selectByDescriptionDomaine(descriptionDomaine);
	}

}
