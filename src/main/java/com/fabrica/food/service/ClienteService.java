package com.fabrica.food.service;

import com.fabrica.food.domain.model.Cliente;

import java.util.List;

public interface ClienteService  {

    Cliente save(Cliente cliente);

    Cliente update(Long id, Cliente cliente);

    void delete(Long id);

    Cliente findById(Long id);

    List<Cliente> findAll();
}
