package uz.online.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@ResponseStatus(HttpStatus.FORBIDDEN)
@Data
public class ForbiddenExeption extends RuntimeException {

	private String message;
	
	private String type;

	public ForbiddenExeption(String type, String message) {
		super();
		this.message = message;
		this.type = type;
	}


	
}
