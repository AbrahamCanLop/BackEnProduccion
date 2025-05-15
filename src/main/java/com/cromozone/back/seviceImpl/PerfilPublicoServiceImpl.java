package com.cromozone.back.seviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.cromozone.back.DTO.CromoUsuarioDto;
import com.cromozone.back.DTO.PerfilPublicoDto;
import com.cromozone.back.entity.CromoUsuario;
import com.cromozone.back.entity.Usuario;
import com.cromozone.back.repository.CromoUsuarioRepository;
import com.cromozone.back.repository.UsuarioRepository;
import com.cromozone.back.service.PerfilPublicoService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PerfilPublicoServiceImpl implements PerfilPublicoService {

    private final UsuarioRepository usuarioRepository;
    private final CromoUsuarioRepository cromoUsuarioRepository;

    @Override
    public PerfilPublicoDto obtenerPerfilPublico(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        List<CromoUsuario> cromos = cromoUsuarioRepository.findByUsuario(usuario);

        List<CromoUsuarioDto> repetidos = cromos.stream()
                .filter(c -> c.getEstado().equals(CromoUsuario.EstadoCromo.REPETIDO))
                .map(this::mapCromo)
                .collect(Collectors.toList());

        List<CromoUsuarioDto> wishlist = cromos.stream()
                .filter(c -> c.getEstado().equals(CromoUsuario.EstadoCromo.WISHLIST))
                .map(this::mapCromo)
                .collect(Collectors.toList());

        return PerfilPublicoDto.builder()
                .id(usuario.getId())
                .nombreUsuario(usuario.getNombreUsuario())
                .fotoPerfilUrl(usuario.getFotoPerfilUrl())
                .ubicacion(usuario.getUbicacion())
                .repetidos(repetidos)
                .wishlist(wishlist)
                .build();
    }

    private CromoUsuarioDto mapCromo(CromoUsuario c) {
        return CromoUsuarioDto.builder()
                .cromoId(c.getCromo().getId())
                .numero(c.getCromo().getNumero())
                .nombreJugador(c.getCromo().getNombreJugador())
                .equipo(c.getCromo().getEquipo())
                .posicion(c.getCromo().getPosicion())
                .imagenUrl(c.getCromo().getImagenUrl())
                .estado(c.getEstado().name().toLowerCase())
                .cantidad(c.getCantidad())
                .build();
    }
}
