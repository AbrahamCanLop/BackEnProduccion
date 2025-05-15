package com.cromozone.back.repository;

import com.cromozone.back.entity.Usuario;
import com.cromozone.back.entity.Coleccion;
import com.cromozone.back.entity.Temporada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ColeccionRepository extends JpaRepository<Coleccion, Long> {

    // Buscar todas las colecciones de un usuario
    List<Coleccion> findByUsuario(Usuario usuario);

    // Buscar colecciones de un usuario por temporada
    Optional<Coleccion> findByUsuarioAndTemporada(Usuario usuario, Temporada temporada);

    // Buscar todas las colecciones de una temporada (útil si algún día quieres estadísticas globales)
    List<Coleccion> findByTemporada(Temporada temporada);

    boolean existsByTemporada(Temporada temporada);
}