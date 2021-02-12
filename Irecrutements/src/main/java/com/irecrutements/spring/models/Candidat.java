package com.irecrutements.spring.models;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * @author zakaria
 * @version 1.0
 * @created 06-mai-2017 20:48:37
 */
@Entity
@Table(name="Candidat")
@PrimaryKeyJoinColumn(name="idUtilisateur")
public class Candidat extends Utilisateur implements Serializable{
	
	@Temporal(TemporalType.DATE)
	@Column(name="dateNaissance")
//	@DateTimeFormat(pattern = "dd-MM-yyyy")
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Past
	private Date dateNaissance;
	

	@Column(length=20)
	private String etablissementEtude;

	@Column(length=20)
	private String niveauEtude;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Candidature> candidatures;
	
	@ManyToMany
	@JoinTable(name = "Candidat_OffresInteressantes", joinColumns = { @JoinColumn(name = "idUtilisateur") }, inverseJoinColumns = { @JoinColumn(name = "idOffre") })
	private List<Offre> offresInteressantes;
	

	public Candidat(){
		dateNaissance = new Date();
		candidatures = new Vector<>();
		offresInteressantes = new Vector<>();
		
		getRoles().add(new Role("CANDIDAT"));
	}

	public Candidat(Date dateNaissance, String etablissementEtude,
			String niveauEtude) {
		super();
		this.dateNaissance = dateNaissance;
		this.etablissementEtude = etablissementEtude;
		this.niveauEtude = niveauEtude;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getEtablissementEtude() {
		return etablissementEtude;
	}

	public void setEtablissementEtude(String etablissementEtude) {
		this.etablissementEtude = etablissementEtude;
	}

	public String getNiveauEtude() {
		return niveauEtude;
	}

	public void setNiveauEtude(String niveauEtude) {
		this.niveauEtude = niveauEtude;
	}

	public List<Candidature> getCandidatures() {
		return candidatures;
	}

	public void setCandidatures(List<Candidature> candidatures) {
		this.candidatures = candidatures;
	}
	
	public List<Offre> getOffresInteressantes() {
		return offresInteressantes;
	}

	public void setOffresInteressantes(List<Offre> offresInteressantes) {
		this.offresInteressantes = offresInteressantes;
	}

	public void addCandidature(Candidature candidature) {
		this.candidatures.add(candidature);
	}
	
	public void addOffreInteressantes(Offre offre){
		for (Offre o : offresInteressantes) {
			if(o.getIdOffre() == offre.getIdOffre()) return;
		}
		this.offresInteressantes.add(offre);
	}
	
	public void deleteOffreInteressantes(Offre offre){
		int pos = -1;
		for (int i = 0; i < offresInteressantes.size(); i++) {
			if(offresInteressantes.get(i).getIdOffre() == offre.getIdOffre()) pos = i;
		}
		offresInteressantes.remove(pos);
	}
	@Override
	public String toString() {
		return "Candidat [dateNaissance=" + dateNaissance + ", etablissementEtude=" + etablissementEtude
				+ ", niveauEtude=" + niveauEtude + ", candidatures=" + candidatures + ", getIdUtilisateur()="
				+ getIdUtilisateur() + ", getNomUtilisateur()=" + getNomUtilisateur() + ", getPrenomUtilisateur()="
				+ getPrenomUtilisateur() + ", getEmailUtilisateur()=" + getEmailUtilisateur() + ", getPwdUtilisateur()="
				+ getPwdUtilisateur() + ", getCiviliteUtilisateur()=" + getCiviliteUtilisateur() + ", getAdresse()="
				+ getAdresse() + ", getTelephone()=" + getTelephone() + ", getDateInscription()=" + getDateInscription()
				+ ", getPhoto()=" + getPhoto() + ", isActive()=" + isActive() + "]";
	}

}