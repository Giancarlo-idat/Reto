package com.civa.futbolista_posicion.dto.futbolistaPosicion;

import com.civa.futbolista_posicion.dto.base.BaseDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class FutbolistaPosicionCreateRequestDTO extends BaseDTO {
    @NotNull(message = "El campo idFutbolista es obligatorio")
    private Set<UUID> idFutbolista;
    @NotNull(message = "El campo idPosicion es obligatorio")
    private Set<UUID> idPosicion;
}
