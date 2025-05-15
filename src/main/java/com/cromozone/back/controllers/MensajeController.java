package com.cromozone.back.controllers;

import com.cromozone.back.DTO.MensajeDto;
import com.cromozone.back.DTO.MensajeRequest;
import com.cromozone.back.service.MensajeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chats/{chatId}/mensajes")
@RequiredArgsConstructor
public class MensajeController {

    private final MensajeService mensajeService;

    @PostMapping
    public ResponseEntity<?> enviarMensaje(@RequestBody MensajeRequest request) {
        mensajeService.enviarMensaje(request);
        return ResponseEntity.ok("Mensaje enviado");
    }

    @GetMapping
    public ResponseEntity<List<MensajeDto>> listarMensajes(@PathVariable Long chatId) {
        return ResponseEntity.ok(mensajeService.obtenerMensajesDeChat(chatId));
    }
}
