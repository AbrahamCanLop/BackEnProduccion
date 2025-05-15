package com.cromozone.back.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CromoDetalleDto {
    private Long id;
    private int numero;
    private String nombreJugador;
    private String equipo;
    private String posicion;
    private String imagenUrl;
    private String nombreTemporada;
    private int anioTemporada;
}