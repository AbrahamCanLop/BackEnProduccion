package com.cromozone.back.seviceImpl;

import com.cromozone.back.DTO.LoginRequest;
import com.cromozone.back.DTO.LoginResponse;
import com.cromozone.back.DTO.RegisterRequest;
import com.cromozone.back.entity.Usuario;
import com.cromozone.back.repository.UsuarioRepository;
import com.cromozone.back.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail()) || usuarioRepository.existsByNombreUsuario(request.getNombreUsuario())) {
            throw new IllegalArgumentException("Email o nombre de usuario ya en uso");
        }

        Usuario nuevoUsuario = Usuario.builder()
                .email(request.getEmail())
                .nombreUsuario(request.getNombreUsuario())
                .contrasenaHash(passwordEncoder.encode(request.getContraseña()))
                .fechaRegistro(LocalDate.now())
                .build();

        usuarioRepository.save(nuevoUsuario);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Email no registrado"));

        if (!passwordEncoder.matches(request.getContraseña(), usuario.getContrasenaHash())) {
            throw new IllegalArgumentException("Contraseña incorrecta");
        }

        // Autenticar al usuario en el contexto de seguridad

        String authToken = "ATKCRZN";

        return new LoginResponse(authToken, request.getEmail());
    }

    @Override
    public void logout(String token) {
        // Spring gestiona el logout con POST /auth/logout (si está configurado)
    }
}
