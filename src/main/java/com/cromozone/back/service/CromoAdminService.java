package com.cromozone.back.service;

import com.cromozone.back.DTO.CromoAdminRequest;
import com.cromozone.back.DTO.CromoResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CromoAdminService {

    // GET /admin/temporadas/:id/cromos
    List<CromoResponse> listarCromosPorTemporada(Long temporadaId);

    // POST /admin/temporadas/:id/cromos
    void crearCromo(Long temporadaId, CromoAdminRequest request);

    // PUT /admin/cromos/:id
    void actualizarCromo(Long cromoId, CromoAdminRequest request);

    // DELETE /admin/cromos/:id
    void eliminarCromo(Long cromoId);

    // POST /admin/temporadas/:id/cromos/importar
    void importarCromosDesdeArchivo(Long temporadaId, MultipartFile archivo);
}
