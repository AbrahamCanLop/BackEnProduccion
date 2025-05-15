package com.cromozone.back.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_usuario", nullable = false, unique = true)
    private String nombreUsuario;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "contrasena_hash", nullable = false)
    private String contrasenaHash;

    @Column(name = "foto_perfil_url")
    private String fotoPerfilUrl;

    private String ubicacion;

    private String bio;

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    // --- Relaciones ---

    // Un usuario tiene muchas colecciones
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Coleccion> colecciones;

    // Chats donde este usuario es el iniciador
    @OneToMany(mappedBy = "usuario1", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chat> chatsIniciados;

    // Chats donde este usuario es el receptor
    @OneToMany(mappedBy = "usuario2", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chat> chatsRecibidos;

    // Mensajes enviados por el usuario
    @OneToMany(mappedBy = "remitente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mensaje> mensajes;

    // Cromos que el usuario tiene, desea o repite
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CromoUsuario> cromosUsuario;

}