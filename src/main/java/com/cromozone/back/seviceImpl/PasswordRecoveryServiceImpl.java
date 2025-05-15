package com.cromozone.back.seviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cromozone.back.DTO.PasswordForgotRequest;
import com.cromozone.back.DTO.PasswordResetRequest;
import com.cromozone.back.entity.Usuario;
import com.cromozone.back.repository.UsuarioRepository;
import com.cromozone.back.service.PasswordRecoveryService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordRecoveryServiceImpl implements PasswordRecoveryService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    // Simulación en memoria (usar BD o Redis en prod)
    private final Map<String, String> recoveryTokens = new HashMap<>();

    @Override
    public void solicitarRecuperacion(PasswordForgotRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Email no registrado"));

        String token = UUID.randomUUID().toString();
        recoveryTokens.put(token, usuario.getEmail());

        System.out.println("Token de recuperación (simulado): " + token);
        // Aquí iría lógica para enviar email real
    }

    @Override
    public void resetearContrasena(PasswordResetRequest request) {
        String email = recoveryTokens.get(request.getToken());
        if (email == null) throw new IllegalArgumentException("Token inválido o expirado");

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Usuario no encontrado"));

        usuario.setContrasenaHash(passwordEncoder.encode(request.getNuevaContraseña()));
        usuarioRepository.save(usuario);

        recoveryTokens.remove(request.getToken());
    }
}
