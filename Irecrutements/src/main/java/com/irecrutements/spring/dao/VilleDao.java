package com.irecrutements.spring.dao;

import java.util.List;

import com.irecrutements.spring.models.Ville;

public interface VilleDao {
	public int insert(Ville c);
	public int update(Ville c);
	public int delete(Ville c);
	public List<Ville> selectAll();
	public Ville selectById(int id);
	public List<Ville> selectByNomVille(String nomVille);
	public Ville selectByNomVilleExact(String nomVille);
	public List<Ville> selectByPaysVille(String paysVille);
	public List<Ville> selectByNomPays(String nomVille, String paysVille);
}
