package com.cromozone.back.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.cromozone.back.DTO.CromoAdminRequest;
import com.cromozone.back.DTO.CromoResponse;
import com.cromozone.back.service.CromoAdminService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminCromoController {

    private final CromoAdminService cromoAdminService;

    @GetMapping("/admin/temporadas/{id}/cromos")
    public ResponseEntity<List<CromoResponse>> listarCromosPorTemporada(@PathVariable Long id) {
        return ResponseEntity.ok(cromoAdminService.listarCromosPorTemporada(id));
    }

    @PostMapping("/admin/temporadas/{id}/cromos")
    public ResponseEntity<?> crearCromo(@PathVariable Long id, @RequestBody CromoAdminRequest request) {
        cromoAdminService.crearCromo(id, request);
        return ResponseEntity.ok("Cromo creado");
    }

    @PutMapping("/admin/cromos/{id}")
    public ResponseEntity<?> editarCromo(@PathVariable Long id, @RequestBody CromoAdminRequest request) {
        cromoAdminService.actualizarCromo(id, request);
        return ResponseEntity.ok("Cromo actualizado");
    }

    @DeleteMapping("/admin/cromos/{id}")
    public ResponseEntity<?> eliminarCromo(@PathVariable Long id) {
        cromoAdminService.eliminarCromo(id);
        return ResponseEntity.ok("Cromo eliminado");
    }

    @PostMapping("/admin/temporadas/{id}/cromos/importar")
    public ResponseEntity<?> importarCromos(@PathVariable Long id, @RequestParam("archivo") MultipartFile archivo) {
        cromoAdminService.importarCromosDesdeArchivo(id, archivo);
        return ResponseEntity.ok("Importación completada");
    }
}
