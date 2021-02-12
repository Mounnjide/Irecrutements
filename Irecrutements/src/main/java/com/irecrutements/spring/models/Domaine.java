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
 * @created 06-mai-2017 20:51:20
 */
@Entity
@Table(name="Domaine")
public class Domaine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDomaine;
	@Column(length=50)
	private String nomDomaine;
	@Column(length=100)
	private String descriptionDomaine;

	public Domaine(){

	}

	public Domaine(int idDomaine, String nomDomaine, String descriptionDomaine) {
		super();
		this.idDomaine = idDomaine;
		this.nomDomaine = nomDomaine;
		this.descriptionDomaine = descriptionDomaine;
	}

	public int getIdDomaine() {
		return idDomaine;
	}

	public void setIdDomaine(int idDomaine) {
		this.idDomaine = idDomaine;
	}

	public String getNomDomaine() {
		return nomDomaine;
	}

	public void setNomDomaine(String nomDomaine) {
		this.nomDomaine = nomDomaine;
	}

	public String getDescriptionDomaine() {
		return descriptionDomaine;
	}

	public void setDescriptionDomaine(String descriptionDomaine) {
		this.descriptionDomaine = descriptionDomaine;
	}

	@Override
	public String toString() {
		return "Domaine [idDomaine=" + idDomaine + ", nomDomaine=" + nomDomaine
				+ ", descriptionDomaine=" + descriptionDomaine + "]";
	}


}