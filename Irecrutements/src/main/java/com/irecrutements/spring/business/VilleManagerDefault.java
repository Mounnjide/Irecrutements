package com.irecrutements.spring.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irecrutements.spring.dao.VilleDao;
import com.irecrutements.spring.models.Ville;
@Service
public class VilleManagerDefault implements VilleManager{
	@Autowired
	private VilleDao dao;
//	public VilleManagerDefault() {
//		// TODO Auto-generated constructor stub
//	}
//	
//	public VilleManagerDefault(VilleDao dao) {
//		super();
//		this.dao = dao;
//	}
//
//	public VilleDao getDao() {
//		return dao;
//	}
//
//	public void setDao(VilleDao dao) {
//		this.dao = dao;
//	}

	public int add(Ville c) {
		return dao.insert(c);
	}

	public int update(Ville c) {
		return dao.update(c);
	}

	public int delete(Ville c) {
		return dao.delete(c);
	}

	public List<Ville> selectAll() {
		return dao.selectAll();
	}

	public Ville searchById(int id) {
		return dao.selectById(id);
	}

	public List<Ville> searchByNomVille(String nomVille) {
		return dao.selectByNomVille(nomVille);
	}

	@Override
	public Ville searchByNomVilleExact(String nomVille) {
		return dao.selectByNomVilleExact(nomVille);
	}
	public List<Ville> searchByPaysVille(String paysVille) {
		return dao.selectByPaysVille(paysVille);
	}
	
	public List<Ville> selectByNomPays(String nomVille, String paysVille) {
		return dao.selectByNomPays(nomVille, paysVille);
	}
}
