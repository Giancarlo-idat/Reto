package com.civa.futbolista_posicion.service.impl;

import com.civa.futbolista_posicion.dto.futbolista.FutbolistaCreateRequestDTO;
import com.civa.futbolista_posicion.dto.futbolista.FutbolistaResponseDTO;
import com.civa.futbolista_posicion.entity.FutbolistaEntity;
import com.civa.futbolista_posicion.mapper.FutbolistaMapper;
import com.civa.futbolista_posicion.repository.FutbolistaRepository;
import com.civa.futbolista_posicion.service.FutbolistaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FutbolistaServiceImpl implements FutbolistaService {

    private final FutbolistaMapper futbolistaMapper;
    private final FutbolistaRepository futbolistaRepository;

    public FutbolistaServiceImpl(FutbolistaMapper futbolistaMapper, FutbolistaRepository futbolistaRepository) {
        this.futbolistaMapper = futbolistaMapper;
        this.futbolistaRepository = futbolistaRepository;
    }

    @Override
    public List<FutbolistaResponseDTO> findAll() {
        try {
            return futbolistaRepository.findAll().stream()
                    .map(futbolistaMapper::toFutbolistaResponseDTO)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Error al obtener los futbolistas: {}", ex.getMessage());
            throw new RuntimeException("Error al obtener los futbolistas: ".concat(ex.getMessage()));
        }
    }

    @Override
    public List<FutbolistaResponseDTO> findByNombresOrApellidos(String nombres) {
        try {
            return futbolistaRepository.findByNombresOrApellidos(nombres).stream()
                    .map(futbolistaMapper::toFutbolistaResponseDTO)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Error al buscar futbolistas por nombres o apellidos: {}", ex.getMessage());
            throw new RuntimeException("Error al buscar futbolistas por nombres o apellidos: ".concat(ex.getMessage()));
        }
    }

    @Override
    public Optional<FutbolistaResponseDTO> findById(UUID id) {
        try {
            FutbolistaEntity futbolistaEntity = futbolistaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("El futbolista no existe"));

            return Optional.of(futbolistaMapper.toFutbolistaResponseDTO(futbolistaEntity));

        } catch (Exception ex) {
            log.error("Error al obtener el futbolista: {}", ex.getMessage());
            throw new RuntimeException("Error al obtener el futbolista: ".concat(ex.getMessage()));
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Page<FutbolistaResponseDTO> getAllFutbolistasPaginated(int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            return futbolistaRepository.findAll(pageable)
                    .map(futbolistaMapper::toFutbolistaResponseDTO);
        } catch (Exception ex) {
            log.error("Error al obtener los futbolistas paginados: {}", ex.getMessage());
            throw new RuntimeException("Error al obtener los futbolistas paginados: ".concat(ex.getMessage()));
        }
    }

    @Override
    public FutbolistaCreateRequestDTO save(FutbolistaCreateRequestDTO futbolistaCreateRequestDTO) {
        try {
            FutbolistaEntity futbolistaEntity = futbolistaMapper.toFutbolistaEntity(futbolistaCreateRequestDTO);

            if (futbolistaRepository.existsByNombresAndApellidos(futbolistaEntity.getNombres(), futbolistaEntity.getApellidos())) {
                throw new RuntimeException("El futbolista ya existe");
            }

            futbolistaEntity = futbolistaRepository.save(futbolistaEntity);

            return futbolistaMapper.toFutbolistaCreateRequestDTO(futbolistaEntity);


        } catch (Exception ex) {
            log.error("Error al guardar el futbolista: {}", ex.getMessage());
            throw new RuntimeException("Error al guardar el futbolista: {}".concat(ex.getMessage()));
        }

    }

    @Override
    public FutbolistaCreateRequestDTO update(FutbolistaCreateRequestDTO futbolistaCreateRequestDTO, UUID id) {
        try {

            if (!futbolistaRepository.existsById(id)) {
                throw new RuntimeException("El futbolista no existe");
            }

            if (futbolistaCreateRequestDTO.getIdFutbolista() != null && !futbolistaCreateRequestDTO.getIdFutbolista().equals(id)) {
                throw new RuntimeException("El id del futbolista no coincide");
            }

            FutbolistaEntity futbolistaEntity = futbolistaMapper.toFutbolistaEntity(futbolistaCreateRequestDTO);
            futbolistaEntity.setUpdatedAt(LocalDateTime.now());

            futbolistaEntity = futbolistaRepository.save(futbolistaEntity);

            return futbolistaMapper.toFutbolistaCreateRequestDTO(futbolistaEntity);

        } catch (Exception ex) {
            log.error("Error al actualizar el futbolista: {}", ex.getMessage());
            throw new RuntimeException("Error al actualizar el futbolista: ".concat(ex.getMessage()));
        }
    }

    @Override
    public void disable(UUID id) {
        try {
            Optional<FutbolistaEntity> optFutbolista = futbolistaRepository.findById(id);

            if (optFutbolista.isEmpty()) {
                throw new RuntimeException("El futbolista no existe");
            }

            FutbolistaEntity futbolistaEntity = optFutbolista.get();

            futbolistaEntity.setUpdatedAt(LocalDateTime.now());
            futbolistaEntity.setStatus(false);

            futbolistaRepository.save(futbolistaEntity);

        } catch (Exception ex) {
            log.error("Error al deshabilitar el futbolista: {}", ex.getMessage());
            throw new RuntimeException("Error al deshabilitar el futbolista: ".concat(ex.getMessage()));
        }
    }

    @Override
    public FutbolistaResponseDTO enable(UUID id) {
        try {
            Optional<FutbolistaEntity> optFutbolista = futbolistaRepository.findById(id);

            if (optFutbolista.isEmpty()) {
                throw new RuntimeException("El futbolista no existe");
            }

            FutbolistaEntity futbolistaEntity = optFutbolista.get();

            futbolistaEntity.setUpdatedAt(LocalDateTime.now());
            futbolistaEntity.setStatus(true);

            futbolistaRepository.save(futbolistaEntity);

            return futbolistaMapper.toFutbolistaResponseDTO(futbolistaEntity);
        } catch (Exception ex) {
            log.error("Error al habilitar el futbolista: {}", ex.getMessage());
            throw new RuntimeException("Error al habilitar el futbolista: ".concat(ex.getMessage()));
        }

    }

}
