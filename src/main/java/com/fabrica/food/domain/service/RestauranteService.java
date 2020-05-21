package com.fabrica.food.domain.service;

import com.fabrica.food.domain.model.Restaurante;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;

public interface RestauranteService {
    
    Restaurante save(Restaurante restaurante);

    Restaurante update(Long id, Restaurante restaurante);

    Restaurante updateParcial(Long id, Map<String, Object> campos);

    void delete(Long id);

    Restaurante findById(Long id);

    List<Restaurante> findAll();

    List<Restaurante> findAllSpecifitaion(Specification<Restaurante> comFreteGratis, Specification<Restaurante> comNomeSemelhante);

    List<Restaurante> findAllSpecifitaion(String nome);

    Restaurante findFirst();
}
