package com.bananeexport.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BusinessResourceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String resourceName;  
	private Object fieldValue;
	private String fieldName;
	public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
		super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue),null);
		setStatus(HttpStatus.NOT_FOUND);
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
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
