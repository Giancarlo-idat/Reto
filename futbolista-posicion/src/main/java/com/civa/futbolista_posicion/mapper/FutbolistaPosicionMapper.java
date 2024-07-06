package com.civa.futbolista_posicion.mapper;


import com.civa.futbolista_posicion.dto.futbolista.FutbolistaResponseDTO;
import com.civa.futbolista_posicion.dto.futbolistaPosicion.FutbolistaPosicionCreateRequestDTO;
import com.civa.futbolista_posicion.dto.futbolistaPosicion.FutbolistaPosicionResponseDTO;
import com.civa.futbolista_posicion.dto.posicion.PosicionResponseDTO;
import com.civa.futbolista_posicion.entity.FutbolistaEntity;
import com.civa.futbolista_posicion.entity.PosicionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class FutbolistaPosicionMapper {

    private final ModelMapper modelMapper;

    public FutbolistaPosicionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public FutbolistaPosicionResponseDTO toFutbolistaPosicionResponseDTO(FutbolistaEntity futbolistaEntity) {
        FutbolistaPosicionResponseDTO futbolistaPosicionResponseDTO = new FutbolistaPosicionResponseDTO();

        futbolistaPosicionResponseDTO.setFutbolistas(Collections.singleton(modelMapper.map(futbolistaEntity, FutbolistaResponseDTO.class)));


        if (futbolistaEntity.getPosiciones() != null) {
            futbolistaPosicionResponseDTO.setPosiciones(futbolistaEntity.getPosiciones().stream()
                    .map(posicion -> {
                        PosicionResponseDTO posicionResponseDTO = new PosicionResponseDTO();
                        posicionResponseDTO.setIdPosicion(posicion.getIdPosicion());
                        posicionResponseDTO.setNombre(posicion.getNombre());
                        return posicionResponseDTO;
                    })
                    .collect(Collectors.toSet()));
        }

        return futbolistaPosicionResponseDTO;
    }

    public FutbolistaPosicionCreateRequestDTO toFutbolistaPosicionCreateRequestDTO(Set<FutbolistaEntity> futbolistaEntities, Set<PosicionEntity> posicionEntities) {
        FutbolistaPosicionCreateRequestDTO futbolistaPosicionCreateRequestDTO = new FutbolistaPosicionCreateRequestDTO();

        Set<UUID> futbolistaIds = futbolistaEntities.stream()
                .map(FutbolistaEntity::getIdFutbolista)
                .collect(Collectors.toSet());
        futbolistaPosicionCreateRequestDTO.setIdFutbolista(futbolistaIds);

        Set<UUID> posicionIds = posicionEntities.stream()
                .map(PosicionEntity::getIdPosicion)
                .collect(Collectors.toSet());
        futbolistaPosicionCreateRequestDTO.setIdPosicion(posicionIds);

        return futbolistaPosicionCreateRequestDTO;
    }
}
