package com.civa.futbolista_posicion.repository;

import com.civa.futbolista_posicion.entity.PosicionEntity;
import com.civa.futbolista_posicion.repository.base.BaseRepository;

import java.util.List;
import java.util.UUID;

public interface PosicionRepository extends BaseRepository<PosicionEntity, UUID> {

    List<PosicionEntity> findByNombre(String nombre);

    Boolean existsByNombre(String nombre);
}
