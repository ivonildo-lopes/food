package com.fabrica.food.domain.service;

import com.fabrica.food.domain.dao.CustomCrudBasicRepository;
import com.fabrica.food.domain.model.Estado;
import com.fabrica.food.domain.model.FormaPagamento;

import java.util.List;


public interface FormasPagamentoService extends CustomCrudBasicRepository<FormaPagamento, Long> {

    FormaPagamento save(FormaPagamento estado);

    FormaPagamento update(Long id, FormaPagamento estado);

    void delete(Long id);

    FormaPagamento findById(Long id);

    List<FormaPagamento> findAll();
}
