package com.civa.futbolista_posicion.mapper;

import com.civa.futbolista_posicion.dto.futbolista.FutbolistaCreateRequestDTO;
import com.civa.futbolista_posicion.dto.futbolista.FutbolistaResponseDTO;
import com.civa.futbolista_posicion.entity.FutbolistaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class FutbolistaMapper {

    private final ModelMapper modelMapper;

    public FutbolistaMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public FutbolistaResponseDTO toFutbolistaResponseDTO(FutbolistaEntity futbolistaEntity) {
        return modelMapper.map(futbolistaEntity, FutbolistaResponseDTO.class);
    }


    public FutbolistaCreateRequestDTO toFutbolistaCreateRequestDTO(FutbolistaEntity futbolistaEntity) {
        return modelMapper.map(futbolistaEntity, FutbolistaCreateRequestDTO.class);
    }

    public FutbolistaEntity toFutbolistaEntity(FutbolistaCreateRequestDTO futbolistaCreateRequestDTO) {
        return modelMapper.map(futbolistaCreateRequestDTO, FutbolistaEntity.class);
    }
}
