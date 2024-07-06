package com.civa.futbolista_posicion.repository;

import com.civa.futbolista_posicion.entity.FutbolistaEntity;
import com.civa.futbolista_posicion.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface FutbolistaRepository extends BaseRepository<FutbolistaEntity, UUID> {

    @Query("SELECT f FROM FutbolistaEntity f WHERE f.nombres LIKE %:terminos% OR f.apellidos LIKE %:terminos%")
    List<FutbolistaEntity> findByNombresOrApellidos(@Param("terminos") String termino);

    boolean existsByNombresAndApellidos(String nombres, String apellidos);
}
