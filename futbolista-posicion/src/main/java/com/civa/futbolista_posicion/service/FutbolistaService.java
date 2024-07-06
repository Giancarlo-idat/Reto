package com.civa.futbolista_posicion.service;

import com.civa.futbolista_posicion.dto.futbolista.FutbolistaCreateRequestDTO;
import com.civa.futbolista_posicion.dto.futbolista.FutbolistaResponseDTO;
import com.civa.futbolista_posicion.entity.FutbolistaEntity;
import com.civa.futbolista_posicion.service.base.BaseService;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FutbolistaService extends BaseService<FutbolistaResponseDTO, FutbolistaCreateRequestDTO> {

    List<FutbolistaResponseDTO> findByNombresOrApellidos(String nombres);

    Page<FutbolistaResponseDTO> getAllFutbolistasPaginated(int page, int size);
}
