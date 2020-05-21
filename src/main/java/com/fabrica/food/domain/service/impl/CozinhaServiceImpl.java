package com.fabrica.food.domain.service.impl;

import com.fabrica.food.domain.dao.CozinhaDao;
import com.fabrica.food.domain.model.Cozinha;
import com.fabrica.food.domain.exception.BadValueException;
import com.fabrica.food.domain.exception.NoContentException;
import com.fabrica.food.domain.service.CozinhaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
        Cozinha cozinha = this.findById(id);
        this.dao.delete(cozinha);
    }

    @Override
    public Cozinha findById(Long id) {

        if(Objects.isNull(id))
            throw new BadValueException("Favor informe o código da cozinha");

        Cozinha cozinha = this.dao.findById(id).orElse(null);

        if(Objects.isNull(cozinha))
            throw new NoContentException("Essa cozinha não existe na nossa base de dados");

        return cozinha;
    }

    @Override
    public List<Cozinha> findAll() {
        return this.dao.findAll();
    }

    @Override
    public List<Cozinha> findByName(String nome) {

        if(StringUtils.isEmpty(nome)){
            throw new BadValueException("Favor informe o nome para realizar a consulta");
        }

        return this.dao.findByNomeContains(nome);
    }
}
