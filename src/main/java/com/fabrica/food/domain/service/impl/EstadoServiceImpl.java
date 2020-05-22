package com.fabrica.food.domain.service.impl;

import com.fabrica.food.domain.dao.EstadoDao;
import com.fabrica.food.domain.dto.EstadoDto;
import com.fabrica.food.domain.model.Estado;
import com.fabrica.food.domain.model.Restaurante;
import com.fabrica.food.domain.service.EstadoService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public EstadoDto saveCustom(Object estado) {
        ObjectMapper mapper = new ObjectMapper();
        Estado est = mapper.convertValue(estado,Estado.class);

        est = this.save(est);
        EstadoDto retorno = new EstadoDto();
        BeanUtils.copyProperties(est,retorno,"");

        return retorno;
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
