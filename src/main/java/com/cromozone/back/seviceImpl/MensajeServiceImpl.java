package com.cromozone.back.seviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.cromozone.back.DTO.MensajeDto;
import com.cromozone.back.DTO.MensajeRequest;
import com.cromozone.back.entity.Chat;
import com.cromozone.back.entity.Mensaje;
import com.cromozone.back.entity.Usuario;
import com.cromozone.back.repository.ChatRepository;
import com.cromozone.back.repository.MensajeRepository;
import com.cromozone.back.repository.UsuarioRepository;
import com.cromozone.back.service.MensajeService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MensajeServiceImpl implements MensajeService {

    private final MensajeRepository mensajeRepository;
    private final ChatRepository chatRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public void enviarMensaje(MensajeRequest request) {
        Usuario remitente = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
        Chat chat = chatRepository.findById(request.getChatId())
                .orElseThrow(() -> new IllegalArgumentException("Chat no encontrado"));

        Mensaje mensaje = Mensaje.builder()
                .chat(chat)
                .remitente(remitente)
                .contenido(request.getContenido())
                .fechaEnvio(LocalDateTime.now())
                .build();

        mensajeRepository.save(mensaje);
    }

    @Override
    public List<MensajeDto> obtenerMensajesDeChat(Long chatId) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new IllegalArgumentException("Chat no encontrado"));

        return mensajeRepository.findByChatOrderByFechaEnvioAsc(chat)
                .stream()
                .map(m -> MensajeDto.builder()
                        .id(m.getId())
                        .remitenteId(m.getRemitente().getId())
                        .contenido(m.getContenido())
                        .fechaEnvio(m.getFechaEnvio())
                        .build())
                .collect(Collectors.toList());
    }

}