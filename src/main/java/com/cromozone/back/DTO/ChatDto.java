package com.cromozone.back.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatDto {
    private Long chatId;
    private Long receptorId;
    private String estado; // "ACTIVO", "CERRADO", "ARCHIVADO"
}
