package com.cromozone.back.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cromozone.back.DTO.TemporadaRequest;
import com.cromozone.back.DTO.TemporadaResponse;
import com.cromozone.back.service.TemporadaAdminService;

import java.util.List;

@RestController
@RequestMapping("/admin/temporadas")
@RequiredArgsConstructor
public class AdminTemporadaController {

    private final TemporadaAdminService temporadaAdminService;

    @GetMapping
    public ResponseEntity<List<TemporadaResponse>> listarTemporadas() {
        return ResponseEntity.ok(temporadaAdminService.listarTemporadas());
    }

    @PostMapping
    public ResponseEntity<?> crearTemporada(@RequestBody TemporadaRequest request) {
        temporadaAdminService.crearTemporada(request);
        return ResponseEntity.ok("Temporada creada");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarTemporada(@PathVariable Long id, @RequestBody TemporadaRequest request) {
        temporadaAdminService.actualizarTemporada(id, request);
        return ResponseEntity.ok("Temporada actualizada");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTemporada(@PathVariable Long id) {
        temporadaAdminService.eliminarTemporada(id);
        return ResponseEntity.ok("Temporada eliminada");
    }
}
