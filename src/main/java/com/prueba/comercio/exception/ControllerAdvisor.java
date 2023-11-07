package com.prueba.comercio.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
	private static final String MESSAGE = "message";
	private static final String TIMESTAMP = "timestamp";

	@ExceptionHandler(ProductoNotFoundException.class)
	public ResponseEntity<Object> handleProductoNotFoundException(ProductoNotFoundException ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put(TIMESTAMP, LocalDateTime.now());
		body.put(MESSAGE, ex.getMessage());

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoFormatDataException.class)
	public ResponseEntity<Object> handleNoFormatDataException(NoFormatDataException ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put(TIMESTAMP, LocalDateTime.now());
		body.put(MESSAGE, ex.getMessage());

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<Object> handleNodataFoundException(NoDataFoundException ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put(TIMESTAMP, LocalDateTime.now());
		body.put(MESSAGE, ex.getMessage());

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
}
