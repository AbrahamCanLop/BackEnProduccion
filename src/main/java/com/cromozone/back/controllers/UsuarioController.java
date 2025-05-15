package com.cromozone.back.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cromozone.back.DTO.UsuarioPerfilDto;
import com.cromozone.back.DTO.UsuarioUpdateRequest;
import com.cromozone.back.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/me")
    public ResponseEntity<UsuarioPerfilDto> obtenerPerfil(Long usuarioId) {
        return ResponseEntity.ok(usuarioService.obtenerPerfilActual(usuarioId));
    }

    @PutMapping("/me")
    public ResponseEntity<?> actualizarPerfil(@RequestBody UsuarioUpdateRequest request) {
        usuarioService.actualizarPerfil(request);
        return ResponseEntity.ok("Perfil actualizado");
    }

    @DeleteMapping("/me")
    public ResponseEntity<?> eliminarCuenta(Long usuarioId) {
        usuarioService.eliminarCuenta(usuarioId);
        return ResponseEntity.ok("Cuenta eliminada");
    }
}