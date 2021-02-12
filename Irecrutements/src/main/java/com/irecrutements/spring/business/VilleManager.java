package com.irecrutements.spring.business;

import java.util.List;

import com.irecrutements.spring.models.Ville;

public interface VilleManager {
	public int add(Ville c);
	public int update(Ville c);
	public int delete(Ville c);
	public List<Ville> selectAll();
	public Ville searchById(int id);
	public List<Ville> searchByNomVille(String nomVille);
	public Ville searchByNomVilleExact(String nomVille);
	public List<Ville> searchByPaysVille(String paysVille);
	public List<Ville> selectByNomPays(String nomVille, String paysVille);
}
