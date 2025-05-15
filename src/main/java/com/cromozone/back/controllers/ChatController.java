package com.cromozone.back.controllers;

import com.cromozone.back.DTO.ChatDto;
import com.cromozone.back.DTO.ChatResumenDto;
import com.cromozone.back.DTO.NuevoChatRequest;
import com.cromozone.back.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chats")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<ChatDto> iniciarChat(@RequestBody NuevoChatRequest request) {
        return ResponseEntity.ok(chatService.iniciarChat(request));
    }

    @GetMapping
    public ResponseEntity<List<ChatResumenDto>> verMisChats(@RequestBody Long usuarioId) {
        return ResponseEntity.ok(chatService.obtenerChatsDelUsuario(usuarioId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> actualizarEstadoChat(@PathVariable Long id, @RequestBody String nuevoEstado) {
        chatService.actualizarEstadoChat(id, nuevoEstado);
        return ResponseEntity.ok("Estado del chat actualizado");
    }
}
