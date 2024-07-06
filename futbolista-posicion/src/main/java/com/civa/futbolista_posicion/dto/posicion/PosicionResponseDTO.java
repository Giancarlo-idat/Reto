package com.civa.futbolista_posicion.dto.posicion;


import com.civa.futbolista_posicion.dto.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PosicionResponseDTO  extends BaseDTO {

    private UUID idPosicion;
    private String nombre;

}
