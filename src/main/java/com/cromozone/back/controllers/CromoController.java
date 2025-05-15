package com.cromozone.back.controllers;

import com.cromozone.back.DTO.CromoBusquedaDto;
import com.cromozone.back.DTO.CromoDetalleDto;
import com.cromozone.back.DTO.UsuarioResumenDto;
import com.cromozone.back.service.BuscadorCromoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cromos")
@RequiredArgsConstructor
public class CromoController {

    private final BuscadorCromoService buscadorCromoService;

    /**
     * Buscar cromos por nombre, equipo, número o temporada
     * Ejemplo: GET /cromos/search?q=ronaldo&temporadaId=2
     */
    @GetMapping("/search")
    public ResponseEntity<List<CromoBusquedaDto>> buscarCromos(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String equipo,
            @RequestParam(required = false) Integer numero,
            @RequestParam(required = false, name = "temporadaId") Long temporadaId) {

        List<CromoBusquedaDto> resultados = buscadorCromoService.buscarCromos(query, temporadaId, equipo, numero);
        return ResponseEntity.ok(resultados);
    }

    /**
     * Ver el detalle de un cromo
     */
    @GetMapping("/{id}")
    public ResponseEntity<CromoDetalleDto> verDetalleCromo(@PathVariable Long id) {
        return ResponseEntity.ok(buscadorCromoService.obtenerDetalleCromo(id));
    }

    /**
     * Ver usuarios que tienen el cromo como repetido
     */
    @GetMapping("/{id}/usuarios")
    public ResponseEntity<List<UsuarioResumenDto>> usuariosConCromoRepetido(@PathVariable Long id) {
        return ResponseEntity.ok(buscadorCromoService.obtenerUsuariosConCromoRepetido(id));
    }
}
