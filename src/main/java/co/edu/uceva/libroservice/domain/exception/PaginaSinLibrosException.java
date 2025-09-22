package co.edu.uceva.libroservice.domain.exception;

/**
 * Excepción lanzada cuando no hay libros en la página solicitada.
 */

public class PaginaSinLibrosException extends RuntimeException {
    public PaginaSinLibrosException(int page) {
        super("No hay libros en la página solicitada: " + page);
    }
}
