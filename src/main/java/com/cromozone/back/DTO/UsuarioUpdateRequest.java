package com.cromozone.back.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioUpdateRequest {
    private String nombreUsuario;
    private String fotoPerfilUrl;
    private String ubicacion;
    private String bio;
    private Long usuarioId;
}
