package fr.formation.eprint.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ErrorApi {

	private LocalDateTime timestamp = LocalDateTime.now();

	private HttpStatus status;

	private String message;

	private List<String> errors = new ArrayList<>();

	public ErrorApi(HttpStatus status, LocalDateTime timestamp, String message, List<String> errors) {

		this.status = status;
		this.timestamp = timestamp;
		this.message = message;
		this.errors = errors;
	}

	public ErrorApi(String message, List<String> errors) {
		this.message = message;
		this.errors = errors;
	}

	public ErrorApi(String message) {
		this.message = message;
	}

	public ErrorApi(String message, String error) {
		this.message = message;
		this.errors = Arrays.asList(error);
	}

	public ErrorApi(HttpStatus status, String message, String error) {
		this.status = status;
		this.message = message;
		this.errors = Arrays.asList(error);
	}

	public ErrorApi(HttpStatus status, String message, List<String> errors) {
		this.status = status;
		this.message = message;
		this.errors = errors;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}
