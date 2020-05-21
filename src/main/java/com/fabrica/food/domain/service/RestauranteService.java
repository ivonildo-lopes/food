package com.fabrica.food.domain.service;

import com.fabrica.food.domain.model.Restaurante;
import com.fabrica.food.infrastructure.spec.RestauranteComFreteGratisSpec;
import com.fabrica.food.infrastructure.spec.RestauranteComNomeSemelhanteSpec;

import java.util.List;
import java.util.Map;

public interface RestauranteService {
    
    Restaurante save(Restaurante restaurante);

    Restaurante update(Long id, Restaurante restaurante);

    Restaurante updateParcial(Long id, Map<String, Object> campos);

    void delete(Long id);

    Restaurante findById(Long id);

    List<Restaurante> findAll();

    List<Restaurante> findAllSpecifitaion(RestauranteComFreteGratisSpec comFreteGratis, RestauranteComNomeSemelhanteSpec comNomeSemelhante);
}
