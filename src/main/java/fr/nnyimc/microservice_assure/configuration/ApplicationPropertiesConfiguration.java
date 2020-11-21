package fr.nnyimc.microservice_assure.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

@Component
@ConfigurationProperties("fr.nnyimc.assur3000")
@Data @ToString
@RefreshScope
public class ApplicationPropertiesConfiguration {
	private int limiteNombreAssures;
	private String filterName;
	private String filteredValue;
	private String noAssureExceptionMessage;
	private String unfoundAssureId;
	private String unfoundAssureNumber;
	private String unfoundPersonNumber;
	private int allAssures;
	private int byId;
	private int byAssureNumber;
	private int byPersonneNumber;
	private int byAssureNameAndFirstname;
	private int byAssureName;
	private int byAssureBornBefore;
}
