package com.guiPalma.cursomc.resourses.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.guiPalma.cursomc.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourseExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StardardError> objectNotFound(ObjectNotFoundException e,HttpServletRequest req){
		
		StardardError err = new StardardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	

}
