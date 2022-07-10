package uz.online.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import uz.online.payload.ApiResponse;
import uz.online.payload.RegisterDto;
import uz.online.payload.UserDto;
import uz.online.service.AuthService;
import uz.online.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService userService;
	
	
	@PreAuthorize(value = "hasAuthority('ADD_USER')")
	@PostMapping
	public HttpEntity<?> addUserToRole(@Valid @RequestBody UserDto userDto ){
		ApiResponse apiResponse=userService.addUser(userDto);
		return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
	}
	
	
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public Map<String, String> handleValidationExceptions(
//	  MethodArgumentNotValidException ex) {
//	    Map<String, String> errors = new HashMap<>();
//	    ex.getBindingResult().getAllErrors().forEach((error) -> {
//	        String fieldName = ((FieldError) error).getField();
//	        String errorMessage = error.getDefaultMessage();
//	        errors.put(fieldName, errorMessage);
//	    });
//	    return errors;
//	}
}
