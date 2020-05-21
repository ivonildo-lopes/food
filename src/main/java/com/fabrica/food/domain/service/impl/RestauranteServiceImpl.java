package com.fabrica.food.domain.service.impl;

import com.fabrica.food.domain.dao.RestauranteDao;
import com.fabrica.food.domain.model.Restaurante;
import com.fabrica.food.domain.exception.BadValueException;
import com.fabrica.food.domain.exception.NoContentException;
import com.fabrica.food.domain.service.RestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class RestauranteServiceImpl implements RestauranteService {

    @Autowired
    private RestauranteDao dao;

    @Override
    public Restaurante save(Restaurante restaurante) {
        return this.dao.save(restaurante);
    }

    @Override
    public Restaurante update(Long id, Restaurante restaurante) {
        Restaurante rest = this.findById(id);
        try{
            BeanUtils.copyProperties(restaurante,rest,"id");
            rest = this.save(rest);
        }catch (DataIntegrityViolationException e) {
            throw new BadValueException("Erro ao tentar atualizar Restaurante não existe Cozinha de Código: " + restaurante.getCozinha().getId());
        }

        return rest;
    }

    @Override
    public Restaurante updateParcial(Long id, Map<String, Object> campos) {
        Restaurante rest = this.findById(id);

        updateOnlyField(campos, rest);
        return update(id,rest);
    }

    private void updateOnlyField(Map<String, Object> campos, Restaurante restauranteDestino) {

        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteCampos = objectMapper.convertValue(campos,Restaurante.class);


        campos.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            field.setAccessible(true);


            Object novoValor = ReflectionUtils.getField(field,restauranteCampos);

            ReflectionUtils.setField(field, restauranteDestino, novoValor);
        });
    }

    @Override
    public void delete(Long id) {
        Restaurante restaurante = this.findById(id);
        this.dao.delete(restaurante);
    }

    @Override
    public Restaurante findById(Long id) {

        if(Objects.isNull(id))
            throw new BadValueException("Favor informe o código da restaurante");

        Restaurante restaurante = this.dao.findById(id).orElseThrow(() ->
             new NoContentException("Esse restaurante não existe na nossa base de dados")
        );

        return restaurante;
    }

    @Override
    public List<Restaurante> findAll() {
        return this.dao.findAll();
    }
}