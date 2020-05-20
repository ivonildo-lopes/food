package com.fabrica.food.domain.dao;

import com.fabrica.food.domain.model.Cozinha;
import com.fabrica.food.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteDao extends JpaRepository<Restaurante, Long> {
}
