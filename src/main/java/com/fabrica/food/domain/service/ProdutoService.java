package com.fabrica.food.domain.service;

import com.fabrica.food.domain.dao.CustomCrudBasicRepository;
import com.fabrica.food.domain.model.Produto;

import java.util.List;

public interface ProdutoService extends CustomCrudBasicRepository<Produto, Long> {
    
    Produto save(Produto produto);

    Produto update(Long id, Produto produto);

    void delete(Long id);

    Produto findById(Long id);

    List<Produto> findAll();

    List<Produto> findByName(String nome);
}
