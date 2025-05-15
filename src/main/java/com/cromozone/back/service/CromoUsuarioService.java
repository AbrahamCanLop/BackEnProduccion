package com.cromozone.back.service;

import com.cromozone.back.DTO.*;

public interface CromoUsuarioService {

    // GET /colecciones/:id/cromos
    CromoUsuarioGrupoDto obtenerCromosPorEstado(CromosEnColeccionDto request);

    // POST /colecciones/:id/cromos
    void agregarCromoAColeccion(Long coleccionId, CromoUsuarioCreateRequest request);

    // PUT /colecciones/:id/cromos/:cromo_id
    void actualizarEstadoYCantidad(Long coleccionId, Long cromoId, CromoUsuarioUpdateRequest request);

    // PATCH /colecciones/:id/cromos/:cromo_id/estado
    void cambiarSoloEstado(Long coleccionId, Long cromoId, CromoUsuarioEstadoRequest request);

    // DELETE /colecciones/:id/cromos/:cromo_id
    void eliminarCromoDeColeccion(EliminarCromoRequestDto request);
}
