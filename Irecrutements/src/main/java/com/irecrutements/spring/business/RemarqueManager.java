package com.irecrutements.spring.business;

import java.util.List;

import com.irecrutements.spring.models.Remarque;

public interface RemarqueManager {
	public int add(Remarque c);
	public int update(Remarque c);
	public int delete(Remarque c);
	public List<Remarque> selectAll();
	public Remarque searchById(int id);
	public List<Remarque> searchByTypeRemarque(String typeRemarque);
	public List<Remarque> searchByCoreRemarque(String coreRemarque);
	
}
