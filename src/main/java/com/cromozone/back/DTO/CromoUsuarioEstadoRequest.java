package com.cromozone.back.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CromoUsuarioEstadoRequest {
    private String nuevoEstado; // "tengo", "repetido", "wishlist"
    private Long usuarioId;
}
