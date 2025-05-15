package com.cromozone.back.seviceImpl;

import com.cromozone.back.DTO.*;
import com.cromozone.back.entity.*;
import com.cromozone.back.repository.ColeccionRepository;
import com.cromozone.back.repository.CromoRepository;
import com.cromozone.back.repository.CromoUsuarioRepository;
import com.cromozone.back.repository.UsuarioRepository;
import com.cromozone.back.service.CromoUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CromoUsuarioServiceImpl implements CromoUsuarioService {

    private final ColeccionRepository coleccionRepository;
    private final CromoRepository cromoRepository;
    private final CromoUsuarioRepository cromoUsuarioRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public CromoUsuarioGrupoDto obtenerCromosPorEstado(CromosEnColeccionDto request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
        Coleccion coleccion = getColeccionSegura(request.getColeccionId(), usuario);

        var cromos = cromoUsuarioRepository.findByColeccion(coleccion);

        return CromoUsuarioGrupoDto.builder()
                .tengo(filtrarPorEstado(cromos, CromoUsuario.EstadoCromo.TENGO))
                .repetidos(filtrarPorEstado(cromos, CromoUsuario.EstadoCromo.REPETIDO))
                .wishlist(filtrarPorEstado(cromos, CromoUsuario.EstadoCromo.WISHLIST))
                .build();
    }

    @Override
    public void agregarCromoAColeccion(Long coleccionId, CromoUsuarioCreateRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
        Coleccion coleccion = getColeccionSegura(coleccionId, usuario);
        Cromo cromo = cromoRepository.findById(request.getCromoId())
                .orElseThrow(() -> new NoSuchElementException("Cromo no encontrado"));

        Optional<CromoUsuario> existente = cromoUsuarioRepository.findByUsuarioAndCromo(usuario, cromo);
        if (existente.isPresent())
            throw new IllegalStateException("Este cromo ya está en tu colección");

        CromoUsuario nuevo = CromoUsuario.builder()
                .usuario(usuario)
                .coleccion(coleccion)
                .cromo(cromo)
                .estado(CromoUsuario.EstadoCromo.valueOf(request.getEstado().toUpperCase()))
                .cantidad(request.getCantidad())
                .build();

        cromoUsuarioRepository.save(nuevo);
    }

    @Override
    public void actualizarEstadoYCantidad(Long coleccionId, Long cromoId, CromoUsuarioUpdateRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
        CromoUsuario cromoUsuario = getCromoUsuarioSegura(usuario, coleccionId, cromoId);

        cromoUsuario.setEstado(CromoUsuario.EstadoCromo.valueOf(request.getEstado().toUpperCase()));
        cromoUsuario.setCantidad(request.getCantidad());
        cromoUsuarioRepository.save(cromoUsuario);
    }

    @Override
    public void cambiarSoloEstado(Long coleccionId, Long cromoId, CromoUsuarioEstadoRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
        CromoUsuario cromoUsuario = getCromoUsuarioSegura(usuario, coleccionId, cromoId);

        cromoUsuario.setEstado(CromoUsuario.EstadoCromo.valueOf(request.getNuevoEstado().toUpperCase()));
        cromoUsuarioRepository.save(cromoUsuario);
    }

    @Override
    public void eliminarCromoDeColeccion(EliminarCromoRequestDto request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
        CromoUsuario cromoUsuario = getCromoUsuarioSegura(usuario, request.getColeccionId(), request.getCromoId());
        cromoUsuarioRepository.delete(cromoUsuario);
    }

    private List<CromoUsuarioDto> filtrarPorEstado(List<CromoUsuario> lista, CromoUsuario.EstadoCromo estado) {
        return lista.stream()
                .filter(c -> c.getEstado().equals(estado))
                .map(c -> CromoUsuarioDto.builder()
                        .cromoId(c.getCromo().getId())
                        .numero(c.getCromo().getNumero())
                        .nombreJugador(c.getCromo().getNombreJugador())
                        .equipo(c.getCromo().getEquipo())
                        .posicion(c.getCromo().getPosicion())
                        .imagenUrl(c.getCromo().getImagenUrl())
                        .estado(c.getEstado().name().toLowerCase())
                        .cantidad(c.getCantidad())
                        .build())
                .collect(Collectors.toList());
    }

    private Coleccion getColeccionSegura(Long coleccionId, Usuario usuario) {
        Coleccion coleccion = coleccionRepository.findById(coleccionId)
                .orElseThrow(() -> new NoSuchElementException("Colección no encontrada"));
        if (!coleccion.getUsuario().getId().equals(usuario.getId()))
            throw new SecurityException("No puedes acceder a esta colección");
        return coleccion;
    }

    private CromoUsuario getCromoUsuarioSegura(Usuario usuario, Long coleccionId, Long cromoId) {
        Coleccion coleccion = getColeccionSegura(coleccionId, usuario);
        return cromoUsuarioRepository.findByUsuarioAndCromo(usuario,
                        cromoRepository.findById(cromoId).orElseThrow())
                .filter(cu -> cu.getColeccion().getId().equals(coleccion.getId()))
                .orElseThrow(() -> new NoSuchElementException("Cromo no está en tu colección"));
    }
}
