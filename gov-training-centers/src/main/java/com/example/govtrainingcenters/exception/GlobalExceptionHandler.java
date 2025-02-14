package com.example.govtrainingcenters.exception;

import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.govtrainingcenters.response.pojo.ApiErrorResponse;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	//Validation exception handling
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiErrorResponse>handleValidationErros(
			MethodArgumentNotValidException ex , HttpServletRequest request){
		
		
		log.error("Validation error : {}", ex.getMessage());
		
		String ErrorMessage = ex.getBindingResult()
								.getFieldErrors()
								.stream()
								.map(error -> error.getField() + ":" + error.getDefaultMessage())
								.findFirst()
								.orElse("Validation failed");
		
		ApiErrorResponse errorResponse = new ApiErrorResponse(
				HttpStatus.BAD_REQUEST.value(),
				ErrorMessage,
				request.getRequestURI(),
				LocalDateTime.now()
				);
		
		
		return ResponseEntity.badRequest().body(errorResponse);
		
	}
	
	
	//Handling Entity not found format
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ApiErrorResponse>handleEntityNotFound(
			EntityNotFoundException ex , HttpServletRequest request){
		
		log.warn("Entity not found : {}" , ex.getMessage());
		
		ApiErrorResponse errorResponse = new ApiErrorResponse(
				HttpStatus.NOT_FOUND.value(),
				ex.getMessage(),
				request.getRequestURI(),
				LocalDateTime.now()
				);
		
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
	
	//Handling SQL Exception Format
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<ApiErrorResponse>handleSQLException(SQLException ex , HttpServletRequest request){
		
		log.error("Database error : {}", ex.getMessage());
		
		ApiErrorResponse errorResponse = new ApiErrorResponse(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Database error occurred. Please check your database configuration.",
				request.getRequestURI(),
				LocalDateTime.now()
				);
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}
	
	
	
	//Generic Exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiErrorResponse>handleGenericException(
			Exception ex , HttpServletRequest request){
		
		log.error("Unexpected error occurred" , ex);
		
		ApiErrorResponse errorResponse = new ApiErrorResponse(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"An unexpected error occurred. Please try again later.",
				request.getRequestURI(),
				LocalDateTime.now()
				);
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}
	
	
	
	

}
