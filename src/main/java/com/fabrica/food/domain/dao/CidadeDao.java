package com.fabrica.food.domain.dao;

import com.fabrica.food.domain.model.Cidade;
import com.fabrica.food.domain.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeDao extends JpaRepository<Cidade, Long> {
}
