package com.cromozone.back.seviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.cromozone.back.DTO.ChatDto;
import com.cromozone.back.DTO.ChatResumenDto;
import com.cromozone.back.DTO.NuevoChatRequest;
import com.cromozone.back.entity.Chat;
import com.cromozone.back.entity.Usuario;
import com.cromozone.back.repository.ChatRepository;
import com.cromozone.back.repository.UsuarioRepository;
import com.cromozone.back.service.ChatService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public ChatDto iniciarChat(NuevoChatRequest request) {
        Usuario actual = usuarioRepository.findById(request.getEmisorId())
                .orElseThrow(() -> new NoSuchElementException("Usuario emisor no encontrado"));
        Usuario receptor = usuarioRepository.findById(request.getReceptorId())
                .orElseThrow(() -> new NoSuchElementException("Usuario receptor no encontrado"));

        // Revisa si ya hay un chat entre ambos
        Chat chatExistente = chatRepository.findByUsuario1AndUsuario2(actual, receptor).orElse(null);

        if (chatExistente != null) {
            return new ChatDto(chatExistente.getId(), receptor.getId(), chatExistente.getEstado().name().toLowerCase());
        }

        Chat nuevo = Chat.builder()
                .usuario1(actual)
                .usuario2(receptor)
                .estado(Chat.EstadoChat.ACTIVO)
                .fechaInicio(java.time.LocalDateTime.now())
                .build();

        chatRepository.save(nuevo);

        return new ChatDto(nuevo.getId(), receptor.getId(), nuevo.getEstado().name().toLowerCase());
    }

    @Override
    public List<ChatResumenDto> obtenerChatsDelUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        List<Chat> chats = chatRepository.findByUsuario1OrUsuario2(usuario, usuario);

        return chats.stream().map(chat -> {
            Usuario otro = chat.getUsuario1().equals(usuario) ? chat.getUsuario2() : chat.getUsuario1();
            String ultimoMensaje = chat.getMensajes().isEmpty()
                    ? ""
                    : chat.getMensajes().get(chat.getMensajes().size() - 1).getContenido();

            return ChatResumenDto.builder()
                    .chatId(chat.getId())
                    .nombreUsuario(otro.getNombreUsuario())
                    .fotoPerfilUrl(otro.getFotoPerfilUrl())
                    .estado(chat.getEstado().name().toLowerCase())
                    .ultimoMensaje(ultimoMensaje)
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public void actualizarEstadoChat(Long chatId, String nuevoEstado) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new NoSuchElementException("Chat no encontrado"));
        chat.setEstado(Chat.EstadoChat.valueOf(nuevoEstado.toUpperCase()));
        chatRepository.save(chat);
    }

}