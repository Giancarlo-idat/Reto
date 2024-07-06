package com.civa.futbolista_posicion.service.impl;

import com.civa.futbolista_posicion.dto.role.RoleResponseDTO;
import com.civa.futbolista_posicion.dto.user.UserCreateRequestDTO;
import com.civa.futbolista_posicion.dto.user.UserResponseDTO;
import com.civa.futbolista_posicion.entity.UserEntity;
import com.civa.futbolista_posicion.mapper.RoleMapper;
import com.civa.futbolista_posicion.mapper.UserMapper;
import com.civa.futbolista_posicion.repository.UserRepository;
import com.civa.futbolista_posicion.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, ModelMapper modelMapper, RoleMapper roleMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
    }


    @Override
    public Optional<UserResponseDTO> findByEmail(String email) {
        try {
            Optional<UserEntity> userEmail = userRepository.findByEmail(email);
            if (userEmail.isEmpty()) return Optional.empty();

            return userEmail.map(userMapper::toUserResponse);

        } catch (Exception e) {
            throw new RuntimeException("A ocurrido un error al obtener el email del cliente. ".concat(e.getMessage()));
        }
    }

    @Override
    public List<UserResponseDTO> findAll() {
        try {
            List<UserEntity> users = userRepository.findAll();

            if (users.isEmpty()) return List.of();

            return users.stream()
                    .map(user -> {
                        UserResponseDTO userResponse = modelMapper.map(user, UserResponseDTO.class);
                        Set<RoleResponseDTO> roles = user.getRoles().stream()
                                .map(roleMapper::toRoleResponse)
                                .collect(Collectors.toSet());
                        return userResponse;
                    }).collect(Collectors.toList());

        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
            throw new RuntimeException("A ocurrido un error al obtener los clientes. ".concat(e.getMessage()));
        }
    }

    @Override
    public Optional<UserResponseDTO> findById(UUID id) {
        try {
            Optional<UserEntity> userId = userRepository.findById(id);
            if (userId.isEmpty()) throw new RuntimeException("El cliente no existe.");
            return userId.map(userMapper::toUserResponse);
        } catch (Exception e) {
            throw new RuntimeException("A ocurrido un error al obtener el cliente. ".concat(e.getMessage()));
        }
    }

    @Override
    public UserCreateRequestDTO save(UserCreateRequestDTO userResponse) {
        try {

            UserEntity userEntity = userMapper.toEntity(userResponse);

            if (userRepository.existsByEmail(userResponse.getEmail())) {
                throw new RuntimeException("El email ya existe.");
            }

            userEntity = userRepository.save(userEntity);
            userEntity.setPassword(BCrypt.hashpw(userResponse.getPassword(), BCrypt.gensalt()));

            return userMapper.toUserCreateRequestDTO(userEntity);
        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
            throw new RuntimeException("A ocurrido un error al guardar el cliente. ".concat(e.getMessage()));
        }
    }


    @Override
    public UserCreateRequestDTO update(UserCreateRequestDTO userResponse, UUID id) {
        try {
            Optional<UserEntity> user = userRepository.findById(id);
            if (user.isEmpty()) throw new RuntimeException("El cliente no existe.");
            UserEntity userEntity = user.get();
            userEntity = userRepository.save(userEntity);
            userEntity.setUpdatedAt(userEntity.getUpdatedAt());
            return modelMapper.map(userEntity, UserCreateRequestDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("A ocurrido un error al actualizar el cliente. ".concat(e.getMessage()));
        }
    }


    @Override
    public void disable(UUID id) {
        try {
            Optional<UserEntity> user = userRepository.findById(id);
            if (user.isEmpty()) throw new RuntimeException("El cliente no existe.");

            UserEntity userEntity = user.get();
            userEntity.setUpdatedAt(LocalDateTime.now());
            userRepository.save(userEntity);
            modelMapper.map(userEntity, UserResponseDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("A ocurrido un error al eliminar el cliente. ".concat(e.getMessage()));
        }
    }

    @Override
    public UserResponseDTO enable(UUID id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isEmpty()) throw new RuntimeException("El usuario no existe.");
        UserEntity userEntity = user.get();
        userEntity.setUpdatedAt(LocalDateTime.now());
        userEntity.setStatus(true);
        userRepository.save(userEntity);
        return modelMapper.map(userEntity, UserResponseDTO.class);

    }
}
