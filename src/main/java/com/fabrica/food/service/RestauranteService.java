package com.fabrica.food.service;

import com.fabrica.food.domain.model.Restaurante;

import java.util.List;

public interface RestauranteService {
    
    Restaurante save(Restaurante restaurante);

    Restaurante update(Long id, Restaurante restaurante);

    void delete(Long id);

    Restaurante findById(Long id);

    List<Restaurante> findAll();
}
