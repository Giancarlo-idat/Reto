package com.civa.futbolista_posicion.dto.futbolista;

import com.civa.futbolista_posicion.dto.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FutbolistaResponseDTO extends BaseDTO {

    private UUID idFutbolista;
    private String nombres;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private Map<String, String> caracteristicas;

}
