package com.cromozone.back.service;

import org.springframework.stereotype.Service;

import com.cromozone.back.entity.Usuario;
import com.cromozone.back.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService{

    private final UsuarioRepository usuarioRepository;

    public Usuario loadUserByUsername(String username) throws Exception {
        return usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));
    }
}