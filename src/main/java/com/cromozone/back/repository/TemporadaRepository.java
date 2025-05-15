package com.cromozone.back.repository;

import com.cromozone.back.entity.Temporada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemporadaRepository extends JpaRepository<Temporada, Long> {

    // Buscar por nombre exacto (ej: "Mundial 2022")
    Temporada findByNombre(String nombre);

    // Buscar por año (útil para filtros)
    List<Temporada> findByAnio(int anio);

    // Buscar por fragmento del nombre (para buscador general)
    List<Temporada> findByNombreContainingIgnoreCase(String nombre);
}
