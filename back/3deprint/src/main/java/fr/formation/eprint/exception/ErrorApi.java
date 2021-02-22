package fr.formation.eprint.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ErrorApi {

	private LocalDateTime timestamp = LocalDateTime.now();

	private String message;

	private List<String> errors = new ArrayList<>();

	public ErrorApi(LocalDateTime timestamp, String message, List<String> errors) {
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

}
