package com.cromozone.back.seviceImpl;

import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.cromozone.back.DTO.UsuarioPerfilDto;
import com.cromozone.back.DTO.UsuarioUpdateRequest;
import com.cromozone.back.entity.Usuario;
import com.cromozone.back.repository.UsuarioRepository;
import com.cromozone.back.service.UsuarioService;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UsuarioPerfilDto obtenerPerfilActual(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        return UsuarioPerfilDto.builder()
                .id(usuario.getId())
                .nombreUsuario(usuario.getNombreUsuario())
                .email(usuario.getEmail())
                .bio(usuario.getBio())
                .fotoPerfilUrl(usuario.getFotoPerfilUrl())
                .ubicacion(usuario.getUbicacion())
                .build();
    }

    @Override
    public void actualizarPerfil(UsuarioUpdateRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        if (request.getNombreUsuario() != null) usuario.setNombreUsuario(request.getNombreUsuario());
        if (request.getFotoPerfilUrl() != null) usuario.setFotoPerfilUrl(request.getFotoPerfilUrl());
        if (request.getBio() != null) usuario.setBio(request.getBio());
        if (request.getUbicacion() != null) usuario.setUbicacion(request.getUbicacion());

        usuarioRepository.save(usuario);
    }

    @Override
    public void eliminarCuenta(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
        usuarioRepository.delete(usuario);
    }
}
