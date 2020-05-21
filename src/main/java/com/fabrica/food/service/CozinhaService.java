package com.fabrica.food.service;

import com.fabrica.food.domain.model.Cozinha;

import java.util.List;

public interface CozinhaService {
    
    Cozinha save(Cozinha cozinha);

    Cozinha update(Long id, Cozinha cozinha);

    void delete(Long id);

    Cozinha findById(Long id);

    List<Cozinha> findAll();

    List<Cozinha> findByName(String nome);
}
