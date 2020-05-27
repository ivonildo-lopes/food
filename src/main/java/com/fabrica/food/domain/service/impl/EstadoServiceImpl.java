package com.fabrica.food.domain.service.impl;

import com.fabrica.food.domain.dao.EstadoDao;
import com.fabrica.food.domain.dto.CidadeDto;
import com.fabrica.food.domain.dto.EstadoDto;
import com.fabrica.food.domain.exception.BadValueException;
import com.fabrica.food.domain.exception.NegocioException;
import com.fabrica.food.domain.exception.NoContentException;
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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Primary
public class EstadoServiceImpl implements EstadoService {

    @Autowired
    private EstadoDao dao;

    @Autowired
    Converter<EstadoDto, Estado> converter;

    @Override
    public Estado save(Estado estado) {

        if(Objects.isNull(estado)) throw new BadValueException("O Estado não pode ser nulo");

        if(Objects.isNull(estado.getId())) existsEstado(estado);

        return this.dao.save(estado);
    }

    private void existsEstado(Estado estado) {

        if(Objects.isNull(estado.getNome()))
            throw new BadValueException("Favor Informe o nome do estado!");

        if(this.dao.existsByNome(estado.getNome().toUpperCase()))
            throw new NegocioException("Estado " + estado.getNome() +  " já existe");
    }

    @Override
    public Estado update(Long id, Estado estadoBodyRequest) {

        if(Objects.isNull(estadoBodyRequest))
            throw new BadValueException("A Estado não pode ser nulo");

        if(Objects.isNull(estadoBodyRequest.getNome()))
            throw new BadValueException("Informe o nome do estado");

        Estado estado = this.findById(id);

        checkAmbiguos(estadoBodyRequest.getNome(), estado);

        BeanUtils.copyProperties(estadoBodyRequest,estado,"id");

        return this.save(estado);
    }

    private void checkAmbiguos(String nomeEstadoBodyRequest, Estado estadoDataBase) {
        if(!nomeEstadoBodyRequest.toUpperCase().equals(estadoDataBase.getNome().toUpperCase()) &&
                this.dao.countByNome(nomeEstadoBodyRequest.toUpperCase()) > 0){
            throw new NegocioException("Não foi possivel atualizar o Estado " + nomeEstadoBodyRequest.toUpperCase() +  " já existe");
        }
    }

    @Override
    public void delete(Long id) {
        Estado estado = this.findById(id);
        this.dao.delete(estado);
    }

    @Override
    public Estado findById(Long id) {
        if(Objects.isNull(id))
            throw new BadValueException("Informe o código do estado ");


        Estado estado = this.dao.findById(id).orElse(null);

        if(Objects.isNull(estado))
            throw new NoContentException("Esse estado não existe na nossa base");


        return estado;
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
        if(Objects.isNull(bodyRequest)) throw new BadValueException("O estado não pode ser nulo");

        String nomeEstadoRequest =  (String) ((Map) bodyRequest).get("nome");
        if(Objects.isNull(nomeEstadoRequest)) throw new BadValueException("O estado não pode ser nulo");

        Estado estado = this.findById(id);

        this.checkAmbiguos(nomeEstadoRequest, estado);
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
