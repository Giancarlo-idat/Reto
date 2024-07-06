package com.civa.futbolista_posicion.dto.role;

import com.civa.futbolista_posicion.dto.base.BaseDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RoleCreateRequestDTO extends BaseDTO {

    @Builder.Default
    private UUID idRole = UUID.randomUUID();

    @NotBlank(message ="El nombre del rol es requerido")
    @Size(min = 3, max = 50, message = "El nombre del rol debe contener entre 3 y 50 caracteres")
    private String name;

}