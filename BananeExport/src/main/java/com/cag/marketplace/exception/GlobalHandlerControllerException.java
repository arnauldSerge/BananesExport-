package com.cag.marketplace.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cag.marketplace.exception.dto.BusinessResourceExceptionDTO;


@RestControllerAdvice
public class GlobalHandlerControllerException extends ResponseEntityExceptionHandler {




	@ExceptionHandler(Exception.class)//toutes les autres erreurs non gérées par le service sont interceptées ici
	public ResponseEntity<BusinessResourceExceptionDTO> unknowError(HttpServletRequest req, Exception ex) {
		BusinessResourceExceptionDTO response = new BusinessResourceExceptionDTO();
		response.setErrorMessage(ex.getMessage());
		response.setRequestURL(req.getRequestURL().toString()); 
		return new ResponseEntity<BusinessResourceExceptionDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(BusinessResourceException.class)
	public ResponseEntity<BusinessResourceExceptionDTO> businessResourceError(HttpServletRequest req, BusinessResourceException ex) {
		BusinessResourceExceptionDTO response = new BusinessResourceExceptionDTO();
		response.setErrorMessage(ex.getMessage());
		response.setErrors(Arrays.asList(ex.getMessageParam()));
		response.setRequestURL(req.getRequestURL().toString()); 
		response.setStatus(ex.getStatus()!=null ? ex.getStatus():HttpStatus.BAD_REQUEST);
		return new ResponseEntity<BusinessResourceExceptionDTO>(response, HttpStatus.BAD_REQUEST  );     
	}


	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		//Get all errors
		List<BusinessResourceExceptionDTO> errors = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(x -> {
					BusinessResourceExceptionDTO response = new BusinessResourceExceptionDTO();
					response.setErrorMessage(ex.getMessage());
					response.getErrors().add("Validate Params errors");
					response.setStatus(HttpStatus.BAD_REQUEST);
					return response;
				})
				.collect(Collectors.toList());
		return new ResponseEntity<>(errors, status!=null? status : HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(
	  ConstraintViolationException ex, WebRequest request) {
	    List<String> errors = new ArrayList<String>();
	    for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
	        errors.add(violation.getRootBeanClass().getSimpleName() + "." + 
	          violation.getPropertyPath() + ": " +  violation.getMessage());
	    }

	    
	    BusinessResourceExceptionDTO response = new BusinessResourceExceptionDTO();
		response.setErrorMessage(ex.getMessage());
		response.setErrors(errors);
		response.setStatus(HttpStatus.BAD_REQUEST);
	    
	    
	    return new ResponseEntity<Object>(
	    		response, new HttpHeaders(), response.getStatus());
	}
	
	
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
	  MissingServletRequestParameterException ex, HttpHeaders headers, 
	  HttpStatus status, WebRequest request) {
	    String error = ex.getParameterName() + " n'a pas été renseigné";
	   
	    BusinessResourceExceptionDTO response = new BusinessResourceExceptionDTO();
		response.setErrorMessage(ex.getMessage());
		response.setErrors(Arrays.asList(error));
		response.setStatus(status);
	    
	    return new ResponseEntity<Object>(
	    		response, new HttpHeaders(), status);
	}
	

}
