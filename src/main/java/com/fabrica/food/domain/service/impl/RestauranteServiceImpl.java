package com.fabrica.food.domain.service.impl;

import com.fabrica.food.domain.dao.CozinhaDao;
import com.fabrica.food.domain.dao.RestauranteDao;
import com.fabrica.food.domain.dto.CozinhaDto;
import com.fabrica.food.domain.dto.RestauranteDto;
import com.fabrica.food.domain.dto.request.RestauranteRequestDto;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RestauranteServiceImpl implements RestauranteService {

    @Autowired
    private RestauranteDao dao;

    @Autowired
    private CozinhaService cozinhaService;

    @Override
    @Transactional
    public Restaurante save(Restaurante restaurante) {
        try{
            restaurante = this.dao.save(restaurante);
            return restaurante;
        }catch (DataIntegrityViolationException e) {
            throw new BadValueException("Erro ao tentar Salvar Restaurante não existe Cozinha de Código: " + restaurante.getCozinha().getId());
        }

    }

    @Override
    @Transactional
    public Restaurante update(Long id, Restaurante restaurante) {

            try{
                Restaurante rest = this.findById(id);
                Cozinha cozinha = this.cozinhaService.findById(restaurante.getCozinha().getId());
                BeanUtils.copyProperties(restaurante,rest,"id","dataCadastro","endereco","formasPagamento");
                rest.setCozinha(cozinha);
                rest = this.save(rest);
            return rest;
        }catch (DataIntegrityViolationException e) {
            throw new BadValueException("Erro ao tentar atualizar Restaurante, não existe Cozinha de Código: " + restaurante.getCozinha().getId());
        }

    }

    @Override
    @Transactional
    public RestauranteDto saveDto(@Valid RestauranteRequestDto restauranteRequest) {
        Restaurante restaurante = new Restaurante();
        restaurante = toModel(restauranteRequest, restaurante);
        this.save(restaurante);
        return toDto(restaurante);
    }

    @Override
    @Transactional
    public RestauranteDto updateDto(Long id, @Valid RestauranteRequestDto restauranteDto) {

        if(id != restauranteDto.getId()){
            throw new BadValueException("Divergencia de ID, verifique o corpo da Requisicao e a URL");
        }

        Restaurante restaurante = this.findById(id);
        restaurante = toModel(restauranteDto, restaurante);
        this.save(restaurante);
        return toDto(restaurante);
    }

    private Restaurante toModel(RestauranteRequestDto restauranteDto, Restaurante restaurante) {
        Cozinha cozinha = this.cozinhaService.findById(restauranteDto.getCozinha().getId());
        BeanUtils.copyProperties(restauranteDto, restaurante,"id","dataCadastro","endereco","formasPagamento");
        restaurante.setCozinha(cozinha);
        return restaurante;
    }

    private RestauranteDto toDto(Restaurante restaurante){
        CozinhaDto cozinhaDto = new CozinhaDto();
        RestauranteDto RestauranteResponse = new RestauranteDto();

        BeanUtils.copyProperties(restaurante.getCozinha(),cozinhaDto);
        BeanUtils.copyProperties(restaurante,RestauranteResponse);

        RestauranteResponse.setCozinha(cozinhaDto);
        return RestauranteResponse;
    }

    @Override
    @Transactional
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
    @Transactional
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
    public RestauranteDto findByIdDto(Long id) {
        return toDto(this.findById(id));
    }

    @Override
    public List<RestauranteDto> findAll() {
        List<Restaurante> restaurantes = this.dao.findAll();
        return restaurantes.stream().map(restaurante -> toDto(restaurante)).collect(Collectors.toList());
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
