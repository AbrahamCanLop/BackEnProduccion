package com.cromozone.back.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CromoAdminRequest {

    private int numero;
    private String nombre;
    private String equipo;
    private String posicion;
    private String imagenUrl;
}
