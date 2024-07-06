package com.civa.futbolista_posicion.service.impl;

import com.civa.futbolista_posicion.dto.futbolistaPosicion.FutbolistaPosicionCreateRequestDTO;
import com.civa.futbolista_posicion.dto.futbolistaPosicion.FutbolistaPosicionResponseDTO;
import com.civa.futbolista_posicion.entity.FutbolistaEntity;
import com.civa.futbolista_posicion.entity.PosicionEntity;
import com.civa.futbolista_posicion.mapper.FutbolistaPosicionMapper;
import com.civa.futbolista_posicion.repository.FutbolistaRepository;
import com.civa.futbolista_posicion.repository.PosicionRepository;
import com.civa.futbolista_posicion.service.FutbolistaPosicionService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FutbolistaPosicionServiceImpl implements FutbolistaPosicionService {

    private final FutbolistaRepository futbolistaRepository;
    private final PosicionRepository posicionRepository;
    private final FutbolistaPosicionMapper futbolistaPosicionMapper;

    public FutbolistaPosicionServiceImpl(FutbolistaRepository futbolistaRepository, PosicionRepository posicionRepository, FutbolistaPosicionMapper futbolistaPosicionMapper) {
        this.futbolistaRepository = futbolistaRepository;
        this.posicionRepository = posicionRepository;
        this.futbolistaPosicionMapper = futbolistaPosicionMapper;
    }

    @Transactional
    @Override
    public FutbolistaPosicionCreateRequestDTO addFutbolistaPosicion(Set<UUID> idFutbolista, Set<UUID> idPosicion) {
        try {

            if (idFutbolista.isEmpty()) {
                throw new IllegalArgumentException("El conjunto de UUIDs de futbolistas está vacío");
            }
            if (idPosicion.isEmpty()) {
                throw new IllegalArgumentException("El conjunto de UUIDs de posiciones está vacío");
            }

            // Obtener los futbolistas por sus UUIDs
            List<FutbolistaEntity> futbolistas = futbolistaRepository.findAllById(idFutbolista);

            // Validar que se encontraron todos los futbolistas
            if (futbolistas.size() != idFutbolista.size()) {
                throw new RuntimeException("No se encontraron todos los futbolistas solicitados");
            }

            // Obtener las posiciones por sus UUIDs
            List<PosicionEntity> posiciones = posicionRepository.findAllById(idPosicion);

            // Validar que se encontraron todas las posiciones
            if (posiciones.size() != idPosicion.size()) {
                throw new RuntimeException("No se encontraron todas las posiciones solicitadas");
            }

            futbolistas.forEach(f -> Hibernate.initialize(f.getPosiciones()));

            // Validar y actualizar las relaciones
            for (FutbolistaEntity futbolistaEntity : futbolistas) {
                if (!futbolistaEntity.getStatus()) {
                    throw new RuntimeException("El futbolista " + futbolistaEntity.getIdFutbolista() + " no está activo");
                }
                for (PosicionEntity posicionEntity : posiciones) {
                    if (!posicionEntity.getStatus()) {
                        throw new RuntimeException("La posición " + posicionEntity.getIdPosicion() + " no está activa");
                    }
                    futbolistaEntity.getPosiciones().add(posicionEntity);
                }
            }

            // Guardar los cambios
            futbolistas = futbolistaRepository.saveAll(futbolistas);

            Set<FutbolistaEntity> futbolistaEntitySet = new HashSet<>(futbolistas);
            Set<PosicionEntity> posicionEntitySet = new HashSet<>(posiciones);

            return futbolistaPosicionMapper.toFutbolistaPosicionCreateRequestDTO(futbolistaEntitySet, posicionEntitySet);

        } catch (Exception ex) {
            log.error("Error al agregar la posicion al futbolista: {}", ex.getMessage());
            throw new RuntimeException("Error al agregar la posicion al futbolista: ".concat(ex.getMessage()));
        }
    }

    @Override
    public List<FutbolistaPosicionResponseDTO> getAllFutbolistasWithPosicion() {
        try {
            List<FutbolistaEntity> futbolistas = futbolistaRepository.findAll();
            return futbolistas.stream()
                    .map(futbolistaPosicionMapper::toFutbolistaPosicionResponseDTO)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Error al obtener los futbolistas con posiciones: {}", ex.getMessage());
            throw new RuntimeException("Error al obtener los futbolistas con posiciones: ".concat(ex.getMessage()));
        }
    }
}
