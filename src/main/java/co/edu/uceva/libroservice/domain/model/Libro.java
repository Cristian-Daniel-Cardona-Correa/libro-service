package co.edu.uceva.libroservice.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Entidad que representa un libro.
 */

@Entity
@Data
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El titulo no puede estar vacio")
    @Size(min = 1, max = 255, message = "El titulo tiene que estar entre 1 y 255 caracteres")
    @Column(nullable = false)
    private String titulo;

    @NotEmpty(message = "El autor no puede estar vacio")
    @Size(min = 1, max = 40, message = "El autor tiene que estar entre 1 y 40 caracteres")
    @Column(nullable = false)
    private String autor;

    @NotEmpty(message = "La editorial no puede estar vacia")
    @Size(min = 1, max = 100, message = "La editorial tiene que estar entre 1 y 100 caracteres")
    @Column(nullable = false)
    private String editorial;

    @Column(nullable = false)
    private Integer anoPublicacion;
}
