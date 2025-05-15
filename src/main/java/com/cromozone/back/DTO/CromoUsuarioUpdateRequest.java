package com.cromozone.back.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CromoUsuarioUpdateRequest {
    private String estado;
    private int cantidad;
    private Long usuarioId;
}
