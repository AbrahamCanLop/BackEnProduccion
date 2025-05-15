package com.cromozone.back.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioPerfilDto {
    private Long id;
    private String nombreUsuario;
    private String email;
    private String fotoPerfilUrl;
    private String ubicacion;
    private String bio;
}