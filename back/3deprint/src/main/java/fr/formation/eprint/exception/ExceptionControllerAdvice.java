package fr.formation.eprint.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		ErrorApi errorApi = new ErrorApi("Resource not found", ex.getMessage());
		return handleExceptionInternal(ex, errorApi, null, HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(ImageAlreadyExistExeption.class)
	public ResponseEntity<Object> handleImageAlreadyExistException(ImageAlreadyExistExeption ex, WebRequest request) {
		ErrorApi errorApi = new ErrorApi("Cette image existe déjà!", ex.getMessage());
		return handleExceptionInternal(ex, errorApi, null, HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(DuplicateEntryException.class)
	public ResponseEntity<Object> handleUserAlreadyExistException(DuplicateEntryException ex, WebRequest request) {
		ErrorApi errorApi = new ErrorApi("Ce surnom existe déjà!", ex.getMessage());
		return handleExceptionInternal(ex, errorApi, null, HttpStatus.NOT_FOUND, request);
	}
}
