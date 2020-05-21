package com.fabrica.food.domain.dao;

import com.fabrica.food.domain.model.Estado;
import com.fabrica.food.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoDao extends JpaRepository<Estado, Long> {

    Boolean existsByNome(String nome);
}
