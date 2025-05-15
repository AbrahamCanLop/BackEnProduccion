package com.cromozone.back.service;

import com.cromozone.back.DTO.MensajeDto;
import com.cromozone.back.DTO.MensajeRequest;

import java.util.List;

public interface MensajeService {

    // POST /chats/:id/mensajes
    void enviarMensaje(MensajeRequest request);

    // GET /chats/:id/mensajes
    List<MensajeDto> obtenerMensajesDeChat(Long chatId);
}