package com.fabrica.food.domain.dao;

import com.fabrica.food.domain.model.Estado;
import com.fabrica.food.domain.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagamentoDao extends JpaRepository<FormaPagamento, Long> {

    Boolean existsByDescricao(String descricao);
    int countByDescricao(String descricao);
}
