package com.civa.futbolista_posicion.mapper;


import com.civa.futbolista_posicion.dto.user.UserCreateRequestDTO;
import com.civa.futbolista_posicion.dto.user.UserResponseDTO;
import com.civa.futbolista_posicion.dto.role.RoleResponseDTO;
import com.civa.futbolista_posicion.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserResponseDTO toUserResponse(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserResponseDTO.class);

    }

    public UserEntity toEntity(UserCreateRequestDTO user) {
        return modelMapper.map(user, UserEntity.class);
    }

    public UserCreateRequestDTO toUserCreateRequestDTO(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserCreateRequestDTO.class);

    }
}

