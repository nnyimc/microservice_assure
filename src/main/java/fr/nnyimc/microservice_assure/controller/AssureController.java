package fr.nnyimc.microservice_assure.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import org.springframework.format.annotation.DateTimeFormat;

import fr.nnyimc.microservice_assure.configuration.ApplicationPropertiesConfiguration;
import fr.nnyimc.microservice_assure.entities.Assure;
import fr.nnyimc.microservice_assure.exceptions.AssureIntrouvableException;
import fr.nnyimc.microservice_assure.exceptions.AucunAssureException;
import fr.nnyimc.microservice_assure.service.AssureService;
import fr.nnyimc.microservice_assure.service.AssureServiceImplementation;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(path="/assur3000")
public class AssureController {
	
	private AssureService assureService;
	private ApplicationPropertiesConfiguration appPropertiesConfig;
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	private Long retrievedAssureNumber, retrievedPersonneNumber, retrievedId;
	private String retrievedName, retrievedFirstname;
	private Date retrievedDateNaissance;
	
	public AssureController(
			AssureService assureService,
			ApplicationPropertiesConfiguration appPropertiesConfig
			) {
		this.assureService = assureService;
		this.appPropertiesConfig = appPropertiesConfig;
	}
	
	/**
	 * Méthode qui crée un Assuré basé sur les informations valides du formulaire
	 * @param assure
	 * @return
	 */
	@ApiOperation(value = "Ajout d'un assuré")
	@PostMapping(path="/ajouter-assure")
	public ResponseEntity<Void> creerAssure(@Valid @RequestBody Assure assure) {
		Assure assureAjoute = ((AssureServiceImplementation)assureService).ajouter(assure);
		actionLogger("Ajout d'un assuré");
	
		if (null == assure) {
			actionLogger("Echec de l'enregistrement de l'assuré.");
			return ResponseEntity
					.noContent()
					.build();
			}
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(assureAjoute.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}
	
	/**
	 * Méthode qui filtre les assurés avant de les renvoyer en réponse
	 * @return
	 */
	@ApiOperation(value = "Lister les assurés")
	@GetMapping(path="/listerLesAssures")
	public @ResponseBody MappingJacksonValue getAllAssures() {
		actionLogger("Recherche de tous les assurés effectuée");
		return buildFilteredList(((AssureServiceImplementation)assureService), appPropertiesConfig.getAllAssures());
		
	}
	
	/**
	 * Traitement d'une requête HTTP DELETE avec un id en paramètre
	 * qui provoque la suppression d'un assuré.
	 * @param id
	 */
	@ApiOperation(value = "Supprimer un assuré")
	@DeleteMapping(path="/assure/{id}")
	public void supprimerAssure(@PathVariable Long id) {
		actionLogger("Suppression de l'assuré: " + id);
		((AssureServiceImplementation)assureService).supprimerAssure(id);
	}
	
	/**
	 * Traitement d'une requête HTTP PUT avec un id en paramètre
	 * qui provoque la modifiction d'un assuré.
	 * @param assure
	 */
	@ApiOperation(value = "Modifier les informations d'un assuré")
	@PutMapping(path="/assure/{id}")
	public void modifierAssure(@RequestBody Assure assure) {
		actionLogger("Modification de l'assuré: " + assure);
		((AssureServiceImplementation)assureService).modifier(assure);
	}
	
	/**
	 * Traitement d'une requête HTTP GET avec pour paramètre l'identifiant de l'assuré
	 * qui retourne les assurés filtrés
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "Rechercher un assuré via son ID")
	@GetMapping(path="/assure/{id}")
	//public Optional<Assure> rechercherAssureId(@PathVariable Long id) {
	public MappingJacksonValue rechercherAssureId(@PathVariable Long id) {

		actionLogger("Recuperer Assuré via son Id: " + id);
		retrievedId = id;
		return buildFilteredList(assureService, appPropertiesConfig.getById());
	}
	
	
	
	
	/**
	 * Traitement d'une requête HTTP GET avec pour paramètres le numéro d'assuré
	 * qui retourne un assuré
	 * @param numeroAssure
	 * @return
	 */
	@ApiOperation(value = "Rechercher un assuré via son numéro d'assuré")
	@GetMapping(path="/assure/numero-assure/{numeroAssure}")
	public MappingJacksonValue listeAssures(@PathVariable Long numeroAssure) {
		actionLogger("Récupérer Assuré via un numéro d'assuré: " + numeroAssure);
		retrievedAssureNumber = numeroAssure;
		return buildFilteredList(assureService, appPropertiesConfig.getByAssureNumber());
	}
	
	/**
	 * Traitement d'une requête HTTP GET avec pour paramètre le numéro de personne
	 * qui retourne une liste d'assurés
	 * @param numeroPersonne
	 * @return
	 */
	@ApiOperation(value = "Rechercher un assuré via son numéro de personne")
	@GetMapping(path="/assure/numero-personne/{numeroPersonne}")
	public MappingJacksonValue listeAssuresParNumero(@PathVariable Long numeroPersonne) {
		actionLogger("Récuperer Assuré via son numéro de Personne: " + numeroPersonne);
		retrievedPersonneNumber = numeroPersonne;
		return buildFilteredList(assureService, appPropertiesConfig.getByPersonneNumber());
	}
	
	/**
	 * Traitement d'une requête HTTP GET avec pour paramètres le nom et le prénom
	 * qui retourne une liste d'assurés.
	 * @param nom
	 * @param prenom
	 * @return
	 */
	@ApiOperation(value = "Rechercher un assuré via son nom et prénom")
	@GetMapping(path="/assure/{nom}/{prenom}")
	public MappingJacksonValue listeAssuresNomPrenom(@PathVariable String nom, @PathVariable String prenom) {
		actionLogger("Récuperer Assuré via un nom: " + nom + " et un prenom: " + prenom) ;
		retrievedName = nom;
		retrievedFirstname = prenom;
		return buildFilteredList(assureService, appPropertiesConfig.getByAssureNameAndFirstname());
	}
	
	/**
	 * Traitement d'une requête HTTP GET avec un nom en paramètre
	 * qui retourne une liste d'assurés.
	 * @param nom
	 * @return
	 */
	@ApiOperation(value = "Rechercher un assuré via son nom")
	@GetMapping(path="/assure/nom/{nom}")
	public MappingJacksonValue listeAssuresNom(@PathVariable String nom) {
		actionLogger("Récupérer Assuré via un nom: " + nom);
		retrievedName = nom;
		return buildFilteredList(assureService, appPropertiesConfig.getByAssureNumber());
	}
	
	/**
	 * Traitement d'une requête HTTP GET avec une date en paramètre
	 * qui retourne une liste d'assurés.
	 * @param dateNaissance
	 * @return
	 */
	@ApiOperation(value = "Rechercher un assuré né avant une certaine date")
	@GetMapping(path="/assure/nes-avant/{dateNaissance}")
	public MappingJacksonValue listeAssuresNesAvant(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd")Date dateNaissance) {
		actionLogger("Récupérer les assurés nés avant une date");
		retrievedDateNaissance = dateNaissance;
		return buildFilteredList(assureService, appPropertiesConfig.getByAssureBornBefore());
	}
	
	private void actionLogger(String action) {
		log.info("\r\n------->"+  action);
	}
	
	/**
	 * Création d'un filtre dynamique
	 * @param nomFiltre
	 * @param attributFiltre
	 * @return
	 */
	private FilterProvider creerFiltre(String nomFiltre, String attributFiltre){
		SimpleBeanPropertyFilter unFiltre;
		if(null == attributFiltre) {
			unFiltre = SimpleBeanPropertyFilter.serializeAll();
		} else {
			unFiltre = SimpleBeanPropertyFilter.serializeAllExcept(attributFiltre);
		}
		return new SimpleFilterProvider().addFilter(nomFiltre, unFiltre);
	}
	
	/**
	 * Renvoi de la liste filtrée
	 * @param assures
	 * @param listeFiltres
	 * @return
	 */
	private MappingJacksonValue filtrerAssures(List<Assure> assures, FilterProvider listeFiltres) {
		MappingJacksonValue assuresFiltres = new MappingJacksonValue(assures);
		assuresFiltres.setFilters(listeFiltres);
		return assuresFiltres;
	}
	
	/**
	 * Méthode qui est basée sur la transmission d'un accès à la DAO
	 * en paramètre afin de retourner une liste filtrée
	 * @param assureService2
	 * @return
	 * @throws AucunAssureException 
	 */
	private MappingJacksonValue buildFilteredList(AssureService assureService, int mode) {
		FilterProvider listeFiltree = creerFiltre(appPropertiesConfig.getFilterName(), appPropertiesConfig.getFilteredValue());
		List<Assure> listeAssures = listLoader(assureService, mode);
		if(listeAssures.isEmpty()) throw new AucunAssureException(appPropertiesConfig.getNoAssureExceptionMessage());
		return filtrerAssures(listeAssures, listeFiltree);
	}

	
	private List<Assure> listLoader(AssureService assureService, int mode) {
		List<Assure> listeAssures = new ArrayList<Assure>();
		if (mode == appPropertiesConfig.getAllAssures()) {
			Iterable<Assure> assureIterable = assureService.listerTous();
			List<Assure> listeAssuresBrute = StreamSupport
											.stream(assureIterable.spliterator(), false)
											.collect(Collectors.toList());
			if(listeAssuresBrute.size() >= appPropertiesConfig.getLimiteNombreAssures()) {
				listeAssures = listeAssuresBrute.subList(
									0, appPropertiesConfig.getLimiteNombreAssures()
								);
			} else {
				listeAssures = listeAssuresBrute;
			}
		} else if (mode == appPropertiesConfig.getById()) {
			Optional<Assure> assure = assureService.findById(retrievedId);
			if(!assure.isPresent()) throw new AssureIntrouvableException(appPropertiesConfig.getUnfoundAssureId());
			Assure a = assure.get();
			listeAssures.add(a);
		} else if (mode == appPropertiesConfig.getByAssureNumber()) {
			listeAssures = assureService.findByNumeroAssure(retrievedAssureNumber);
			if(listeAssures.isEmpty()) throw new AssureIntrouvableException(appPropertiesConfig.getUnfoundAssureNumber());
		} else if (mode == appPropertiesConfig.getByPersonneNumber()) {
			listeAssures = assureService.findByNumeroAssure(retrievedPersonneNumber);
			if(listeAssures.isEmpty()) throw new AssureIntrouvableException(appPropertiesConfig.getUnfoundPersonNumber());
		} else if (mode == appPropertiesConfig.getByAssureNameAndFirstname()) {
			listeAssures = assureService.findByNomAndPrenom(retrievedName, retrievedFirstname);
		} else if (mode == appPropertiesConfig.getByAssureName()) {
			listeAssures = assureService.findByNomContaining(retrievedName);
		} else if (mode == appPropertiesConfig.getByAssureBornBefore()) {
			listeAssures = assureService.findByDateNaissanceBefore(retrievedDateNaissance);
		}
		return listeAssures;
	}
	
}
