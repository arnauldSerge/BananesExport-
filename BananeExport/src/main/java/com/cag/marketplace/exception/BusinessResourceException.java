package com.cag.marketplace.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BusinessResourceException extends RuntimeException  {
	
	 private static final long serialVersionUID = 1L;
	    private Long resourceId;
	    private String errorCode;
	    private HttpStatus status;
	    
	    private String[] messageParam = {};

	    public BusinessResourceException(String message,  String[] messageParam) {
	        super(message);
	        
	    }
	    /**
	     * 
	     * @param resourceId
	     * @param messageKey
	     */
	    public BusinessResourceException(Long resourceId, String messageKey,  String[] messageParam) {
	        super(messageKey);
	        this.resourceId = resourceId;
	    }
	    public BusinessResourceException(Long resourceId, String errorCode, String messageKey,  String[] messageParam) {
	        super(messageKey);
	        this.resourceId = resourceId;
	        this.errorCode = errorCode;
	    }
	    
	    public BusinessResourceException(String errorCode, String messageKey,  String[] messageParam) {
	        super(messageKey);
	        this.errorCode = errorCode;
	    }
	    
	    public BusinessResourceException(String errorCode, String messageKey, String[] messageParam, HttpStatus status) {
	        super(messageKey);
	        this.errorCode = errorCode;
	        this.status = status;
	        this.setMessageParam(messageParam);
	    }

	    public Long getResourceId() {
	        return resourceId;
	    }

	    public void setResourceId(Long resourceId) {
	        this.resourceId = resourceId;
	    }

	    public String getErrorCode() {
	        return errorCode;
	    }

	    public void setErrorCode(String errorCode) {
	        this.errorCode = errorCode;
	    }    
	    
	    public HttpStatus getStatus() {
	        return status;
	    }

	    public void setStatus(HttpStatus status) {
	        this.status = status;
	    }
		public String[] getMessageParam() {
			return messageParam;
		}
		public void setMessageParam(String[] messageParam) {
			this.messageParam = messageParam;
		}
}
