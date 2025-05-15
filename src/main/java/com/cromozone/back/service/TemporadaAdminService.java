package com.cromozone.back.service;

import com.cromozone.back.DTO.TemporadaRequest;
import com.cromozone.back.DTO.TemporadaResponse;

import java.util.List;

public interface TemporadaAdminService {

    // GET /admin/temporadas
    List<TemporadaResponse> listarTemporadas();

    // POST /admin/temporadas
    void crearTemporada(TemporadaRequest request);

    // PUT /admin/temporadas/:id
    void actualizarTemporada(Long id, TemporadaRequest request);

    // DELETE /admin/temporadas/:id
    void eliminarTemporada(Long id);
}
