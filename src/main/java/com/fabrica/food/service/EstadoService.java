package com.fabrica.food.service;

import com.fabrica.food.domain.model.Estado;

import java.util.List;

public interface EstadoService {

    Estado save(Estado estado);

    Estado update(Long id, Estado estado);

    void delete(Long id);

    Estado findById(Long id);

    List<Estado> findAll();
}
