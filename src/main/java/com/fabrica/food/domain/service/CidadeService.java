package com.fabrica.food.domain.service;

import com.fabrica.food.domain.model.Cidade;

import java.util.List;

public interface CidadeService {

    Cidade save(Cidade cidade);

    Cidade update(Long id, Cidade cidade);

    void delete(Long id);

    Cidade findById(Long id);

    List<Cidade> findAll();
}
