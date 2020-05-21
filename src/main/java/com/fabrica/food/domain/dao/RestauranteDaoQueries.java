package com.fabrica.food.domain.dao;

import com.fabrica.food.domain.model.Restaurante;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteDaoQueries {

    List<Restaurante> findParams(String nome, BigDecimal taxaFrete, BigDecimal taxaFreteFinal);

    List<Restaurante> findSpecification(String nome);
}
