package com.cromozone.back.service;

import com.cromozone.back.DTO.ColeccionDetalleDto;
import com.cromozone.back.DTO.ColeccionDetalleRequestDto;
import com.cromozone.back.DTO.ColeccionListaDto;
import com.cromozone.back.DTO.ColeccionNuevaRequest;

import java.util.List;

public interface ColeccionService {

    // GET /colecciones
    List<ColeccionListaDto> obtenerColeccionesDelUsuario(Long usuarioId);

    // POST /colecciones
    void crearColeccion(ColeccionNuevaRequest request);

    // GET /colecciones/:id
    ColeccionDetalleDto obtenerDetalleColeccion(ColeccionDetalleRequestDto request);

    // DELETE /colecciones/:id
    void eliminarColeccion(ColeccionDetalleRequestDto request);
}