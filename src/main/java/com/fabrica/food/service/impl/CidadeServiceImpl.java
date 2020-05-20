package com.fabrica.food.service.impl;

import com.fabrica.food.domain.dao.CidadeDao;
import com.fabrica.food.domain.model.Cidade;
import com.fabrica.food.service.CidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeServiceImpl implements CidadeService {

    @Autowired
    private CidadeDao dao;

    @Override
    public Cidade save(Cidade cidade) {
        return this.dao.save(cidade);
    }

    @Override
    public Cidade update(Long id, Cidade cidade) {
        Cidade cli = this.findById(id);
        BeanUtils.copyProperties(cidade,cli,"id");

        return this.save(cli);
    }

    @Override
    public void delete(Long id) {
        this.dao.deleteById(id);
    }

    @Override
    public Cidade findById(Long id) {
        return this.dao.findById(id).orElse(null);
    }

    @Override
    public List<Cidade> findAll() {
        return this.dao.findAll();
    }
}
