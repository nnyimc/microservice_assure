package fr.nnyimc.microservice_assure.exceptions.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import fr.nnyimc.microservice_assure.dao.AssureRepository;
import fr.nnyimc.microservice_assure.entities.Assure;
import org.springframework.stereotype.Service;

@Service
public class AssureServiceImplementation implements AssureService {
	private AssureRepository assureRepository;

	public AssureServiceImplementation(AssureRepository assureRepository) {
		this.assureRepository = assureRepository;
	}
	
	@PostConstruct
	public void init() {
	 /*Avoiding NPE*/
	}

	@Override
	public Assure ajouter(Assure assure) {
		return assureRepository.save(assure);
	}

	@Override
	public void supprimerAssure(Long id) {
		assureRepository.deleteById(id);
		
	}

	@Override
	public void modifier(Assure assure) {
		assureRepository.save(assure);
	}

	@Override
	public Iterable<Assure> listerTous() {
		return assureRepository.findAll();
	}

	@Override
	public Optional<Assure> findById(Long retrievedId) {
		return assureRepository.findById(retrievedId);
	}

	@Override
	public List<Assure> findByNumeroAssure(Long retrievedAssureNumber) {
		return assureRepository.findByNumeroAssure(retrievedAssureNumber);
	}

	@Override
	public List<Assure> findByNomAndPrenom(String retrievedName, String retrievedFirstname) {
		return assureRepository.findByNomAndPrenom(retrievedName, retrievedFirstname);
	}

	@Override
	public List<Assure> findByNomContaining(String retrievedName) {
		return assureRepository.findByNomContaining(retrievedName);
	}

	@Override
	public List<Assure> findByDateNaissanceBefore(Date retrievedDateNaissance) {
		return assureRepository.findByDateNaissanceBefore(retrievedDateNaissance);
	}

}
