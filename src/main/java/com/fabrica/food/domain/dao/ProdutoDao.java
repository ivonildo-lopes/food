package com.fabrica.food.domain.dao;

import com.fabrica.food.domain.model.Cozinha;
import com.fabrica.food.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoDao extends JpaRepository<Produto, Long> {

    List<Produto> findByNomeContains(String nome);
}
