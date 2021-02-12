package com.irecrutements.spring.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;



/**
 * @author zakaria
 * @version 1.0
 * @created 06-mai-2017 20:51:32
 */
@Entity
@Table(name = "Offre")
public class Offre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idOffre")
	private int idOffre;
	
	@Column(length=50)
	private String intituleOffre;
	
	@Column(length=50)
	private String typeOffre;
	
	@Column(length=50)
	private String descriptionOffre;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date dateDebutOffre;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date dateFinOffre;
	
	@Column(length=50)
	private String dureeOffre;
	
	@Column(length=50)
	private String experienceRequise;
	
//	@ManyToMany(cascade = CascadeType.ALL)
	@ManyToMany
	@JoinTable(name = "Offre_Domaine", joinColumns = { @JoinColumn(name = "idOffre") }, inverseJoinColumns = { @JoinColumn(name = "idDomaine") })
	private List<Domaine> domaines;

//	@ManyToOne(cascade=CascadeType.ALL)
	@ManyToOne
	private Ville villeOffre;

//	@OneToMany
////	private List<Candidature> candidatures;
////	@MapKey
//	@MapKey(name = "idUtilisateur")
//	private Map<Integer, Candidature> candidatures;
	@ElementCollection
	@JoinTable(name="Offre_Candidature", joinColumns=@JoinColumn(name="idOffre"))
	@MapKeyColumn(name="idUtilisateur")
	@Column(name="candidature")
	private Map<Integer, Candidature> candidatures;
	
	public Offre(){
		domaines = new Vector<>();
//		candidatures = new Vector<>();
		candidatures = new HashMap();
	}

	public Offre(int idOffre, String intituleOffre, String typeOffre,
			String descriptionOffre, Date dateDebutOffre, Date dateFinOffre,
			String dureeOffre, String experienceRequise,
			List<Domaine> domaines, Ville villeOffre) {
		super();
		this.idOffre = idOffre;
		this.intituleOffre = intituleOffre;
		this.typeOffre = typeOffre;
		this.descriptionOffre = descriptionOffre;
		this.dateDebutOffre = dateDebutOffre;
		this.dateFinOffre = dateFinOffre;
		this.dureeOffre = dureeOffre;
		this.experienceRequise = experienceRequise;
		this.domaines = domaines;
		this.villeOffre = villeOffre;
	}

	public int getIdOffre() {
		return idOffre;
	}

	public void setIdOffre(int idOffre) {
		this.idOffre = idOffre;
	}

	public String getIntituleOffre() {
		return intituleOffre;
	}

	public void setIntituleOffre(String intituleOffre) {
		this.intituleOffre = intituleOffre;
	}

	public String getTypeOffre() {
		return typeOffre;
	}

	public void setTypeOffre(String typeOffre) {
		this.typeOffre = typeOffre;
	}

	public String getDescriptionOffre() {
		return descriptionOffre;
	}

	public void setDescriptionOffre(String descriptionOffre) {
		this.descriptionOffre = descriptionOffre;
	}
	
	public Date getDateDebutOffre() {
		return dateDebutOffre;
	}

	public void setDateDebutOffre(Date dateDebutOffre) {
		this.dateDebutOffre = dateDebutOffre;
	}

	public Date getDateFinOffre() {
		return dateFinOffre;
	}

	public void setDateFinOffre(Date dateFinOffre) {
		this.dateFinOffre = dateFinOffre;
	}

	public String getDureeOffre() {
		return dureeOffre;
	}

	public void setDureeOffre(String dureeOffre) {
		this.dureeOffre = dureeOffre;
	}

	public String getExperienceRequise() {
		return experienceRequise;
	}

	public void setExperienceRequise(String experienceRequise) {
		this.experienceRequise = experienceRequise;
	}

	public List<Domaine> getDomaines() {
		return domaines;
	}

	public void setDomaines(List<Domaine> domaines) {
		this.domaines = domaines;
	}

	public Ville getVilleOffre() {
		return villeOffre;
	}

	public void setVilleOffre(Ville villeOffre) {
		this.villeOffre = villeOffre;
	}
	
	
	public Map<Integer, Candidature> getCandidatures() {
		return candidatures;
	}

	public void setCandidatures(Map<Integer, Candidature> candidatures) {
		this.candidatures = candidatures;
	}

	public void addDomaine(Domaine domaine){
		domaines.add(domaine);
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
	
	public String getDateDebutFormat(){
		return new SimpleDateFormat("MM-dd-yyyy").format(dateDebutOffre);
	}
	public String getDateFinFormat(){
		return new SimpleDateFormat("MM-dd-yyyy").format(dateFinOffre);
	}
	
//	public void addCandidature(Candidature candidature){
//		for (Candidature can : candidatures) {
//			if(can.getIdCandidature() == candidature.getIdCandidature())return;
//		}
//		candidatures.add(candidature);
//	}

	
	public void addCandidature(int idCandidat, Candidature candidature){
		if(! candidatures.containsKey(idCandidat)) candidatures.put(idCandidat, candidature);
	}
	
	@Override
	public String toString() {
		return "Offre [idOffre=" + idOffre + ", intituleOffre=" + intituleOffre
				+ ", typeOffre=" + typeOffre + ", descriptionOffre="
				+ descriptionOffre + ", dateDebutOffre=" + dateDebutOffre
				+ ", dateFinOffre=" + dateFinOffre + ", dureeOffre="
				+ dureeOffre + ", experienceRequise=" + experienceRequise
				+ ", domaines=" + domaines + ", villeOffre=" + villeOffre + "]";
	}

	

}