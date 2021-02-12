package com.irecrutements.spring.dao;

import java.util.List;

import com.irecrutements.spring.models.Remarque;


public interface RemarqueDao {
	public int insert(Remarque c);
	public int update(Remarque c);
	public int delete(Remarque c);
	public List<Remarque> selectAll();
	public Remarque selectById(int id);
	public List<Remarque> selectByTypeRemarque(String typeRemarque);
	public List<Remarque> selectByCoreRemarque(String coreRemarque);
	
}
