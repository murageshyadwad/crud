package com.QoreMigration.Exceptionhandler;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;



@ControllerAdvice
public class GlobalExceptionhandler {

	 @ExceptionHandler(Exception.class )
	 public ResponseEntity<Object> handelSpecificException(Exception ex , WebRequest request){
		 String errorMessageDescription = ex.getLocalizedMessage();
		 if(errorMessageDescription == null) errorMessageDescription = ex.toString();
		 ErrorMessage errorMessage = new ErrorMessage(new Date(),errorMessageDescription);
		 
		 return new ResponseEntity<>(
				 errorMessage, new HttpHeaders(),HttpStatus.BAD_REQUEST);
	 }
}
