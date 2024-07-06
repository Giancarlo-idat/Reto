package com.civa.futbolista_posicion.mapper;


import com.civa.futbolista_posicion.dto.posicion.PosicionCreateRequestDTO;
import com.civa.futbolista_posicion.dto.posicion.PosicionResponseDTO;
import com.civa.futbolista_posicion.entity.PosicionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PosicionMapper {

    private final ModelMapper modelMapper;

    public PosicionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PosicionResponseDTO toPosicionResponseDTO(PosicionEntity posicionEntity) {
        return modelMapper.map(posicionEntity, PosicionResponseDTO.class);
    }

    public PosicionEntity toPosicionEntity(PosicionCreateRequestDTO toPosicionCreateRequestDTO) {
        return modelMapper.map(toPosicionCreateRequestDTO, PosicionEntity.class);
    }

    public PosicionCreateRequestDTO toPosicionCreateRequestDTO(PosicionEntity posicionEntity) {
        return modelMapper.map(posicionEntity, PosicionCreateRequestDTO.class);
    }
}
