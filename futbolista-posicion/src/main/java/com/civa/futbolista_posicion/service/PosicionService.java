package com.civa.futbolista_posicion.service;

import com.civa.futbolista_posicion.dto.posicion.PosicionCreateRequestDTO;
import com.civa.futbolista_posicion.dto.posicion.PosicionResponseDTO;
import com.civa.futbolista_posicion.service.base.BaseService;

import java.util.List;

public interface PosicionService extends BaseService<PosicionResponseDTO, PosicionCreateRequestDTO> {

    List<PosicionResponseDTO> findByNombre(String nombre);
}
