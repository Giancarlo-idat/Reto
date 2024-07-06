package com.civa.futbolista_posicion.dto.posicion;


import com.civa.futbolista_posicion.dto.base.BaseDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PosicionCreateRequestDTO extends BaseDTO {

    @Builder.Default
    private UUID idPosicion = UUID.randomUUID();

    @NotBlank(message = "El campo nombre de la posición es obligatorio")
    @Size(min = 3, max = 50, message = "El campo nombre de la posición debe tener entre 3 y 50 caracteres")
    private String nombre;
}
