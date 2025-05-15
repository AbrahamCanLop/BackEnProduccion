package com.cromozone.back.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EliminarCromoRequestDto {

    private Long coleccionId;
    private Long cromoId;
    private Long usuarioId;
}
