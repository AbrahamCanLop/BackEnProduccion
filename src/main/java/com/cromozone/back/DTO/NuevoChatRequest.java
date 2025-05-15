package com.cromozone.back.DTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NuevoChatRequest {
    private Long receptorId;
    private Long emisorId;
}
