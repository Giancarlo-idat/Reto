package com.civa.futbolista_posicion.service.impl;

import com.civa.futbolista_posicion.dto.posicion.PosicionCreateRequestDTO;
import com.civa.futbolista_posicion.dto.posicion.PosicionResponseDTO;
import com.civa.futbolista_posicion.entity.PosicionEntity;
import com.civa.futbolista_posicion.mapper.PosicionMapper;
import com.civa.futbolista_posicion.repository.PosicionRepository;
import com.civa.futbolista_posicion.service.PosicionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PosicionServiceImpl implements PosicionService {

    private final PosicionRepository posicionRepository;
    private final PosicionMapper posicionMapper;

    public PosicionServiceImpl(PosicionRepository posicionRepository, PosicionMapper posicionMapper) {
        this.posicionRepository = posicionRepository;
        this.posicionMapper = posicionMapper;
    }

    @Override
    public List<PosicionResponseDTO> findByNombre(String nombre) {
        try {
            return posicionRepository.findByNombre(nombre)
                    .stream()
                    .map(posicionMapper::toPosicionResponseDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("A ocurrido un error el nombre de la posición: {}", e.getMessage());
            throw new RuntimeException("A ocurrido un error el nombre de la posición".concat(e.getMessage()));
        }
    }

    @Override
    public List<PosicionResponseDTO> findAll() {
        try {
            return posicionRepository.findAll()
                    .stream()
                    .map(posicionMapper::toPosicionResponseDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("A ocurrido un error al obtener las posiciones: {}", e.getMessage());
            throw new RuntimeException("A ocurrido un error al obtener las posiciones: ".concat(e.getMessage()));
        }
    }

    @Override
    public Optional<PosicionResponseDTO> findById(UUID id) {
        try {
            PosicionEntity posicionEntity = posicionRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("No se ha encontrado la posición con el id: ".concat(id.toString())));

            return Optional.of(posicionMapper.toPosicionResponseDTO(posicionEntity));

        } catch (Exception e) {
            log.error("A ocurrido un error al obtener la posición por id: {}", e.getMessage());
            throw new RuntimeException("A ocurrido un error al obtener la posición por id: ".concat(e.getMessage()));
        }
    }

    @Override
    public PosicionCreateRequestDTO save(PosicionCreateRequestDTO posicionCreateRequestDTO) {
        try {

            if (posicionRepository.existsByNombre(posicionCreateRequestDTO.getNombre())) {
                throw new RuntimeException("Ya existe una posición con el nombre: ".concat(posicionCreateRequestDTO.getNombre()));
            }

            PosicionEntity posicionEntity = posicionMapper.toPosicionEntity(posicionCreateRequestDTO);

            posicionEntity = posicionRepository.save(posicionEntity);

            return posicionMapper.toPosicionCreateRequestDTO(posicionEntity);

        } catch (Exception ex) {
            log.error("Error al guardar la posición: {}", ex.getMessage());
            throw new RuntimeException("Error al guardar la posición: ".concat(ex.getMessage()));
        }
    }

    @Override
    public PosicionCreateRequestDTO update(PosicionCreateRequestDTO posicionCreateRequestDTO, UUID id) {
        try {

            PosicionEntity posicionEntity = posicionRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("No se ha encontrado la posición con el id: ".concat(id.toString())));

            if (posicionCreateRequestDTO.getIdPosicion() != null && !posicionCreateRequestDTO.getIdPosicion().equals(id)) {
                throw new RuntimeException("El id de la posición no coincide con el id proporcionado");
            }

            posicionEntity.setNombre(posicionCreateRequestDTO.getNombre());
            posicionEntity.setUpdatedAt(LocalDateTime.now());

            posicionEntity = posicionRepository.save(posicionEntity);

            return posicionMapper.toPosicionCreateRequestDTO(posicionEntity);


        } catch (Exception e) {
            log.error("A ocurrido un error al actualizar la posición: {}", e.getMessage());
            throw new RuntimeException("A ocurrido un error al actualizar la posición: ".concat(e.getMessage()));
        }
    }

    @Override
    public void disable(UUID id) {
        try {

            Optional<PosicionEntity> posicionEntity = posicionRepository.findById(id);
            if (posicionEntity.isPresent()) {
                PosicionEntity posicionId = posicionEntity.get();
                posicionId.setUpdatedAt(LocalDateTime.now());
                posicionId.setStatus(false);
                posicionRepository.save(posicionId);
            } else {
                throw new RuntimeException("No se ha encontrado la posición con el id: ".concat(id.toString()));
            }

        } catch (Exception e) {
            log.error("A ocurrido un error al deshabilitar la posición: {}", e.getMessage());
            throw new RuntimeException("A ocurrido un error al deshabilitar la posición: ".concat(e.getMessage()));
        }
    }

    @Override
    public PosicionResponseDTO enable(UUID id) {
        try {
            Optional<PosicionEntity> posicionEntity = posicionRepository.findById(id);
            if (posicionEntity.isPresent()) {
                PosicionEntity posicionId = posicionEntity.get();
                posicionId.setUpdatedAt(LocalDateTime.now());
                posicionId.setStatus(true);
                posicionRepository.save(posicionId);
                return posicionMapper.toPosicionResponseDTO(posicionId);
            } else {
                throw new RuntimeException("No se ha encontrado la posición con el id: ".concat(id.toString()));
            }

        } catch (Exception e) {
            log.error("A ocurrido un error al habilitar la posición: {}", e.getMessage());
            throw new RuntimeException("A ocurrido un error al habilitar la posición: ".concat(e.getMessage()));
        }
    }
}