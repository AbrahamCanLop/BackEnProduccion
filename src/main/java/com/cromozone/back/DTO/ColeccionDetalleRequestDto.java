package com.cromozone.back.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ColeccionDetalleRequestDto {
    
    private Long coleccionId;
    private Long usuarioId;
}
