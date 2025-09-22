package co.edu.uceva.libroservice.domain.exception;

import org.springframework.validation.BindingResult;

/**
 * Excepción personalizada para errores de validación.
 * Esta excepción se lanza cuando hay errores en la validación de datos.
 */

public class ValidationException extends RuntimeException {
    public final BindingResult result;
    public ValidationException(BindingResult result) {
        super("Error de validacion de datos.");
        this.result = result;
    }
}
