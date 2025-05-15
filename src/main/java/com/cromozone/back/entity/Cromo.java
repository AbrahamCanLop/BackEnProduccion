package com.cromozone.back.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "cromos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cromo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int numero;

    @Column(name = "nombre_jugador", nullable = false)
    private String nombreJugador;

    @Column(nullable = false)
    private String equipo;

    @Column(nullable = false)
    private String posicion;

    @Column(name = "imagen_url")
    private String imagenUrl;

    // --- Relaciones ---

    // La temporada a la que pertenece este cromo
    @ManyToOne
    @JoinColumn(name = "temporada_id", nullable = false)
    private Temporada temporada;

    // Relación con CromoUsuario, ya que cada cromo puede ser poseído por varios usuarios
    @OneToMany(mappedBy = "cromo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CromoUsuario> cromosUsuario;
}