package co.edu.uceva.libroservice.domain.service;

import co.edu.uceva.libroservice.domain.model.Libro;
import co.edu.uceva.libroservice.domain.repository.ILibroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Implementación de la interfaz ILibroService que proporciona servicios para gestionar libros.
 */

@Service
public class LibroServiceImpl implements ILibroService{

    ILibroRepository libroRepository;

    public LibroServiceImpl(ILibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    @Transactional
    public Libro save(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    @Transactional
    public void delete(Libro libro) {
        libroRepository.delete(libro);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Libro> findById(Long id) {
        return libroRepository.findById(id);
    }

    @Override
    @Transactional
    public Libro update(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Libro> findAll() {
        return libroRepository.findAll();
    }

    @Override
    public Page<Libro> findAll(Pageable pageable) {
        return libroRepository.findAll(pageable);
    }
}
