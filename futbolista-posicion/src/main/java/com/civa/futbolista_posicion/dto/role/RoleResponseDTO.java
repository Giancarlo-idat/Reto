package com.civa.futbolista_posicion.dto.role;

import com.civa.futbolista_posicion.dto.base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponseDTO extends BaseDTO {

    private UUID idRole;
    private String name;
}
