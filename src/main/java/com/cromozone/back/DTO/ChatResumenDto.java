package com.cromozone.back.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatResumenDto {
    private Long chatId;
    private String nombreUsuario;
    private String fotoPerfilUrl;
    private String ultimoMensaje;
    private String estado;
}
