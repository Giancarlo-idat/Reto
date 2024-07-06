package com.civa.futbolista_posicion.repository;

import com.civa.futbolista_posicion.entity.UserEntity;
import com.civa.futbolista_posicion.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends BaseRepository<UserEntity, UUID> {

    @Query("SELECT c FROM UserEntity c WHERE c.email = :email")
    Optional<UserEntity> findByEmail(@Param("email") String email);

    boolean existsByEmail(String email);

}
