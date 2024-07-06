package com.civa.futbolista_posicion.service;

import com.civa.futbolista_posicion.dto.userRole.UserRoleCreateRequestDTO;
import com.civa.futbolista_posicion.dto.userRole.UserRoleResponseDTO;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface UserRoleService {

    UserRoleCreateRequestDTO addRoleToUser(Set<UUID> idUsers, Set<UUID> idRoles);

    List<UserRoleResponseDTO> getAllUsersWithRoles();

}
