package com.cromozone.back.service;

import com.cromozone.back.DTO.PerfilPublicoDto;

public interface PerfilPublicoService {

    // GET /usuarios/:id/perfil-publico
    PerfilPublicoDto obtenerPerfilPublico(Long usuarioId);
}
