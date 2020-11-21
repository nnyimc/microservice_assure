package fr.nnyimc.microservice_assure.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.nnyimc.microservice_assure.entities.Assure;

public interface AssureRepository extends CrudRepository<Assure, Long>{
	List<Assure> findByNumeroAssure(Long retrievedAssureNumber);
	List<Assure> findByNumeroPersonne(Long retrievedPersonneNumber);
	List<Assure> findByNomAndPrenom(String retrievedName, String retrievedFirstname);
	List<Assure> findByNomContaining(String retrievedName);
	List<Assure> findByDateNaissanceBefore(Date retrievedDateNaissance);
}
