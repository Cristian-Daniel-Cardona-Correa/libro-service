package co.edu.uceva.libroservice.domain.exception;

/**
 * Excepción personalizada para indicar que un libro no fue encontrado.
 */

public class LibroNoEncontradoException extends RuntimeException {
    public LibroNoEncontradoException(Long id) {
        super("El libro con ID " + id + " no fue encontrado.");
    }
}
