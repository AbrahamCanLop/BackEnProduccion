package com.cromozone.back.seviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cromozone.back.DTO.CromoAdminRequest;
import com.cromozone.back.DTO.CromoResponse;
import com.cromozone.back.entity.Cromo;
import com.cromozone.back.entity.Temporada;
import com.cromozone.back.repository.CromoRepository;
import com.cromozone.back.repository.TemporadaRepository;
import com.cromozone.back.service.CromoAdminService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CromoAdminServiceImpl implements CromoAdminService {

    private final TemporadaRepository temporadaRepository;
    private final CromoRepository cromoRepository;

    @Override
    public List<CromoResponse> listarCromosPorTemporada(Long temporadaId) {
        Temporada temporada = temporadaRepository.findById(temporadaId)
                .orElseThrow(() -> new NoSuchElementException("Temporada no encontrada"));

        return cromoRepository.findByTemporada(temporada).stream()
                .map(c -> CromoResponse.builder()
                        .id(c.getId())
                        .numero(c.getNumero())
                        .nombre(c.getNombreJugador())
                        .equipo(c.getEquipo())
                        .posicion(c.getPosicion())
                        .imagenUrl(c.getImagenUrl())
                        .temporadaId(temporada.getId())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void crearCromo(Long temporadaId, CromoAdminRequest request) {
        Temporada temporada = temporadaRepository.findById(temporadaId)
                .orElseThrow(() -> new NoSuchElementException("Temporada no encontrada"));

        Cromo nuevo = Cromo.builder()
                .temporada(temporada)
                .numero(request.getNumero())
                .nombreJugador(request.getNombre())
                .equipo(request.getEquipo())
                .posicion(request.getPosicion())
                .imagenUrl(request.getImagenUrl())
                .build();

        cromoRepository.save(nuevo);
    }

    @Override
    public void actualizarCromo(Long cromoId, CromoAdminRequest request) {
        Cromo cromo = cromoRepository.findById(cromoId)
                .orElseThrow(() -> new NoSuchElementException("Cromo no encontrado"));

        cromo.setNumero(request.getNumero());
        cromo.setNombreJugador(request.getNombre());
        cromo.setEquipo(request.getEquipo());
        cromo.setPosicion(request.getPosicion());
        cromo.setImagenUrl(request.getImagenUrl());

        cromoRepository.save(cromo);
    }

    @Override
    public void eliminarCromo(Long cromoId) {
        Cromo cromo = cromoRepository.findById(cromoId)
                .orElseThrow(() -> new NoSuchElementException("Cromo no encontrado"));

        boolean estaEnColecciones = !cromo.getCromosUsuario().isEmpty(); // relación con CromoUsuario
        if (estaEnColecciones) {
            throw new IllegalStateException("No se puede eliminar: el cromo ya está en colecciones de usuarios");
        }

        cromoRepository.delete(cromo);
    }

    @Override
    public void importarCromosDesdeArchivo(Long temporadaId, MultipartFile archivo) {
        Temporada temporada = temporadaRepository.findById(temporadaId)
                .orElseThrow(() -> new NoSuchElementException("Temporada no encontrada"));

        try (BufferedReader br = new BufferedReader(new InputStreamReader(archivo.getInputStream(), StandardCharsets.UTF_8))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length < 5) continue;

                int numero = Integer.parseInt(partes[0].trim());
                String nombre = partes[1].trim();
                String equipo = partes[2].trim();
                String posicion = partes[3].trim();
                String imagenUrl = partes[4].trim();

                Cromo cromo = Cromo.builder()
                        .temporada(temporada)
                        .numero(numero)
                        .nombreJugador(nombre)
                        .equipo(equipo)
                        .posicion(posicion)
                        .imagenUrl(imagenUrl)
                        .build();

                cromoRepository.save(cromo);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al importar cromos: " + e.getMessage());
        }
    }
}
