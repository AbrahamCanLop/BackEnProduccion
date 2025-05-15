package com.cromozone.back.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MensajeRequest {
    private String contenido;
    private Long usuarioId;
    private Long chatId;
}
