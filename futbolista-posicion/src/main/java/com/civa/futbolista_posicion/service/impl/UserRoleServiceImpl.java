package com.civa.futbolista_posicion.service.impl;

import com.civa.futbolista_posicion.dto.userRole.UserRoleCreateRequestDTO;
import com.civa.futbolista_posicion.dto.userRole.UserRoleResponseDTO;
import com.civa.futbolista_posicion.entity.RoleEntity;
import com.civa.futbolista_posicion.entity.UserEntity;
import com.civa.futbolista_posicion.mapper.UserRoleMapper;
import com.civa.futbolista_posicion.repository.RoleRepository;
import com.civa.futbolista_posicion.repository.UserRepository;
import com.civa.futbolista_posicion.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleMapper userRoleMapper;

    public UserRoleServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserRoleMapper userRoleMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public UserRoleCreateRequestDTO addRoleToUser(Set<UUID> idUsers, Set<UUID> idRoles) {
        try {
            if (idUsers.isEmpty()) {
                throw new IllegalArgumentException("El conjunto de UUIDs de usuarios está vacío");
            }

            if (idRoles.isEmpty()) {
                throw new IllegalArgumentException("El conjunto de UUIDs de roles está vacío");
            }

            List<UserEntity> users = userRepository.findAllById(idUsers);


            if (users.size() != idUsers.size()) {
                throw new RuntimeException("No se encontraron todos los usuarios solicitados");
            }

            List<RoleEntity> roles = roleRepository.findAllById(idRoles);

            if (roles.size() != idRoles.size()) {
                throw new RuntimeException("No se encontraron todos los roles solicitados");
            }

            for (UserEntity userEntity : users) {
                if (!userEntity.getStatus()) {
                    throw new RuntimeException("El usuario con id: ".concat(userEntity.getIdUser().toString()).concat(" no está activo"));
                }
                for (RoleEntity roleEntity : roles) {
                    if (!roleEntity.getStatus()) {
                        throw new RuntimeException("El rol con id: ".concat(roleEntity.getIdRole().toString()).concat(" no está activo"));
                    }
                    userEntity.getRoles().add(roleEntity);
                }
            }

            users = userRepository.saveAll(users);

            Set<UserEntity> userEntitySet = new HashSet<>(users);
            Set<RoleEntity> roleEntitySet = new HashSet<>(roles);

            return userRoleMapper.toUserRoleCreateRequestDTO(userEntitySet, roleEntitySet);

        } catch (Exception ex) {
            log.error("Error al agregar roles a usuarios: {}", ex.getMessage());
            throw new RuntimeException("Error al agregar roles a usuarios".concat(ex.getMessage()));
        }
    }

    @Override
    public List<UserRoleResponseDTO> getAllUsersWithRoles() {
        try {
            List<UserEntity> users = userRepository.findAll();

            return users.stream()
                    .map(userRoleMapper::toUserRoleResponseDTO)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Error al obtener los usuarios con roles: {}", ex.getMessage());
            throw new RuntimeException("Error al obtener los usuarios con roles".concat(ex.getMessage()));
        }
    }
}
