package com.civa.futbolista_posicion.dto.futbolistaPosicion;

import com.civa.futbolista_posicion.dto.base.BaseDTO;
import com.civa.futbolista_posicion.dto.futbolista.FutbolistaResponseDTO;
import com.civa.futbolista_posicion.dto.posicion.PosicionResponseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FutbolistaPosicionResponseDTO extends BaseDTO {
    private Set<FutbolistaResponseDTO> futbolistas;
    private Set<PosicionResponseDTO> posiciones;
}

