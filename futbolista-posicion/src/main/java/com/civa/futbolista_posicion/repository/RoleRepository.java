package com.civa.futbolista_posicion.repository;

import com.civa.futbolista_posicion.entity.RoleEntity;
import com.civa.futbolista_posicion.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RoleRepository extends BaseRepository<RoleEntity, UUID> {

    @Query("SELECT r FROM RoleEntity r WHERE r.name = :name")
    List<RoleEntity> findByName(@Param("name") String name);

    boolean existsByName(String name);

}
