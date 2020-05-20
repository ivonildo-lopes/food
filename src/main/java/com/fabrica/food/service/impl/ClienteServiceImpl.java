package com.fabrica.food.service.impl;

import com.fabrica.food.dao.ClienteDao;
import com.fabrica.food.domain.model.Cliente;
import com.fabrica.food.service.ClienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteDao dao;

    @Override
    public Cliente save(Cliente cliente) {
        return this.dao.save(cliente);
    }

    @Override
    public Cliente update(Long id, Cliente cliente) {
        Cliente cli = this.findById(id);
        BeanUtils.copyProperties(cliente,cli,"id");
        return cli;
    }

    @Override
    public void delete(Long id) {
        this.dao.deleteById(id);
    }

    @Override
    public Cliente findById(Long id) {
        return this.dao.findById(id).orElse(null);
    }

    @Override
    public List<Cliente> findAll() {
        return this.dao.findAll();
    }
}
