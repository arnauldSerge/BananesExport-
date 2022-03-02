package com.bananeexport.exception.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class BusinessResourceExceptionDTO implements Serializable {
	
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String errorMessage;
		private String requestURL;
		private HttpStatus status;
		private List<String> errors = new ArrayList<String>();
}
