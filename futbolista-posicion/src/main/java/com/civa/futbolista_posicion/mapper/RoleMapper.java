package com.civa.futbolista_posicion.mapper;


import com.civa.futbolista_posicion.dto.role.RoleCreateRequestDTO;
import com.civa.futbolista_posicion.dto.role.RoleResponseDTO;
import com.civa.futbolista_posicion.entity.RoleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class RoleMapper {

    private final ModelMapper modelMapper;

    public RoleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public RoleResponseDTO toRoleResponse(RoleEntity roleEntity) {
        return modelMapper.map(roleEntity, RoleResponseDTO.class);

    }

    public RoleEntity toEntity(RoleCreateRequestDTO roleRequest) {
        RoleEntity roleEntity = modelMapper.map(roleRequest, RoleEntity.class);
        String capitalizedName = roleRequest.getName().substring(0, 1).toUpperCase() + roleRequest.getName().substring(1).toLowerCase();
        roleEntity.setName(capitalizedName);
        return roleEntity;
    }

    public RoleCreateRequestDTO toRoleCreateRequestDTO(RoleEntity roleEntity) {
        return modelMapper.map(roleEntity, RoleCreateRequestDTO.class);
    }

}


