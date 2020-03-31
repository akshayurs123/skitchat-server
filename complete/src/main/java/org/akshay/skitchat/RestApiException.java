package org.akshay.skitchat;

public class RestApiException extends RuntimeException{

	int statusCode;

	public RestApiException(int statusCode) {
		super();
		this.statusCode = statusCode;
	}

	public RestApiException(String message, int statusCode) {
		super(message);
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	
	
	
}
