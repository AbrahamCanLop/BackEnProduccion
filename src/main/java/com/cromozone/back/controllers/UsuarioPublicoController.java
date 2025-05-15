package com.cromozone.back.controllers;

import com.cromozone.back.DTO.PerfilPublicoDto;
import com.cromozone.back.DTO.UsuarioResumenDto;
import com.cromozone.back.service.BuscadorCromoService;
import com.cromozone.back.service.PerfilPublicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsuarioPublicoController {

    private final PerfilPublicoService perfilPublicoService;
    private final BuscadorCromoService buscadorCromoService;

    @GetMapping("/usuarios/{id}/perfil-publico")
    public ResponseEntity<PerfilPublicoDto> verPerfilPublico(@PathVariable Long id) {
        return ResponseEntity.ok(perfilPublicoService.obtenerPerfilPublico(id));
    }

    @GetMapping("/cromos/{id}/usuarios-repetido")
    public ResponseEntity<List<UsuarioResumenDto>> verUsuariosConRepetido(@PathVariable Long id) {
        return ResponseEntity.ok(buscadorCromoService.obtenerUsuariosConCromoRepetido(id));
    }
}
