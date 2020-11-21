package fr.nnyimc.microservice_assure.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFilter;


@Entity
@PrimaryKeyJoinColumn(name="id")
@JsonFilter("HiddenValue")
public class Assure extends Personne implements Serializable{
	@NotNull
	private String dossierMedical;
	@NotNull
	@Column(name="numeroAssure", unique = true)
	private Long numeroAssure;
	
	public Assure() {
		super();
	}
	
	public Assure(@NotNull String dossierMedical, @NotNull Long numeroAssure) {
		super();
		this.dossierMedical = dossierMedical;
		this.numeroAssure = numeroAssure;
	}

	public String getDossierMedical() {
		return dossierMedical;
	}

	public void setDossierMedical(String dossierMedical) {
		this.dossierMedical = dossierMedical;
	}

	public Long getNumeroAssure() {
		return numeroAssure;
	}

	public void setNumeroAssure(Long numeroAssure) {
		this.numeroAssure = numeroAssure;
	}
	
}
