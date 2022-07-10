package uz.online.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import uz.online.aop.CheckPermission;
import uz.online.payload.ApiResponse;
import uz.online.payload.RegisterDto;
import uz.online.payload.RoleDto;
import uz.online.payload.UserDto;
import uz.online.service.AuthService;
import uz.online.service.RoleService;
import uz.online.service.UserService;
import uz.online.controller.RoleController;
import uz.online.exceptions.ForbiddenExeption;

@Slf4j
@RestController
@RequestMapping("/api/role")
public class RoleController {

	@Autowired
	RoleService roleService;
	
	//@PreAuthorize(value = "hasAuthority('ADD_ROLE')")
	@CheckPermission(value = "ADD_ROLE")
	@PostMapping
	public HttpEntity<?> register(@Valid @RequestBody RoleDto roleDto ){
		ApiResponse apiResponse=roleService.addRole(roleDto);
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
	
	@RestControllerAdvice()
	public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

		@ExceptionHandler(value = { ForbiddenExeption.class, IllegalStateException.class })
		protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
			String bodyOfResponse = "You are not allowed to execute";
			log.info("In RestResponseEntityExceptionHandler");
			return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
		}
	}
}
