package com.cromozone.back.repository;

import com.cromozone.back.entity.CromoUsuario;
import com.cromozone.back.entity.Usuario;
import com.cromozone.back.entity.Cromo;
import com.cromozone.back.entity.Coleccion;
import com.cromozone.back.entity.EstadoCromo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CromoUsuarioRepository extends JpaRepository<CromoUsuario, Long> {

    // Buscar todos los cromos de una colección específica
    List<CromoUsuario> findByColeccion(Coleccion coleccion);

    // Buscar por colección y estado (ej: todos los repetidos o wishlist)
    List<CromoUsuario> findByColeccionAndEstado(Coleccion coleccion, EstadoCromo estado);

    // Buscar por usuario y cromo específico
    Optional<CromoUsuario> findByUsuarioAndCromo(Usuario usuario, Cromo cromo);

    // Buscar usuarios que tengan un cromo en cierto estado (para intercambios)
    List<CromoUsuario> findByCromoAndEstado(Cromo cromo, com.cromozone.back.entity.CromoUsuario.EstadoCromo repetido);

    // Buscar todos los cromos de un usuario
    List<CromoUsuario> findByUsuario(Usuario usuario);
}
