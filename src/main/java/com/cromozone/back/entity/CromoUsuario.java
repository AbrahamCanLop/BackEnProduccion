package com.cromozone.back.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cromos_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CromoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cantidad;  // Cantidad de cromos que tiene el usuario (si es repetido)

    @Enumerated(EnumType.STRING)
    private EstadoCromo estado;

    // --- Relaciones ---

    // Usuario propietario del cromo
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Colección a la que pertenece este cromo
    @ManyToOne
    @JoinColumn(name = "coleccion_id", nullable = false)
    private Coleccion coleccion;

    // Cromo específico de la temporada
    @ManyToOne
    @JoinColumn(name = "cromo_id", nullable = false)
    private Cromo cromo;

    // --- Enumeración del estado del cromo ---
    public enum EstadoCromo {
        TENGO, REPETIDO, WISHLIST
    }
}
