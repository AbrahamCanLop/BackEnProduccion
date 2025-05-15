
package com.cromozone.back.controllers;

import com.cromozone.back.DTO.ColeccionDetalleDto;
import com.cromozone.back.DTO.ColeccionDetalleRequestDto;
import com.cromozone.back.DTO.ColeccionListaDto;
import com.cromozone.back.DTO.ColeccionNuevaRequest;
import com.cromozone.back.service.ColeccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/colecciones")
@RequiredArgsConstructor
public class ColeccionController {

    private final ColeccionService coleccionService;

    @GetMapping
    public ResponseEntity<List<ColeccionListaDto>> listarMisColecciones(@RequestBody Long usuarioId) {
        return ResponseEntity.ok(coleccionService.obtenerColeccionesDelUsuario(usuarioId));
    }

    @PostMapping
    public ResponseEntity<?> crearColeccion(@RequestBody ColeccionNuevaRequest request) {
        coleccionService.crearColeccion(request);
        return ResponseEntity.ok("Colección creada correctamente");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColeccionDetalleDto> verDetalle(@RequestBody ColeccionDetalleRequestDto request) {
        return ResponseEntity.ok(coleccionService.obtenerDetalleColeccion(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarColeccion(@RequestBody ColeccionDetalleRequestDto request) {
        coleccionService.eliminarColeccion(request);
        return ResponseEntity.ok("Colección eliminada");
    }
}
