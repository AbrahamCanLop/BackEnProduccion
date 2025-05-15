package com.cromozone.back.seviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.cromozone.back.DTO.TemporadaRequest;
import com.cromozone.back.DTO.TemporadaResponse;
import com.cromozone.back.entity.Temporada;
import com.cromozone.back.repository.ColeccionRepository;
import com.cromozone.back.repository.TemporadaRepository;
import com.cromozone.back.service.TemporadaAdminService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TemporadaAdminServiceImpl implements TemporadaAdminService {

    private final TemporadaRepository temporadaRepository;
    private final ColeccionRepository coleccionRepository;

    @Override
    public List<TemporadaResponse> listarTemporadas() {
        return temporadaRepository.findAll().stream()
                .map(t -> TemporadaResponse.builder()
                        .id(t.getId())
                        .nombre(t.getNombre())
                        .descripcion(t.getDescripcion())
                        .anio(t.getAnio())
                        .imagenUrl(t.getImagenPortadaUrl())
                        .activa(t.isActiva())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void crearTemporada(TemporadaRequest request) {
        Temporada t = Temporada.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .anio(request.getAnio())
                .imagenPortadaUrl(request.getImagenUrl())
                .activa(request.isActiva())
                .build();
        temporadaRepository.save(t);
    }

    @Override
    public void actualizarTemporada(Long id, TemporadaRequest request) {
        Temporada t = temporadaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Temporada no encontrada"));

        t.setNombre(request.getNombre());
        t.setDescripcion(request.getDescripcion());
        t.setAnio(request.getAnio());
        t.setImagenPortadaUrl(request.getImagenUrl());
        t.setActiva(request.isActiva());

        temporadaRepository.save(t);
    }

    @Override
    public void eliminarTemporada(Long id) {
        Temporada temporada = temporadaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Temporada no encontrada"));

        boolean tieneColecciones = coleccionRepository.existsByTemporada(temporada);
        if (tieneColecciones) throw new IllegalStateException("No puedes eliminar esta temporada, tiene colecciones activas");

        temporadaRepository.delete(temporada);
    }
}
