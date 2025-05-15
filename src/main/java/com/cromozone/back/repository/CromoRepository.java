package com.cromozone.back.repository;

import com.cromozone.back.entity.Cromo;
import com.cromozone.back.entity.Temporada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CromoRepository extends JpaRepository<Cromo, Long> {

    // Buscar todos los cromos de una temporada
    List<Cromo> findByTemporada(Temporada temporada);

    // Buscar cromo por número dentro de una temporada
    Optional<Cromo> findByNumeroAndTemporada(int numero, Temporada temporada);

    // Buscar cromos por nombre de jugador (útil para buscador)
    List<Cromo> findByNombreJugadorContainingIgnoreCase(String nombreJugador);

    // Buscar cromos por equipo
    List<Cromo> findByEquipoContainingIgnoreCase(String equipo);

    // Buscar cromos combinando criterios opcionales. EQUIPO SERA MEDIANTE UN SELECTOR VISUAL DONDE SE MOSTRARARN LOS ESCUDOS
    @Query("""
                SELECT c FROM Cromo c
                WHERE (:query IS NULL OR LOWER(c.nombreJugador) LIKE LOWER(CONCAT('%', :query, '%')))
                  AND (:equipo IS NULL OR c.equipo = :equipo)
                  AND (:temporadaId IS NULL OR c.temporada.id = :temporadaId)
                  AND (:numero IS NULL OR c.numero = :numero)
            """)
    List<Cromo> buscarCromos(String query, Long temporadaId, String equipo, Integer numero);

}