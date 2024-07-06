package com.civa.futbolista_posicion.service;

import com.civa.futbolista_posicion.dto.user.UserCreateRequestDTO;
import com.civa.futbolista_posicion.dto.user.UserResponseDTO;
import com.civa.futbolista_posicion.service.base.BaseService;

import java.util.Optional;

public interface UserService extends BaseService<UserResponseDTO, UserCreateRequestDTO> {

    Optional<UserResponseDTO> findByEmail(String email);

}
