package com.irecrutements.spring.models;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.JoinColumn;

/**
 * @author zakaria
 * @version 1.0
 * @created 06-mai-2017 20:51:46
 */ 
@Entity
@Table(name="Utilisateur")
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUtilisateur")
	private int idUtilisateur;

	@Column(length=30)
	@Size(min=2, max=30)
	private String nomUtilisateur;
	
	@Column(length=50)
	@Size(min=2, max=30)
	private String prenomUtilisateur;

	@Column(length=50)
	@NotBlank
	@Email
	private String emailUtilisateur;

	@Column(length=250)//////
	@NotBlank
	private String pwdUtilisateur;

	@Column(length=20)
	@NotBlank
	private String civiliteUtilisateur;

	@Column(length=50)
	private String adresse;

	@Column(length=20)
	private String telephone;

	@Temporal(TemporalType.DATE)
	@Column(name="dateInscription")
	@NotNull
	private Date dateInscription;

	@ManyToOne(cascade=CascadeType.ALL)
	private Document photo;
	//
	@Column
	private boolean active;

//	@ManyToMany(cascade = CascadeType.ALL)
	@ManyToMany
	@JoinTable(name = "Utilisateur_Role", 
		joinColumns ={ @JoinColumn(name = "emailUtilisateur", referencedColumnName="emailUtilisateur") }, 
		inverseJoinColumns = { @JoinColumn(name = "role", referencedColumnName="role") })
	private List<Role> roles;
//	private Role role;
	
	public Utilisateur() {
		dateInscription = new Date();
		photo = new Document();
		active = false;
		roles = new Vector<>();
//		roles.add(new Role("USER"));
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}


	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}


	public String getPrenomUtilisateur() {
		return prenomUtilisateur;
	}

	public void setPrenomUtilisateur(String prenomUtilisateur) {
		this.prenomUtilisateur = prenomUtilisateur;
	}

	public String getEmailUtilisateur() {
		return emailUtilisateur;
	}

	public void setEmailUtilisateur(String emailUtilisateur) {
		this.emailUtilisateur = emailUtilisateur;
	}

	public String getPwdUtilisateur() {
		return pwdUtilisateur;
	}

	public void setPwdUtilisateur(String pwdUtilisateur) {
		this.pwdUtilisateur = pwdUtilisateur;
	}

	public String getCiviliteUtilisateur() {
		return civiliteUtilisateur;
	}

	public void setCiviliteUtilisateur(String civiliteUtilisateur) {
		this.civiliteUtilisateur = civiliteUtilisateur;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}

	public Document getPhoto() {
		return photo;
	}

	public void setPhoto(Document photo) {
		this.photo = photo;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Utilisateur [idUtilisateur=" + idUtilisateur + ", nomUtilisateur=" + nomUtilisateur
				+ ", prenomUtilisateur=" + prenomUtilisateur + ", emailUtilisateur=" + emailUtilisateur
				+ ", pwdUtilisateur=" + pwdUtilisateur + ", civiliteUtilisateur=" + civiliteUtilisateur + ", adresse="
				+ adresse + ", telephone=" + telephone + ", dateInscription=" + dateInscription + ", photo=" + photo
				+ ", active=" + active + ", roles=" + roles + "]";
	}
	
}