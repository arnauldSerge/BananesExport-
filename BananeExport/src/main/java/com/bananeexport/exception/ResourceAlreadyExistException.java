package com.bananeexport.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ResourceAlreadyExistException extends BusinessResourceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String resourceName;  
	private Object fieldValue;
	private String fieldName;
	public ResourceAlreadyExistException(String resourceName) {
		super(String.format("Cette destinataire existe déja", resourceName,""),null);
		setStatus(HttpStatus.ACCEPTED);
		this.resourceName = resourceName;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public Object getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}
	
	
	

}
