package com.civa.futbolista_posicion.service;

import com.civa.futbolista_posicion.dto.role.RoleCreateRequestDTO;
import com.civa.futbolista_posicion.dto.role.RoleResponseDTO;
import com.civa.futbolista_posicion.service.base.BaseService;

import java.util.List;

public interface RoleService extends BaseService<RoleResponseDTO, RoleCreateRequestDTO> {

    List<RoleResponseDTO> findByName(String name);
}
