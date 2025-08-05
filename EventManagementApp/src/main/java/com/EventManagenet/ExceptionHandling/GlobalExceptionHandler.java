package com.EventManagenet.ExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.EventManagenet.dto.ResponseStructure;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleIdNotFoundException(IdNotFoundException exception) {
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setMessage("Failure");
		response.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoRecordFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleRecordNotFoundException(NoRecordFoundException exception) {
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setMessage("No record found");
		response.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.NOT_FOUND);
	}
}
