package com.fabrica.food.domain.service.impl;

import com.fabrica.food.domain.dao.EstadoDao;
import com.fabrica.food.domain.model.Estado;
import com.fabrica.food.domain.service.EstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoServiceImpl implements EstadoService {

    @Autowired
    private EstadoDao dao;

    @Override
    public Estado save(Estado estado) {
        return this.dao.save(estado);
    }

    @Override
    public Estado update(Long id, Estado estado) {
        Estado cli = this.findById(id);
        BeanUtils.copyProperties(estado,cli,"id");

        return this.save(cli);
    }

    @Override
    public void delete(Long id) {
        this.dao.deleteById(id);
    }

    @Override
    public Estado findById(Long id) {
        return this.dao.findById(id).orElse(null);
    }

    @Override
    public List<Estado> findAll() {
        return this.dao.findAll();
    }
}
