package fr.nnyimc.microservice_assure.exceptions.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import fr.nnyimc.microservice_assure.entities.Assure;


public interface AssureService {
	public Assure ajouter(Assure assure);
	public void supprimerAssure(Long id);
	public void modifier(Assure assure);
	public Iterable<Assure> listerTous();
	public Optional<Assure> findById(Long retrievedId);
	public List<Assure> findByNumeroAssure(Long retrievedAssureNumber);
	public List<Assure> findByNomAndPrenom(String retrievedName, String retrievedFirstname);
	public List<Assure> findByNomContaining(String retrievedName);
	public List<Assure> findByDateNaissanceBefore(Date retrievedDateNaissance);
}
