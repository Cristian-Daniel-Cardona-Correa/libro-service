package co.edu.uceva.libroservice.delivery.exception;

import co.edu.uceva.libroservice.domain.exception.*;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manejador global de excepciones para la aplicación.
 * Captura y maneja diversas excepciones lanzadas en los controladores REST,
 * proporcionando respuestas HTTP adecuadas con mensajes de error detallados.
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Constantes para los mensajes de respuesta
    private static final String ERROR = "error";
    private static final String ERRORS = "errors";
    private static final String MENSAJE = "mensaje";
    private static final String LIBRO = "libro";
    private static final String LIBROS = "libros";
    private static final String STATUS = "status";


    /**
     * Manejador para la excepción PaginaSinLibrosException.
     * Se utiliza cuando una página solicitada no contiene libros.
     * @return ResponseEntity con el mensaje de error y estado 404
     */
    @ExceptionHandler(PaginaSinLibrosException.class)
    public ResponseEntity<Map<String, Object>> handlePaginaSinLibros(PaginaSinLibrosException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    /**
     * Manejador para la excepción IllegalArgumentException.
     * Se utiliza para manejar argumentos ilegales, como números de página negativos.
     * @return ResponseEntity con el mensaje de error y estado 400
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, "Número de página inválido.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    /**
     * Manejador para la excepción NoHayLibrosException.
     * Se utiliza cuando no hay libros en la base de datos.
     * @return ResponseEntity con el mensaje de error y estado 200
     */
    @ExceptionHandler(NoHayLibrosException.class)
    public ResponseEntity<Map<String, Object>> handleNoHayLibros(NoHayLibrosException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, "No hay libros en la base de datos.");
        response.put(LIBROS, null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Manejador para la excepción LibroNoEncontradoException.
     * Se utiliza cuando un libro con un ID específico no se encuentra en la base de datos
     * @return ResponseEntity con el mensaje de error y estado 404
     */
    @ExceptionHandler(LibroNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> handleLibroNoEncontrado(LibroNoEncontradoException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put(ERROR, ex.getMessage());
        response.put(STATUS, HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    /**
     * Manejador para la excepción LibroExistenteException.
     * Se utiliza cuando se intenta crear un libro que ya existe en la base de datos.
     * @return ResponseEntity con el mensaje de error y estado 400
     */
    @ExceptionHandler(LibroExistenteException.class)
    public ResponseEntity<Map<String, Object>> handleLibroExistente(LibroExistenteException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put(ERROR, ex.getMessage());
        response.put(STATUS, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Manejador para excepciones generales.
     * Captura cualquier excepción no manejada específicamente.
     * @return ResponseEntity con el mensaje de error y estado 500
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put(ERROR, "Error inesperado: " + ex.getMessage());
        response.put(STATUS, HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    /**
     * Manejador para excepciones de acceso a datos.
     * Captura excepciones relacionadas con problemas al interactuar con la base de datos.
     * @return ResponseEntity con el mensaje de error y estado 500
     */
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Map<String, Object>> handleDataAccessException(DataAccessException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put(ERROR, "Error de acceso a datos: " + ex.getMessage());
        response.put(STATUS, HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    /**
     * Manejador para la excepción ValidationException.
     * Se utiliza para manejar errores de validación de datos.
     * @return ResponseEntity con el mensaje de error, lista de errores y estado 400
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(ValidationException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put(ERROR, "Error de validación: " + ex.getMessage());
        response.put(STATUS, HttpStatus.BAD_REQUEST.value());

        List<String> errors = ex.result.getFieldErrors()
                .stream()
                .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                .toList();

        response.put(ERRORS, errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
