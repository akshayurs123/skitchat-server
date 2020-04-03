package org.akshay.skitchat;

public class RestApiException extends RuntimeException{

	int statusCode;
	String errorType;

	public RestApiException(int statusCode) {
		super();
		this.statusCode = statusCode;
	}

	public RestApiException(String message, int statusCode) {
		super(message);
		this.statusCode = statusCode;
	}
	
	public RestApiException(String message, int statusCode,String errorType) {
		super(message);
		this.statusCode = statusCode;
		this.errorType = errorType;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
	
	
	
	
	
}
