package fr.formation.eprint.exception;

public class FileStorageTooBigException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FileStorageTooBigException(String message) {
        super(message);
    }

    public FileStorageTooBigException(String message, Throwable cause) {
        super(message, cause);
    }
}