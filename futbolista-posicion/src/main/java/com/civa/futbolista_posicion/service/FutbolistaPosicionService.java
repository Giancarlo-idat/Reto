package com.civa.futbolista_posicion.service;

import com.civa.futbolista_posicion.dto.futbolistaPosicion.FutbolistaPosicionCreateRequestDTO;
import com.civa.futbolista_posicion.dto.futbolistaPosicion.FutbolistaPosicionResponseDTO;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface FutbolistaPosicionService {
    FutbolistaPosicionCreateRequestDTO addFutbolistaPosicion(Set<UUID> idFutbolista, Set<UUID> idPosicion);
    List<FutbolistaPosicionResponseDTO>getAllFutbolistasWithPosicion();
}
