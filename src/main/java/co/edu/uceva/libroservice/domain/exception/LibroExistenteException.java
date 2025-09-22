package co.edu.uceva.libroservice.domain.exception;

/**
 * Excepción lanzada cuando se intenta crear un libro que ya existe.
 */

public class LibroExistenteException extends RuntimeException {
    public LibroExistenteException(String nombre) {
        super("El libro con nombre '" + nombre + "' ya existe.");
    }
}
