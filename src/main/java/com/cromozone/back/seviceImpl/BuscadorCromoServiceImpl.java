package com.cromozone.back.seviceImpl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.cromozone.back.DTO.CromoBusquedaDto;
import com.cromozone.back.DTO.CromoDetalleDto;
import com.cromozone.back.DTO.UsuarioResumenDto;
import com.cromozone.back.entity.Cromo;
import com.cromozone.back.entity.CromoUsuario;
import com.cromozone.back.repository.CromoRepository;
import com.cromozone.back.repository.CromoUsuarioRepository;
import com.cromozone.back.service.BuscadorCromoService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BuscadorCromoServiceImpl implements BuscadorCromoService {

    private final CromoRepository cromoRepository;
    private final CromoUsuarioRepository cromoUsuarioRepository;

    @Override
    public List<CromoBusquedaDto> buscarCromos(String query, Long temporadaId, String equipo, Integer numero) {
        List<Cromo> resultados = cromoRepository.buscarCromos(query, temporadaId, equipo, numero);

        return resultados.stream()
                .map(c -> CromoBusquedaDto.builder()
                        .id(c.getId())
                        .numero(c.getNumero())
                        .nombreJugador(c.getNombreJugador())
                        .equipo(c.getEquipo())
                        .posicion(c.getPosicion())
                        .imagenUrl(c.getImagenUrl())
                        .nombreTemporada(c.getTemporada().getNombre())
                        .anioTemporada(c.getTemporada().getAnio())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public CromoDetalleDto obtenerDetalleCromo(Long cromoId) {
        Cromo c = cromoRepository.findById(cromoId)
                .orElseThrow(() -> new NoSuchElementException("Cromo no encontrado"));

        return CromoDetalleDto.builder()
                .id(c.getId())
                .numero(c.getNumero())
                .nombreJugador(c.getNombreJugador())
                .equipo(c.getEquipo())
                .posicion(c.getPosicion())
                .imagenUrl(c.getImagenUrl())
                .nombreTemporada(c.getTemporada().getNombre())
                .anioTemporada(c.getTemporada().getAnio())
                .build();
    }

    @Override
    public List<UsuarioResumenDto> obtenerUsuariosConCromoRepetido(Long cromoId) {
        Cromo cromo = cromoRepository.findById(cromoId)
                .orElseThrow(() -> new NoSuchElementException("Cromo no encontrado"));

        List<CromoUsuario> entradas = cromoUsuarioRepository.findByCromoAndEstado(
                cromo, CromoUsuario.EstadoCromo.REPETIDO
        );

        return entradas.stream()
                .map(CromoUsuario::getUsuario)
                .distinct()
                .map(u -> UsuarioResumenDto.builder()
                        .id(u.getId())
                        .nombreUsuario(u.getNombreUsuario())
                        .fotoPerfilUrl(u.getFotoPerfilUrl())
                        .ubicacion(u.getUbicacion())
                        .build())
                .collect(Collectors.toList());
    }
}