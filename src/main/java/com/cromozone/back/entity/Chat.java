package com.cromozone.back.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "chats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime fechaInicio;

    @Enumerated(EnumType.STRING)
    private EstadoChat estado;

    // --- Relaciones ---

    // El primer usuario del chat
    @ManyToOne
    @JoinColumn(name = "usuario_1_id", nullable = false)
    private Usuario usuario1;

    // El segundo usuario del chat
    @ManyToOne
    @JoinColumn(name = "usuario_2_id", nullable = false)
    private Usuario usuario2;

    // Lista de mensajes en el chat
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mensaje> mensajes;

    // --- Enumeración del estado del chat ---
    public enum EstadoChat {
        ACTIVO, CERRADO
    }
}
