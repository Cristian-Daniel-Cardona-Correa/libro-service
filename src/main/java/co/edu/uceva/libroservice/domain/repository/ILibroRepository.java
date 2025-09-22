package co.edu.uceva.libroservice.domain.repository;

import co.edu.uceva.libroservice.domain.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad Libro.
 * Extiende JpaRepository para proporcionar operaciones CRUD y de paginación.
 */

public interface ILibroRepository extends JpaRepository<Libro, Long> {
}
