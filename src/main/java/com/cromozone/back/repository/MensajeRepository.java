package com.cromozone.back.repository;

import com.cromozone.back.entity.Mensaje;
import com.cromozone.back.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {

    // Obtener todos los mensajes de un chat, ordenados por fecha
    List<Mensaje> findByChatOrderByFechaEnvioAsc(Chat chat);
}