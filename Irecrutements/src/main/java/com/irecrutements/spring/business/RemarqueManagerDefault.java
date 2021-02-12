package com.irecrutements.spring.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irecrutements.spring.dao.RemarqueDao;
import com.irecrutements.spring.models.Remarque;
@Service
public class RemarqueManagerDefault implements RemarqueManager{
	@Autowired
	private RemarqueDao dao;
//	public RemarqueManagerDefault() {
//		// TODO Auto-generated constructor stub
//	}
//	
//	public RemarqueManagerDefault(RemarqueDao dao) {
//		super();
//		this.dao = dao;
//	}
//
//	public RemarqueDao getDao() {
//		return dao;
//	}
//
//	public void setDao(RemarqueDao dao) {
//		this.dao = dao;
//	}

	public int add(Remarque c) {
		return dao.insert(c);
	}

	public int update(Remarque c) {
		return dao.update(c);
	}

	public int delete(Remarque c) {
		return dao.delete(c);
	}

	public List<Remarque> selectAll() {
		return dao.selectAll();
	}

	public Remarque searchById(int id) {
		return dao.selectById(id);
	}

	public List<Remarque> searchByTypeRemarque(String typeRemarque) {
		return dao.selectByTypeRemarque(typeRemarque);
	}

	public List<Remarque> searchByCoreRemarque(String coreRemarque) {
		return dao.selectByCoreRemarque(coreRemarque);
	}
	
}
