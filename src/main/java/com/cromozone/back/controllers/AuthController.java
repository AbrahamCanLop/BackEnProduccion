package com.cromozone.back.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cromozone.back.DTO.LoginRequest;
import com.cromozone.back.DTO.LoginResponse;
import com.cromozone.back.DTO.PasswordForgotRequest;
import com.cromozone.back.DTO.PasswordResetRequest;
import com.cromozone.back.DTO.RegisterRequest;
import com.cromozone.back.service.AuthService;
import com.cromozone.back.service.PasswordRecoveryService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final PasswordRecoveryService passwordRecoveryService;

    @PostMapping("/register")
    public ResponseEntity<?> registrar(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("Usuario registrado correctamente");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request, HttpSession session) {
        LoginResponse response = authService.login(request);
        session.setAttribute("userId", response.getToken());
        return ResponseEntity.ok(response);
     
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        authService.logout(null); // no necesitas token si usas sesiones
        return ResponseEntity.ok("Sesión cerrada");
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> solicitarRecuperacion(@RequestBody PasswordForgotRequest request) {
        passwordRecoveryService.solicitarRecuperacion(request);
        return ResponseEntity.ok("Instrucciones enviadas por email (simulado)");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetearContrasena(@RequestBody PasswordResetRequest request) {
        passwordRecoveryService.resetearContrasena(request);
        return ResponseEntity.ok("Contraseña actualizada");
    }
}
