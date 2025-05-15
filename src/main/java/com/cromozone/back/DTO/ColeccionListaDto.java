package com.cromozone.back.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ColeccionListaDto {
    private Long id;
    private String nombreTemporada;
    private int anio;
    private String imagenPortada;
    private String nombreCustom;
}