package com.civa.futbolista_posicion.repository.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {
}
