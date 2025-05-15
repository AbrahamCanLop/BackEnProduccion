package com.cromozone.back.service;

import com.cromozone.back.DTO.ChatResumenDto;
import com.cromozone.back.DTO.NuevoChatRequest;
import com.cromozone.back.DTO.ChatDto;

import java.util.List;

public interface ChatService {

    // POST /chats
    ChatDto iniciarChat(NuevoChatRequest request);

    // GET /chats
    List<ChatResumenDto> obtenerChatsDelUsuario(Long usuarioId);

    // PATCH /chats/:id
    void actualizarEstadoChat(Long chatId, String nuevoEstado);
}
