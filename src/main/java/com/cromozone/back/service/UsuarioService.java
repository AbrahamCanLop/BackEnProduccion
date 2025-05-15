package com.cromozone.back.service;

import com.cromozone.back.DTO.UsuarioPerfilDto;
import com.cromozone.back.DTO.UsuarioUpdateRequest;

public interface UsuarioService {

    UsuarioPerfilDto obtenerPerfilActual(Long usuarioId); // GET /usuarios/me

    void actualizarPerfil(UsuarioUpdateRequest request); // PUT /usuarios/me

    void eliminarCuenta(Long usuarioId); // DELETE /usuarios/me
}
