package co.edu.uceva.libroservice.delivery.rest;

import co.edu.uceva.libroservice.domain.exception.LibroNoEncontradoException;
import co.edu.uceva.libroservice.domain.exception.NoHayLibrosException;
import co.edu.uceva.libroservice.domain.exception.PaginaSinLibrosException;
import co.edu.uceva.libroservice.domain.exception.ValidationException;
import co.edu.uceva.libroservice.domain.model.Libro;
import co.edu.uceva.libroservice.domain.repository.ILibroRepository;
import co.edu.uceva.libroservice.domain.service.ILibroService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador REST para gestionar libros.
 */

@RestController
@RequestMapping("/api/v1/libro-service")
public class LibroRestController {

    private final ILibroService libroService;

    private static final String MENSAJE = "mensaje";
    private static final String LIBRO = "libro";
    private static final String LIBROS = "libros";

    public LibroRestController(ILibroService libroService) {
        this.libroService = libroService;
    }

    /**
     * Listar todos los libros.
     * @return Lista de libros o excepción si no hay libros.
     */
    @GetMapping("/libros")
    public ResponseEntity<Map<String, Object>> getLibros(){
        List<Libro> libros = libroService.findAll();
        if (libros.isEmpty()){
            throw new NoHayLibrosException();
        }
        Map<String, Object> response = new HashMap<>();
        response.put(LIBROS, libros);
        return ResponseEntity.ok(response);
    }

    /**
     * Listar libros con paginación.
     * @param page Número de página.
     * @return Página de libros o excepción si la página está vacía.
     */
    @GetMapping("/libros/page/{page}")
    public ResponseEntity<Object> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page,4);
        Page<Libro> libros = libroService.findAll(pageable);
        if (libros.isEmpty()){
            throw new PaginaSinLibrosException(page);
        }
        return ResponseEntity.ok(libros);
    }

    /**
     * Crear un nuevo libro.
     * @param libro Datos del libro a crear.
     * @param result Resultado de la validación.
     * @return Respuesta con el libro creado o error de validación.
     */
    @PostMapping("/libros")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody Libro libro, BindingResult result){
        if (result.hasErrors()){
            throw new ValidationException(result);
        }
        Map<String, Object> response = new HashMap<>();
        Libro nuevoLibro = libroService.save(libro);
        response.put(MENSAJE, "El libro ha sido creado con éxito!");
        response.put(LIBRO, nuevoLibro);
        return ResponseEntity.status(201).body(response); // Código 201: Created
    }

    /**
     * Actualizar un libro.
     * @param libro Datos del libro a actualizar.
     * @param result Resultado de la validación.
     * @return Respuesta con el libro actualizado o error de validación.
     */
    @PutMapping("/libros")
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody Libro libro, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }
        libroService.findById(libro.getId())
                .orElseThrow(() -> new LibroNoEncontradoException(libro.getId()));
        Map<String, Object> response = new HashMap<>();
        Libro libroActualizado = libroService.update(libro);
        response.put(MENSAJE, "El libro ha sido actualizado con éxito!");
        response.put(LIBRO, libroActualizado);
        return ResponseEntity.ok(response);
    }

    /**
     * Eliminar un libro.
     * @param libro Datos del libro a eliminar.
     * @return Respuesta con mensaje de éxito o excepción si el libro no se encuentra.
     */
    @DeleteMapping("/libros")
    public ResponseEntity<Map<String, Object>> delete(@RequestBody Libro libro){
        libroService.findById(libro.getId())
                .orElseThrow(() -> new LibroNoEncontradoException(libro.getId()));
        libroService.delete(libro);
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, "El libro ha sido eliminado con éxito!");
        response.put(LIBRO, null);
        return ResponseEntity.ok(response);
    }


    /**
     * Obtener un libro por su ID.
     * @param id ID del libro a buscar.
     * @return Respuesta con el libro encontrado o excepción si no se encuentra.
     */
    @GetMapping("/libros/{id}")
    public ResponseEntity<Map<String, Object>> getLibroById(@PathVariable Long id) {
        Libro libro = libroService.findById(id)
                .orElseThrow(() -> new LibroNoEncontradoException(id));
        Map<String, Object> response = new HashMap<>();
        response.put(LIBRO, libro);
        return ResponseEntity.ok(response);
    }
}
