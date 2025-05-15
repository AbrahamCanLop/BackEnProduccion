package com.cromozone.back.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ColeccionNuevaRequest {
    private Long temporadaId;
    private String nombreCustom; // opcional
    private Long usuarioId;
}
