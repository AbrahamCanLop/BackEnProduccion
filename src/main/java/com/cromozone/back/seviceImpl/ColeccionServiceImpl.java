package com.cromozone.back.seviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.cromozone.back.DTO.ColeccionDetalleDto;
import com.cromozone.back.DTO.ColeccionDetalleRequestDto;
import com.cromozone.back.DTO.ColeccionListaDto;
import com.cromozone.back.DTO.ColeccionNuevaRequest;
import com.cromozone.back.DTO.CromoUsuarioDto;
import com.cromozone.back.entity.Coleccion;
import com.cromozone.back.entity.Temporada;
import com.cromozone.back.entity.Usuario;
import com.cromozone.back.repository.ColeccionRepository;
import com.cromozone.back.repository.TemporadaRepository;
import com.cromozone.back.repository.UsuarioRepository;
import com.cromozone.back.service.ColeccionService;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ColeccionServiceImpl implements ColeccionService {

    private final ColeccionRepository coleccionRepository;
    private final TemporadaRepository temporadaRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public List<ColeccionListaDto> obtenerColeccionesDelUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
        return coleccionRepository.findByUsuario(usuario).stream()
                .map(c -> ColeccionListaDto.builder()
                        .id(c.getId())
                        .nombreTemporada(c.getTemporada().getNombre())
                        .anio(c.getTemporada().getAnio())
                        .imagenPortada(c.getTemporada().getImagenPortadaUrl())
                        .nombreCustom(c.getNombreCustom())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void crearColeccion(ColeccionNuevaRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        Temporada temporada = temporadaRepository.findById(request.getTemporadaId())
                .orElseThrow(() -> new IllegalArgumentException("Temporada no encontrada"));

        boolean yaExiste = coleccionRepository.findByUsuarioAndTemporada(usuario, temporada).isPresent();
        if (yaExiste)
            throw new IllegalStateException("Ya tienes esta colección");

        Coleccion nueva = Coleccion.builder()
                .usuario(usuario)
                .temporada(temporada)
                .nombreCustom(request.getNombreCustom())
                .build();

        coleccionRepository.save(nueva);
    }

    @Override
    public ColeccionDetalleDto obtenerDetalleColeccion(ColeccionDetalleRequestDto request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
        Coleccion coleccion = coleccionRepository.findById(request.getColeccionId())
                .orElseThrow(() -> new NoSuchElementException("Colección no encontrada"));

        if (!coleccion.getUsuario().getId().equals(usuario.getId()))
            throw new SecurityException("No puedes acceder a esta colección");

        List<CromoUsuarioDto> tengo = new ArrayList<>();
        List<CromoUsuarioDto> repetidos = new ArrayList<>();
        List<CromoUsuarioDto> wishlist = new ArrayList<>();

        coleccion.getCromosUsuario().forEach(c -> {
            CromoUsuarioDto dto = CromoUsuarioDto.builder()
                    .cromoId(c.getCromo().getId())
                    .numero(c.getCromo().getNumero())
                    .nombreJugador(c.getCromo().getNombreJugador())
                    .equipo(c.getCromo().getEquipo())
                    .posicion(c.getCromo().getPosicion())
                    .imagenUrl(c.getCromo().getImagenUrl())
                    .estado(c.getEstado().name().toLowerCase())
                    .cantidad(c.getCantidad())
                    .build();

            switch (c.getEstado()) {
                case TENGO -> tengo.add(dto);
                case REPETIDO -> repetidos.add(dto);
                case WISHLIST -> wishlist.add(dto);
            }
        });

        return ColeccionDetalleDto.builder()
                .id(coleccion.getId())
                .nombreTemporada(coleccion.getTemporada().getNombre())
                .nombreCustom(coleccion.getNombreCustom())
                .tengo(tengo)
                .repetidos(repetidos)
                .wishlist(wishlist)
                .build();
    }

    @Override
    public void eliminarColeccion(ColeccionDetalleRequestDto request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
        Coleccion coleccion = coleccionRepository.findById(request.getColeccionId())
                .orElseThrow(() -> new NoSuchElementException("Colección no encontrada"));

        if (!coleccion.getUsuario().getId().equals(usuario.getId()))
            throw new SecurityException("No puedes eliminar esta colección");

        coleccionRepository.delete(coleccion);
    }

}
