package co.edu.uceva.libroservice.domain.service;

import co.edu.uceva.libroservice.domain.model.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define los servicios para gestionar libros.
 */

public interface ILibroService {
    Libro save(Libro libro);
    void delete(Libro libro);
    Optional<Libro> findById(Long id);
    Libro update(Libro libro);
    List<Libro> findAll();
    Page<Libro> findAll(Pageable pageable);
}
