package fr.nnyimc.microservice_assure.exceptions;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @ToString
public class AucunAssureException extends RuntimeException {
	String message;
}
