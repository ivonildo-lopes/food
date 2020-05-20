package com.fabrica.food.service.impl;

import com.fabrica.food.domain.dao.CozinhaDao;
import com.fabrica.food.domain.model.Cozinha;
import com.fabrica.food.service.CozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CozinhaServiceImpl implements CozinhaService {

    @Autowired
    private CozinhaDao dao;

    @Override
    public Cozinha save(Cozinha cozinha) {
        return this.dao.save(cozinha);
    }

    @Override
    public Cozinha update(Long id, Cozinha cozinha) {
        Cozinha cli = this.findById(id);
        BeanUtils.copyProperties(cozinha,cli,"id");

        return this.save(cli);
    }

    @Override
    public void delete(Long id) {
        this.dao.deleteById(id);
    }

    @Override
    public Cozinha findById(Long id) {
        return this.dao.findById(id).orElse(null);
    }

    @Override
    public List<Cozinha> findAll() {
        return this.dao.findAll();
    }
}
