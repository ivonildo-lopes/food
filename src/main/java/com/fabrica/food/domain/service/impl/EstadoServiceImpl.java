package com.fabrica.food.domain.service.impl;

import com.fabrica.food.domain.dao.EstadoDao;
import com.fabrica.food.domain.dto.CidadeDto;
import com.fabrica.food.domain.dto.EstadoDto;
import com.fabrica.food.domain.model.Cidade;
import com.fabrica.food.domain.model.Estado;
import com.fabrica.food.domain.model.Estado;
import com.fabrica.food.domain.model.Restaurante;
import com.fabrica.food.domain.service.EstadoService;
import com.fabrica.food.util.Converter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Primary
public class EstadoServiceImpl implements EstadoService {

    @Autowired
    private EstadoDao dao;

    @Autowired
    Converter<EstadoDto, Estado> converter;

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

    @Override
    public EstadoDto saveCustom(Object bodyRequest) {
        Estado estado = new Estado();
        return saveAndFlushCustom(bodyRequest,estado);
    }

    @Override
    public EstadoDto updateCustom(Long id, Object bodyRequest) {
        Estado estado = this.findById(id);
        return  saveAndFlushCustom(bodyRequest,estado);
    }

    private EstadoDto saveAndFlushCustom(Object bodyRequest, Estado estado){
        EstadoDto estadoDto = new EstadoDto();

        estado = converter.getEntity((Map<String, Object>) bodyRequest,estadoDto,EstadoDto.class,estado);
        this.save(estado);
        estadoDto = converter.getDto(estadoDto,estado);

        return  estadoDto;
    }

}
