package com.civa.futbolista_posicion.service.impl;

import com.civa.futbolista_posicion.dto.role.RoleCreateRequestDTO;
import com.civa.futbolista_posicion.dto.role.RoleResponseDTO;
import com.civa.futbolista_posicion.entity.RoleEntity;
import com.civa.futbolista_posicion.mapper.RoleMapper;
import com.civa.futbolista_posicion.repository.RoleRepository;
import com.civa.futbolista_posicion.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    public final RoleRepository roleRepository;
    public final ModelMapper modelMapper;
    public final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleResponseDTO> findByName(String name) {
        try {
            List<RoleEntity> rolesNames = roleRepository.findByName(name);
            if (rolesNames.isEmpty()) return List.of();

            return rolesNames.stream()
                    .map(roleMapper::toRoleResponse)
                    .collect(Collectors.toList());
        } catch (RuntimeException e) {
            throw new RuntimeException("Error al buscar el nombre del rol: " + e.getMessage());
        }
    }

    @Override
    public List<RoleResponseDTO> findAll() {
        try {
            List<RoleEntity> roles = roleRepository.findAll();
            if (roles.isEmpty()) return List.of();

            return roles.stream()
                    .map(roleMapper::toRoleResponse)
                    .collect(Collectors.toList());
        } catch (RuntimeException e) {
            throw new RuntimeException("Error al buscar los roles: " + e.getMessage());
        }
    }

    @Override
    public Optional<RoleResponseDTO> findById(UUID id) {
        try {
            Optional<RoleEntity> roleId = roleRepository.findById(id);
            if (roleId.isEmpty()) throw new RuntimeException("Rol no encontrado");
            if (id == UUID.fromString("00000000-0000-0000-0000-000000000000"))
                throw new Error("El id de la role no puede ser nulo");
            return roleId.map(roleMapper::toRoleResponse);
        } catch (RuntimeException e) {
            throw new Error("Error al buscar el id rol: " + e.getMessage());
        }
    }

    @Override
    public RoleCreateRequestDTO save(RoleCreateRequestDTO roleRequest) {
        try {
            if (roleRepository.existsByName(roleRequest.getName())) throw new RuntimeException("El rol ya existe");

            RoleEntity role = roleMapper.toEntity(roleRequest);

            RoleEntity savedRole = roleRepository.save(role);

            return roleMapper.toRoleCreateRequestDTO(savedRole);

        } catch (RuntimeException e) {
            throw new RuntimeException("Error al crear el rol: " + e.getMessage());
        }
    }

    @Override
    public RoleCreateRequestDTO update(RoleCreateRequestDTO createRoleRequest, UUID id) {
        try {
            if (!roleRepository.existsById(id)) {
                throw new RuntimeException("El rol no existe");
            }

            if (createRoleRequest.getIdRole() != null && !createRoleRequest.getIdRole().equals(id)) {
                throw new RuntimeException("El id del rol no puede ser diferente al id de la url");
            }

            RoleEntity roleEntity = roleMapper.toEntity(createRoleRequest);

            roleEntity.setUpdatedAt(LocalDateTime.now());

            roleEntity = roleRepository.save(roleEntity);

            return roleMapper.toRoleCreateRequestDTO(roleEntity);

        } catch (RuntimeException e) {
            throw new RuntimeException("Error al actualizar el rol: " + e.getMessage());
        }
    }

    @Override
    public void disable(UUID id) {
        try {
            RoleEntity role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Rol no encontrado"));

            role.setStatus(false);
            role.setUpdatedAt(LocalDateTime.now());
            roleRepository.save(role);

        } catch (RuntimeException e) {
            throw new RuntimeException("Error al eliminar el rol: " + e.getMessage());
        }
    }

    @Override
    public RoleResponseDTO enable(UUID id) {
        try {
            Optional<RoleEntity> roleId = roleRepository.findById(id);
            if (roleId.isEmpty()) throw new RuntimeException("Rol no encontrado");

            RoleEntity role = roleId.get();
            role.setUpdatedAt(LocalDateTime.now());
            role.setStatus(true);
            roleRepository.save(role);
            return modelMapper.map(role, RoleResponseDTO.class);

        } catch (RuntimeException e) {
            throw new RuntimeException("Error al habilitar el rol: " + e.getMessage());
        }
    }

}
