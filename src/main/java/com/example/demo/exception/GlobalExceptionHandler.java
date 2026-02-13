package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
	
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(BookNotFound.class)
	public ResponseEntity<?> handleBookNotFound(BookNotFound ex){
		
		ErrorResponse errorResp = new ErrorResponse(
				"Not Found",
				ex.getMessage(),
				404
				);
		
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(errorResp);
	}
	
	
	
	//@ControllerAdvice
	//public class GlobalExceptionHandler {
	//	
//		@ExceptionHandler(BookNotFound.class)
//		public ResponseEntity<String> handleBookNotFound(BookNotFound ex){
//			
//			return ResponseEntity
//					.status(HttpStatus.NOT_FOUND)
//					.body(ex.getMessage());
//		}
}
