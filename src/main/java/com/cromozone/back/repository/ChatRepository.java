package com.cromozone.back.repository;

import com.cromozone.back.entity.Chat;
import com.cromozone.back.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    // Buscar todos los chats en los que participa un usuario (como usuario1 o usuario2)
    List<Chat> findByUsuario1OrUsuario2(Usuario usuario1, Usuario usuario2);

    // Buscar si ya existe un chat entre dos usuarios (para no duplicar)
    Optional<Chat> findByUsuario1AndUsuario2(Usuario usuario1, Usuario usuario2);

    Optional<Chat> findByUsuario2AndUsuario1(Usuario usuario1, Usuario usuario2);

    // Buscar todos los chats activos de un usuario
    List<Chat> findByEstadoAndUsuario1OrUsuario2(String estado, Usuario usuario1, Usuario usuario2);
}
