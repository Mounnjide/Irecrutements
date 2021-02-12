package com.irecrutements.spring.models;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;



/**
 * @author zakaria
 * @version 1.0
 * @created 06-mai-2017 20:50:49
 */
@Entity
@Table(name = "Candidature")
public class Candidature {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCandidature")
	private int idCandidature;

	@Column(length=50)
	@NotBlank
	private String intituleCandidature;

	@Column(length=50)
	@NotBlank
	private String typeCandidature;

	@Column(length=50)
	private String etatCandidature;

	@Column(length=50)
	private String experienceCandidature;

//	@ManyToMany(cascade = CascadeType.ALL)
	@ManyToMany
	@JoinTable(name = "Candidature_Domaine", joinColumns = { @JoinColumn(name = "idCandidature") }, inverseJoinColumns = { @JoinColumn(name = "idDomaine") })
	private List<Domaine> domaines;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Document cv;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Document motivation;

//	@ManyToOne(cascade=CascadeType.ALL)
	@ManyToOne
	private Ville villeCandidature;
	
	public Candidature(){
		domaines = new Vector<Domaine>();
	}

	public Candidature(int idCandidature, String intituleCandidature,
			String typeCandidature, String etatCandidature,
			String experienceCandidature, Ville villeCandidature) {
		super();
		this.idCandidature = idCandidature;
		this.intituleCandidature = intituleCandidature;
		this.typeCandidature = typeCandidature;
		this.etatCandidature = etatCandidature;
		this.experienceCandidature = experienceCandidature;
		this.villeCandidature = villeCandidature;
	}

	public int getIdCandidature() {
		return idCandidature;
	}

	public void setIdCandidature(int idCandidature) {
		this.idCandidature = idCandidature;
	}

	public String getIntituleCandidature() {
		return intituleCandidature;
	}

	public void setIntituleCandidature(String intituleCandidature) {
		this.intituleCandidature = intituleCandidature;
	}

	public String getTypeCandidature() {
		return typeCandidature;
	}

	public void setTypeCandidature(String typeCandidature) {
		this.typeCandidature = typeCandidature;
	}

	public String getEtatCandidature() {
		return etatCandidature;
	}

	public void setEtatCandidature(String etatCandidature) {
		this.etatCandidature = etatCandidature;
	}

	public String getExperienceCandidature() {
		return experienceCandidature;
	}

	public void setExperienceCandidature(String experienceCandidature) {
		this.experienceCandidature = experienceCandidature;
	}

	public List<Domaine> getDomaines() {
		return domaines;
	}

	public void setDomaines(List<Domaine> domaines) {
		this.domaines = domaines;
	}

	public Document getCv() {
		return cv;
	}

	public void setCv(Document cv) {
		this.cv = cv;
	}

	public Document getMotivation() {
		return motivation;
	}

	public void setMotivation(Document motivation) {
		this.motivation = motivation;
	}

	public Ville getVilleCandidature() {
		return villeCandidature;
	}

	public void setVilleCandidature(Ville villeCandidature) {
		this.villeCandidature = villeCandidature;
	}

	public void addDomaine(Domaine domaine) {
		this.domaines.add(domaine);
	}
	
	public String domainesToString(){
		StringBuilder nomDomaines = new StringBuilder("");
		
		for (Iterator iterator = domaines.iterator(); iterator.hasNext();) {
			Domaine domaine = (Domaine) iterator.next();
			nomDomaines.append(domaine.getNomDomaine() + " - ");
		}
		if(nomDomaines.length()>0){
			nomDomaines.replace(nomDomaines.length()-3, nomDomaines.length(), "");
		}
//		System.out.println(nomDomaines.toString());
		return nomDomaines.toString();
	}
	
	public boolean domaineExiste(int id){
		for (Domaine domaine : domaines) {
			if(domaine.getIdDomaine() == id) return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Candidature [idCandidature=" + idCandidature
				+ ", intituleCandidature=" + intituleCandidature
				+ ", typeCandidature=" + typeCandidature + ", etatCandidature="
				+ etatCandidature + ", experienceCandidature="
				+ experienceCandidature + ", domaines=" + domaines + ", cv="
				+ cv + ", motivation=" + motivation + ", villeCandidature="
				+ villeCandidature + "]";
	}

}