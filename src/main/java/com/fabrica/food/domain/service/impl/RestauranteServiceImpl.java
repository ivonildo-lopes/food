package com.fabrica.food.domain.service.impl;

import com.fabrica.food.domain.dao.CozinhaDao;
import com.fabrica.food.domain.dao.RestauranteDao;
import com.fabrica.food.domain.model.Cozinha;
import com.fabrica.food.domain.model.Restaurante;
import com.fabrica.food.domain.exception.BadValueException;
import com.fabrica.food.domain.exception.NoContentException;
import com.fabrica.food.domain.service.CozinhaService;
import com.fabrica.food.domain.service.RestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
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

    @Autowired
    private CozinhaService cozinhaService;

    @Override
    public Restaurante save(Restaurante restaurante) {
        return this.dao.save(restaurante);
    }

    @Override
    public Restaurante update(Long id, Restaurante restaurante) {
        Restaurante rest = this.findById(id);
        try{
            Cozinha cozinha = this.cozinhaService.findById(restaurante.getCozinha().getId());
            BeanUtils.copyProperties(restaurante,rest,"id","dataCadastro","endereco","formasPagamento");
            rest.setCozinha(cozinha);
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
        List<Restaurante> restaurantes = this.dao.findAll();

        System.out.println("O nome da cozinha é: ");
        System.out.println(restaurantes.stream().findFirst().get().getCozinha().getNome());

        System.out.println("Nossa cidade é ");
        System.out.println(restaurantes.stream().findFirst().get().getEndereco().getCidade().getNome());

        return restaurantes;
    }

    @Override
    public List<Restaurante> findAllSpecifitaion(Specification<Restaurante> comFreteGratis, Specification<Restaurante> comNomeSemelhante) {
        return this.dao.findAll(comFreteGratis.and(comNomeSemelhante));
    }

    @Override
    public List<Restaurante> findAllSpecifitaion(String nome) {
        return this.dao.findSpecification(nome);
    }

    @Override
    public Restaurante findFirst() {
        return this.dao.findFirst().orElse(null);
    }


}
