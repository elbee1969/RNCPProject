package fr.formation.eprint.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ImageAlreadyExistExeption extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ImageAlreadyExistExeption(String message) {
        super(message);
    }

    public ImageAlreadyExistExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
