package org.akshay.skitchat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.akshay.skitchat.ErrorResponse;
import org.akshay.skitchat.RestApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GenricExceptionHandler {

@ExceptionHandler(RestApiException.class)
	public ResponseEntity<ErrorResponse> handleRestApiException(HttpServletRequest httpServletRequest 
			, HttpServletResponse httpServletResponse ,RestApiException apiException ){

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorMessage(apiException.getMessage());

		//return new ResponseEntity<ErrorResponse>(errorResponse,null,HttpStatus.valueOf(apiException.getStatusCode()));
		return new ResponseEntity<ErrorResponse>(errorResponse,null,HttpStatus.valueOf(apiException.getStatusCode()));
		
	}
}
