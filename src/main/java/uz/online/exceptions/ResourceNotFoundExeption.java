package uz.online.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;

@ResponseStatus(HttpStatus.NOT_FOUND)
@AllArgsConstructor
public class ResourceNotFoundExeption extends RuntimeException{

	private final String role;
	
	private final String name;
	
	private final Object object;
}
