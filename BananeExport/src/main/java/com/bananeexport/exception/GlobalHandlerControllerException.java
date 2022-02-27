package com.bananeexport.exception;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bananeexport.exception.dto.BusinessResourceExceptionDTO;


@RestControllerAdvice
public class GlobalHandlerControllerException extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;


	@ExceptionHandler(Exception.class)//toutes les autres erreurs non gérées par le service sont interceptées ici
	public ResponseEntity<BusinessResourceExceptionDTO> unknowError(HttpServletRequest req, Exception ex) {
		BusinessResourceExceptionDTO response = new BusinessResourceExceptionDTO();
		response.setErrorCode("Technical_Error");
		response.setErrorMessage((ex.getMessage()!=null && !ex.getMessage().isEmpty())? ex.getMessage()
				: ex.getCause().getMessage());
		response.setRequestURL(req.getRequestURL().toString()); 
		return new ResponseEntity<BusinessResourceExceptionDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(BusinessResourceException.class)
	public ResponseEntity<BusinessResourceExceptionDTO> businessResourceError(HttpServletRequest req, BusinessResourceException ex) {
		BusinessResourceExceptionDTO businessResourceExceptionDTO = new BusinessResourceExceptionDTO();
		businessResourceExceptionDTO.setStatus(ex.getStatus());
		businessResourceExceptionDTO.setErrorCode(ex.getErrorCode());
		businessResourceExceptionDTO.setErrorMessage(messageSource.getMessage(ex.getMessage(),null,ex.getMessage(), Locale.getDefault() ) );
		businessResourceExceptionDTO.setRequestURL(req.getRequestURL().toString()); 
		return new ResponseEntity<BusinessResourceExceptionDTO>(businessResourceExceptionDTO, ex.getStatus()!=null ? ex.getStatus():HttpStatus.BAD_REQUEST  );     
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
					BusinessResourceExceptionDTO businessResourceExceptionDTO = new BusinessResourceExceptionDTO();
					businessResourceExceptionDTO.setStatus(status);
					businessResourceExceptionDTO.setErrorCode("Validation_Error");
					businessResourceExceptionDTO.setErrorMessage(ex.getMessage());
					// businessResourceExceptionDTO.setRequestURL(request.); 
					return businessResourceExceptionDTO;
				})
				.collect(Collectors.toList());
		return new ResponseEntity<>(errors, status!=null? status : HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public void constraintViolationException(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value());
	}

}
