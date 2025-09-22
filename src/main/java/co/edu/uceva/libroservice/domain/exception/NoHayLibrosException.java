package co.edu.uceva.libroservice.domain.exception;

/**
 * Excepción lanzada cuando no hay libros en la base de datos.
 */

public class NoHayLibrosException extends RuntimeException {
    public NoHayLibrosException() {
        super("No hay libros en la base de datos.");
    }
}
