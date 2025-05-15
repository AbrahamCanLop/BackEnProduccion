package com.cromozone.back.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TemporadaResponse {

    private Long id;
    private String nombre;
    private String descripcion;
    private int anio;
    private String imagenUrl;
    private boolean activa;
}