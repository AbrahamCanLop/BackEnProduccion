package com.cromozone.back.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CromoUsuarioCreateRequest {
    private Long cromoId;
    private String estado; // "tengo", "repetido", "wishlist"
    private int cantidad;
    private Long usuarioId;
}
