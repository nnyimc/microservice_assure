package fr.nnyimc.microservice_assure;

//import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.context.ApplicationContext;
import org.springframework.cloud.openfeign.EnableFeignClients;

import fr.nnyimc.microservice_assure.dao.AssureRepository;
//import fr.nnyimc.microservice_assure.entities.Assure;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
@EnableFeignClients
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
			a1.setNom("Welsh");
			a1.setPrenom("Davif");
			a1.setNumeroAssure(75L);
			a1.setNumeroPersonne(20L);
			a1.setDossierMedical("");
			a1.setDateNaissance(new SimpleDateFormat("yyyy-MM-dd").parse("1987-05-11"));
			assureRepository.save(a1);
			
			Assure a2 = (Assure) Class.forName("fr.nnyimc.microservice_assure.entities.Assure").newInstance();
			a2.setNom("Béliveau");
			a2.setPrenom("Marcel");
			a2.setNumeroAssure(63L);
			a2.setNumeroPersonne(4L);
			a2.setDossierMedical("Diabète type 2");
			a2.setDateNaissance(new SimpleDateFormat("yyyy-MM-dd").parse("1960-02-14"));
			assureRepository.save(a2);
			
			Assure a3 = (Assure) Class.forName("fr.nnyimc.microservice_assure.entities.Assure").newInstance();
			a3.setNom("Mascarpone");
			a3.setPrenom("Bertille");
			a3.setNumeroAssure(230L);
			a3.setNumeroPersonne(104L);
			a3.setDossierMedical("Anorexie");
			a3.setDateNaissance(new SimpleDateFormat("yyyy-MM-dd").parse("2001-08-19"));
			assureRepository.save(a3);
		} catch (Exception e) {
			errorLogger("Error " + e.getMessage());
		}*/
		
	}

	/*
	private void errorLogger(String s) {
		log.info("\r\n------->" + s);
	}
	*/
}
