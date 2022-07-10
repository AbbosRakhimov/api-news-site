package uz.online.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import uz.online.payload.ApiResponse;
import uz.online.payload.LoginDto;
import uz.online.payload.RegisterDto;
import uz.online.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AutController {

	@Autowired
	AuthService authService;
	
	
	@PostMapping("/register")
	public HttpEntity<?> register(@Valid @RequestBody RegisterDto registerDto ){
		ApiResponse apiResponse=authService.registerUser(registerDto);
		return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
	}
	
	@PostMapping("/login")
	public HttpEntity<?> register(@Valid @RequestBody LoginDto loginDto ){
		ApiResponse apiResponse=authService.loginUser(loginDto);
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
