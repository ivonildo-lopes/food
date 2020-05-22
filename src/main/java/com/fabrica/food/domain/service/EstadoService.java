package com.fabrica.food.domain.service;

import com.fabrica.food.domain.dao.CustomCrudBasicRepository;
import com.fabrica.food.domain.model.Estado;

import java.util.List;


public interface EstadoService extends CustomCrudBasicRepository<Estado, Long> {

    Estado save(Estado estado);

    Estado update(Long id, Estado estado);

    void delete(Long id);

    Estado findById(Long id);

    List<Estado> findAll();
}
