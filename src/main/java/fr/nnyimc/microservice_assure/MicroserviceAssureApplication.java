package fr.nnyimc.microservice_assure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import fr.nnyimc.microservice_assure.dao.AssureRepository;

@SpringBootApplication
public class MicroserviceAssureApplication implements ApplicationRunner {
	private AssureRepository assureRepository;
	Logger log = LoggerFactory.getLogger(getClass());
	
	public MicroserviceAssureApplication(AssureRepository assureRepository) {
		this.assureRepository = assureRepository;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceAssureApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {
	/*	
		try {
			Assure a1 = (Assure) Class.forName("fr.nnyimc.microservice_assure.entities.Assure").newInstance();
			a1.setNom("Freeway");
			a1.setPrenom("Linda");
			a1.setNumeroAssure(120L);
			a1.setNumeroPersonne(23L);
			a1.setDossierMedical("Cancer");
			a1.setDateNaissance(new SimpleDateFormat("yyyy-MM-dd").parse("1983-02-18"));
			assureRepository.save(a1);
			
			Assure a2 = (Assure) Class.forName("fr.nnyimc.microservice_assure.entities.Assure").newInstance();
			a2.setNom("Cross");
			a2.setPrenom("Paul");
			a2.setNumeroAssure(100L);
			a2.setNumeroPersonne(13L);
			a2.setDossierMedical("");
			a2.setDateNaissance(new SimpleDateFormat("yyyy-MM-dd").parse("1983-02-18"));
			assureRepository.save(a2);
		} catch (Exception e) {
			errorLogger("Error " + e.getMessage());
		}
		*/
	}

	private void errorLogger(String s) {
		log.info("\r\n------->" + s);
	}
	
}
