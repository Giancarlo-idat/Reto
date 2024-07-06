package com.civa.futbolista_posicion.mapper;

import com.civa.futbolista_posicion.dto.role.RoleResponseDTO;
import com.civa.futbolista_posicion.dto.user.UserResponseDTO;
import com.civa.futbolista_posicion.dto.userRole.UserRoleCreateRequestDTO;
import com.civa.futbolista_posicion.dto.userRole.UserRoleResponseDTO;
import com.civa.futbolista_posicion.entity.RoleEntity;
import com.civa.futbolista_posicion.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UserRoleMapper {

    private final ModelMapper modelMapper;

    public UserRoleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserRoleResponseDTO toUserRoleResponseDTO(UserEntity userEntity) {
        UserRoleResponseDTO userRoleResponseDTO = new UserRoleResponseDTO();

        userRoleResponseDTO.setUsers(Collections.singleton(modelMapper.map(userEntity, UserResponseDTO.class)));

        if (userEntity.getRoles() != null) {
            userRoleResponseDTO.setRoles(userEntity.getRoles().stream()
                    .map(role -> {
                        RoleResponseDTO roleResponseDTO = new RoleResponseDTO();
                        roleResponseDTO.setIdRole(role.getIdRole());
                        roleResponseDTO.setName(role.getName());
                        return roleResponseDTO;
                    })
                    .collect(Collectors.toSet()));
        }

        return userRoleResponseDTO;

    }

    public UserRoleCreateRequestDTO toUserRoleCreateRequestDTO(Set<UserEntity> userEntities, Set<RoleEntity> roleEntities) {
        UserRoleCreateRequestDTO userRoleCreateRequestDTO = new UserRoleCreateRequestDTO();

        Set<UUID> userIds = userEntities.stream()
                .map(UserEntity::getIdUser)
                .collect(Collectors.toSet());
        userRoleCreateRequestDTO.setIdUser(userIds);

        Set<UUID> roleIds = roleEntities.stream()
                .map(RoleEntity::getIdRole)
                .collect(Collectors.toSet());
        userRoleCreateRequestDTO.setIdRole(roleIds);

        return userRoleCreateRequestDTO;
    }
}
