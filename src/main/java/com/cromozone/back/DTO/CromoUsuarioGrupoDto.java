package com.cromozone.back.DTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CromoUsuarioGrupoDto {
    private List<CromoUsuarioDto> tengo;
    private List<CromoUsuarioDto> repetidos;
    private List<CromoUsuarioDto> wishlist;
}

