package com.cromozone.back.DTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerfilPublicoDto {
    private Long id;
    private String nombreUsuario;
    private String fotoPerfilUrl;
    private String ubicacion;

    private List<CromoUsuarioDto> repetidos;
    private List<CromoUsuarioDto> wishlist;
}
