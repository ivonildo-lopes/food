package com.fabrica.food.service.impl;

import com.fabrica.food.domain.dao.RestauranteDao;
import com.fabrica.food.domain.model.Restaurante;
import com.fabrica.food.errors.BadValueException;
import com.fabrica.food.errors.NoContentException;
import com.fabrica.food.service.RestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        BeanUtils.copyProperties(restaurante,rest,"id");

        return this.save(rest);
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

        Restaurante restaurante = this.dao.findById(id).orElse(null);

        if(Objects.isNull(restaurante))
            throw new NoContentException("Esse restaurante não existe na nossa base de dados");

        return restaurante;
    }

    @Override
    public List<Restaurante> findAll() {
        return this.dao.findAll();
    }
}
