package fr.formation.eprint.exception;

import java.sql.SQLIntegrityConstraintViolationException;

public class DuplicateEntryException extends SQLIntegrityConstraintViolationException {
	private static final long serialVersionUID = 1L;

	public DuplicateEntryException(String message) {
		super(message);
	}

	public DuplicateEntryException(String message, Throwable cause) {
		super(message, cause);
	}
}
