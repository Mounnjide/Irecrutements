package com.irecrutements.spring.models;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;



/**
 * @author zakaria
 * @version 1.0
 * @created 06-mai-2017 20:51:27
 */
@Entity
@Table(name="Employer")
@PrimaryKeyJoinColumn(name="idUtilisateur")
public class Employeur extends Utilisateur implements Serializable{

	@Column(length=20)
	private String infoEntreprise;
	
	@Column(length=20)
	private String siteWeb;
	
	@Column(length=20)
	private String facebookEntreprise;
	
	@Column(length=20)
	private String linkedinEntreprise;

	@OneToMany(cascade=CascadeType.ALL)
	private List<Offre> offres;
	
	@ManyToMany
	@JoinTable(name = "Employeur_CandidatureInteressantes", joinColumns = { @JoinColumn(name = "idUtilisateur") }, inverseJoinColumns = { @JoinColumn(name = "idCandidature") })
	private List<Candidature> candidatureInteressantes;
	
	public Employeur(){
		offres = new Vector<Offre>();
		candidatureInteressantes = new Vector<>();
		
		
		getRoles().add(new Role("EMPLOYEUR"));
	}

	public Employeur(String infoEntreprise, String siteWeb,
			String facebookEntreprise, String linkedinEntreprise,
			List<Offre> offres) {
		super();
		this.infoEntreprise = infoEntreprise;
		this.siteWeb = siteWeb;
		this.facebookEntreprise = facebookEntreprise;
		this.linkedinEntreprise = linkedinEntreprise;
		this.offres = offres;
	}

	public String getInfoEntreprise() {
		return infoEntreprise;
	}

	public void setInfoEntreprise(String infoEntreprise) {
		this.infoEntreprise = infoEntreprise;
	}

	public String getSiteWeb() {
		return siteWeb;
	}

	public void setSiteWeb(String siteWeb) {
		this.siteWeb = siteWeb;
	}

	public String getFacebookEntreprise() {
		return facebookEntreprise;
	}

	public void setFacebookEntreprise(String facebookEntreprise) {
		this.facebookEntreprise = facebookEntreprise;
	}

	public String getLinkedinEntreprise() {
		return linkedinEntreprise;
	}

	public void setLinkedinEntreprise(String linkedinEntreprise) {
		this.linkedinEntreprise = linkedinEntreprise;
	}

	public List<Offre> getOffres() {
		return offres;
	}

	public void setOffres(List<Offre> offres) {
		this.offres = offres;
	}

	public List<Candidature> getCandidatureInteressantes() {
		return candidatureInteressantes;
	}

	public void setCandidatureInteressantes(List<Candidature> candidatureInteressantes) {
		this.candidatureInteressantes = candidatureInteressantes;
	}

	public void addOffre(Offre offre){
		offres.add(offre);
	}
	
	public void addCandidatureInteressantes(Candidature candidature){
		for (Candidature c : candidatureInteressantes) {
			if(c.getIdCandidature() == candidature.getIdCandidature())return;
		}
		this.candidatureInteressantes.add(candidature);
	}
	
	public void deleteCandidatureInteressantes(Candidature candidature){
		int pos = -1;
		for (int i = 0; i < candidatureInteressantes.size(); i++) {
			if(candidatureInteressantes.get(i).getIdCandidature() == candidature.getIdCandidature())pos = i;
		}
		candidatureInteressantes.remove(pos);
	}
	
	@Override
	public String toString() {
		return "Employeur [infoEntreprise=" + infoEntreprise + ", siteWeb=" + siteWeb + ", facebookEntreprise="
				+ facebookEntreprise + ", linkedinEntreprise=" + linkedinEntreprise + ", offres=" + offres
				+ ", getIdUtilisateur()=" + getIdUtilisateur() + ", getNomUtilisateur()=" + getNomUtilisateur()
				+ ", getPrenomUtilisateur()=" + getPrenomUtilisateur() + ", getEmailUtilisateur()="
				+ getEmailUtilisateur() + ", getPwdUtilisateur()=" + getPwdUtilisateur() + ", getCiviliteUtilisateur()="
				+ getCiviliteUtilisateur() + ", getAdresse()=" + getAdresse() + ", getTelephone()=" + getTelephone()
				+ ", getDateInscription()=" + getDateInscription() + ", getPhoto()=" + getPhoto() + ", isActive()="
				+ isActive() + "]";
	}
	
}