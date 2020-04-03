package org.akshay.skitchat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.akshay.skitchat.RestApiException;
import org.akshay.skitchat.entity.ErrorResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GenricExceptionHandler {

@ExceptionHandler(RestApiException.class)
	public ResponseEntity<ErrorResponseEntity> handleRestApiException(HttpServletRequest httpServletRequest 
			, HttpServletResponse httpServletResponse ,RestApiException apiException ){

		ErrorResponseEntity errorResponse = new ErrorResponseEntity();
		errorResponse.setErrorMessage(apiException.getMessage());
		errorResponse.setErrorCode(apiException.getStatusCode());
		errorResponse.setErrorType(apiException.getErrorType());

		//return new ResponseEntity<ErrorResponse>(errorResponse,null,HttpStatus.valueOf(apiException.getStatusCode()));
		return new ResponseEntity<ErrorResponseEntity>(errorResponse,null,HttpStatus.valueOf(apiException.getStatusCode()));
		
	}
}
