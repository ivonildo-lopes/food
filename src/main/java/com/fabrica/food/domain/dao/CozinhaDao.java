package com.fabrica.food.domain.dao;

import com.fabrica.food.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CozinhaDao extends JpaRepository<Cozinha, Long> {

    List<Cozinha> findByNomeContains(String nome);
}
