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
 * @created 06-mai-2017 20:51:38
 */
@Entity
@Table(name="Remarque")
public class Remarque {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int idRemarque;
	@Column(length=20)
	private String typeRemarque;
	@Column(length=100)
	private String coreRemarque;

	public Remarque(){

	}

	public Remarque(int idRemarque, String typeRemarque, String coreRemarque) {
		super();
		this.idRemarque = idRemarque;
		this.typeRemarque = typeRemarque;
		this.coreRemarque = coreRemarque;
	}

	public int getIdRemarque() {
		return idRemarque;
	}

	public void setIdRemarque(int idRemarque) {
		this.idRemarque = idRemarque;
	}

	public String getTypeRemarque() {
		return typeRemarque;
	}

	public void setTypeRemarque(String typeRemarque) {
		this.typeRemarque = typeRemarque;
	}

	public String getCoreRemarque() {
		return coreRemarque;
	}

	public void setCoreRemarque(String coreRemarque) {
		this.coreRemarque = coreRemarque;
	}

	@Override
	public String toString() {
		return "Remarque [idRemarque=" + idRemarque + ", typeRemarque="
				+ typeRemarque + ", coreRemarque=" + coreRemarque + "]";
	}
	

}