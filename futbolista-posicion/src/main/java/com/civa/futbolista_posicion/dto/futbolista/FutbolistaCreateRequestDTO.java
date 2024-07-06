package com.civa.futbolista_posicion.dto.futbolista;

import com.civa.futbolista_posicion.dto.base.BaseDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;


@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FutbolistaCreateRequestDTO extends BaseDTO {

    @Builder.Default
    private UUID idFutbolista = UUID.randomUUID();

    @NotBlank(message = "El campo nombres es obligatorio")
    @Size(min = 3, max = 50, message = "El campo nombres debe contener entre 3 y 50 caracteres")
    private String nombres;

    @NotBlank(message = "El campo apellidos es obligatorio")
    @Size(min = 3, max = 50, message = "El campo apellidos debe contener entre 3 y 50 caracteres")
    private String apellidos;

    @NotNull(message = "El campo fechaNacimiento es obligatorio")
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    private LocalDate fechaNacimiento;

    @NotNull(message = "El campo caracteristicas es obligatorio")
    private Map<String, String> caracteristicas;

}

