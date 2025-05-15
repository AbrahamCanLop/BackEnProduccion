package com.cromozone.back.DTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ColeccionDetalleDto {
    private Long id;
    private String nombreTemporada;
    private String nombreCustom;

    private List<CromoUsuarioDto> tengo;
    private List<CromoUsuarioDto> repetidos;
    private List<CromoUsuarioDto> wishlist;
}