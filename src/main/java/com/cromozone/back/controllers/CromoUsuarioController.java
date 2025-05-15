
package com.cromozone.back.controllers;

import com.cromozone.back.DTO.CromoUsuarioCreateRequest;
import com.cromozone.back.DTO.CromoUsuarioEstadoRequest;
import com.cromozone.back.DTO.CromoUsuarioGrupoDto;
import com.cromozone.back.DTO.CromoUsuarioUpdateRequest;
import com.cromozone.back.DTO.CromosEnColeccionDto;
import com.cromozone.back.DTO.EliminarCromoRequestDto;
import com.cromozone.back.service.CromoUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/colecciones/{coleccionId}/cromos")
@RequiredArgsConstructor
public class CromoUsuarioController {

    private final CromoUsuarioService cromoUsuarioService;

    @GetMapping
    public ResponseEntity<CromoUsuarioGrupoDto> listarCromosPorEstado(@RequestBody CromosEnColeccionDto request) {
        return ResponseEntity.ok(cromoUsuarioService.obtenerCromosPorEstado(request));
    }

    @PostMapping
    public ResponseEntity<?> agregarCromo(@PathVariable Long coleccionId, @RequestBody CromoUsuarioCreateRequest request) {
        cromoUsuarioService.agregarCromoAColeccion(coleccionId, request);
        return ResponseEntity.ok("Cromo añadido a tu colección");
    }

    @PutMapping("/{cromoId}")
    public ResponseEntity<?> actualizarCromo(
            @PathVariable Long coleccionId,
            @PathVariable Long cromoId,
            @RequestBody CromoUsuarioUpdateRequest request) {
        cromoUsuarioService.actualizarEstadoYCantidad(coleccionId, cromoId, request);
        return ResponseEntity.ok("Cromo actualizado");
    }

    @PatchMapping("/{cromoId}/estado")
    public ResponseEntity<?> cambiarEstado(
            @PathVariable Long coleccionId,
            @PathVariable Long cromoId,
            @RequestBody CromoUsuarioEstadoRequest request) {
        cromoUsuarioService.cambiarSoloEstado(coleccionId, cromoId, request);
        return ResponseEntity.ok("Estado del cromo cambiado");
    }

    @DeleteMapping("/{cromoId}")
    public ResponseEntity<?> eliminarCromo(@RequestBody EliminarCromoRequestDto request) {
        cromoUsuarioService.eliminarCromoDeColeccion(request);
        return ResponseEntity.ok("Cromo eliminado de la colección");
    }
}
