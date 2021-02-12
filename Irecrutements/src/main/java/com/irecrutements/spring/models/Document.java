package com.irecrutements.spring.models;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author zakaria
 * @version 1.0
 * @created 06-mai-2017 20:51:11
 */
@Entity
@Table(name="Document")
public class Document {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idDocument")
	private int idDocument;

	@Column(length=50)
	private String titreDocument;

	@Column(length=20)
	private String typeDocument;
	
	@Column
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] contenu;
	
	@Column
	@NotNull
	private Date dateChargement;
	
	public Document(){

	}
	
	public Document(int idDocument, String titreDocument, String typeDocument) {
		super();
		this.idDocument = idDocument;
		this.titreDocument = titreDocument;
		this.typeDocument = typeDocument;
	}

	public int getIdDocument() {
		return idDocument;
	}

	public void setIdDocument(int idDocument) {
		this.idDocument = idDocument;
	}

	public String getTitreDocument() {
		return titreDocument;
	}

	public void setTitreDocument(String titreDocument) {
		this.titreDocument = titreDocument;
	}

	public String getTypeDocument() {
		return typeDocument;
	}

	public void setTypeDocument(String typeDocument) {
		this.typeDocument = typeDocument;
	}
	
	public byte[] getContenu() {
		return contenu;
	}

	public void setContenu(byte[] contenu) {
		this.contenu = contenu;
	}

	public Date getDateChargement() {
		return dateChargement;
	}

	public void setDateChargement(Date dateChargement) {
		this.dateChargement = dateChargement;
	}

	@Override
	public String toString() {
		return "Document [idDocument=" + idDocument + ", titreDocument=" + titreDocument + ", typeDocument="
				+ typeDocument + ", dateChargement=" + dateChargement + "]";
	}

}