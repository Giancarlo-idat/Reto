package com.civa.futbolista_posicion.service.base;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/*
 * C -> CREATE
 * R -> RESPONSE
 */

public interface BaseService<R, C> {

    List<R> findAll();

    Optional<R> findById(UUID id);

    C save(C c);

    C update(C c, UUID id);

    void disable(UUID id);

    R enable(UUID id);
}
