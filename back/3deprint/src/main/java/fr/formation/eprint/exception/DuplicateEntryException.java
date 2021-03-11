package fr.formation.eprint.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class DuplicateEntryException extends DataIntegrityViolationException {
	private static final long serialVersionUID = 1L;

	public DuplicateEntryException(String message) {
		super(message);
	}

	public DuplicateEntryException(String message, Throwable cause) {
		super(message, cause);
	}
}
