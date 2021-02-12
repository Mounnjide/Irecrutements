package com.irecrutements.spring.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * @author zakaria
 * @version 1.0
 * @created 06-mai-2017 20:51:53
 */

@Entity
@Table(name="Ville")
public class Ville {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idVille;
	
	@Column(length=20)
	private String nomVille;
	
	@Column(length=20)
	private String paysVille;

	public Ville(){

	}

	public Ville(int idVille, String nomVille, String paysVille) {
		super();
		this.idVille = idVille;
		this.nomVille = nomVille;
		this.paysVille = paysVille;
	}

	public long getIdVille() {
		return idVille;
	}

	public void setIdVille(int idVille) {
		this.idVille = idVille;
	}

	public String getNomVille() {
		return nomVille;
	}

	public void setNomVille(String nomVille) {
		this.nomVille = nomVille;
	}

	public String getPaysVille() {
		return paysVille;
	}

	public void setPaysVille(String paysVille) {
		this.paysVille = paysVille;
	}
	
	public String toString() {
		return "Ville [idVille=" + idVille + ", nomVille=" + nomVille
				+ ", paysVille=" + paysVille + "]";
	}

}