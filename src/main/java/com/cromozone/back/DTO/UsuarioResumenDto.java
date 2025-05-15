package com.cromozone.back.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResumenDto {
    private Long id;
    private String nombreUsuario;
    private String fotoPerfilUrl;
    private String ubicacion;
}
