package fr.formation.eprint.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	public ResponseEntity<Object> handleEmailAlreadyExistException(DuplicateEntryException ex, WebRequest request) {
		ErrorApi errorApi = new ErrorApi("Ce mail existe déjà!", ex.getMessage());
		return handleExceptionInternal(ex, errorApi, null, HttpStatus.CONFLICT, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = new ArrayList<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getCode());
		}
		ErrorApi errorApi = new ErrorApi(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
		return handleExceptionInternal(ex, errorApi, headers, errorApi.getStatus(), request);
	}
}
