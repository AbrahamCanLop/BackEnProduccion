package com.cromozone.back.DTO;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MensajeDto {
    private Long id;
    private Long remitenteId;
    private String contenido;
    private LocalDateTime fechaEnvio;
}
