package fr.nnyimc.microservice_assure.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Personne implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull
	@Length(min=2, max=40, message="Le nom doit être compris entre 2 et 40 caractères")
	private String nom;
	@NotNull
	@Length(min=2, max=40, message="Le prénom doit être compris entre 2 et 40 caractères")
	private String prenom;
	@NotNull
	@Column(name="numeroPersonne", unique=true)
	private Long numeroPersonne;
	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dateNaissance;
	
	public Personne() {
		super();
	}

	public Personne(Long id,
			@NotNull @Length(min = 2, max = 40, message = "Le nom doit être compris entre 2 et 40 caractères") String nom,
			@NotNull @Length(min = 2, max = 40, message = "Le prénom doit être compris entre 2 et 40 caractères") String prenom,
			@NotNull Long numeroPersonne, @NotNull Date dateNaissance) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.numeroPersonne = numeroPersonne;
		this.dateNaissance = dateNaissance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Long getNumeroPersonne() {
		return numeroPersonne;
	}

	public void setNumeroPersonne(Long numeroPersonne) {
		this.numeroPersonne = numeroPersonne;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	
}
