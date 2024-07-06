package com.civa.futbolista_posicion.dto.userRole;

import com.civa.futbolista_posicion.dto.base.BaseDTO;
import com.civa.futbolista_posicion.dto.role.RoleResponseDTO;
import com.civa.futbolista_posicion.dto.user.UserResponseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRoleResponseDTO extends BaseDTO {
    private Set<UserResponseDTO> users;
    private Set<RoleResponseDTO> roles;
}
