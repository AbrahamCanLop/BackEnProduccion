package com.cromozone.back.DTO;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CromoUsuarioDto {
    private Long cromoId;
    private int numero;
    private String nombreJugador;
    private String equipo;
    private String posicion;
    private String imagenUrl;

    private String estado;
    private int cantidad;
}