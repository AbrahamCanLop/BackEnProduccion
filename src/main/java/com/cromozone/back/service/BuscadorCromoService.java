package com.cromozone.back.service;

import com.cromozone.back.DTO.CromoDetalleDto;
import com.cromozone.back.DTO.CromoBusquedaDto;
import com.cromozone.back.DTO.UsuarioResumenDto;

import java.util.List;

public interface BuscadorCromoService {

    // GET /cromos/search
    List<CromoBusquedaDto> buscarCromos(String query, Long temporadaId, String equipo, Integer numero);

    // GET /cromos/:id
    CromoDetalleDto obtenerDetalleCromo(Long cromoId);

    // GET /cromos/:id/usuarios
    List<UsuarioResumenDto> obtenerUsuariosConCromoRepetido(Long cromoId);
}