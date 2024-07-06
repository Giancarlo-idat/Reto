package com.civa.futbolista_posicion.dto.userRole;

import com.civa.futbolista_posicion.dto.base.BaseDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserRoleCreateRequestDTO extends BaseDTO {
    @NotNull(message = "El campo idUser es obligatorio")
    private Set<UUID> idUser;

    @NotNull(message = "El campo idRole es obligatorio")
    private Set<UUID> idRole;
}
