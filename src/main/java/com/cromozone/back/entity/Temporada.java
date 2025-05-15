package com.cromozone.back.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "temporadas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Temporada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private int anio;

    private String descripcion;

    @Column(name = "imagen_portada_url")
    private String imagenPortadaUrl;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    private boolean activa;

    // --- Relaciones ---

    // Lista de cromos que pertenecen a esta temporada
    @OneToMany(mappedBy = "temporada", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cromo> cromos;

    // Todas las colecciones de usuarios asociadas a esta temporada
    @OneToMany(mappedBy = "temporada", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Coleccion> colecciones;
}
