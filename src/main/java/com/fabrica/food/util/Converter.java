package com.fabrica.food.util;

import com.fabrica.food.domain.dto.EstadoDto;
import com.fabrica.food.domain.model.Estado;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

@Component
public  class Converter<T,S> {

    /**
     * CONVERTE MAP EM OBJETO JAVA
     * @param mapBodyRequest
     * @param object
     * @param clazz
     */
    public  void mapToObject(Map<String, Object> mapBodyRequest, T object, Class<T> clazz) {

        ObjectMapper mapper = new ObjectMapper();

        Object ObjetoCampos = mapper.convertValue(mapBodyRequest,clazz);

        mapBodyRequest.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(clazz, key);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field,ObjetoCampos);

            ReflectionUtils.setField(field, object, novoValor);
        });
    }

    /**
     * USADO APENAS PARA ENTIDADES SIMPLES SEM RELACIONAMETO
     * @param mapBodyRequest -> Map que veio no corpo da Requisição
     * @param entity -> Entidade do Banco usada para o save e o update
     * @param clazz -> Tipo de Classe que será Convertido(a) os Objetos
     * @param objetoDTO -> Objeto que vai ser convertido do Map
     * @param jpa -> Implementação Jpa para comunicação de Dados usado para save e update
     * @return DTO -> retorno do Tipo DTO que sera informado
     */
    public T saveAndFlushCustom(Map<String, Object> mapBodyRequest, S entity, Class<T> clazz, T objetoDTO, JpaRepository jpa) {

        this.mapToObject(mapBodyRequest, objetoDTO, clazz);
        BeanUtils.copyProperties(objetoDTO,entity);
        jpa.save(entity);
        BeanUtils.copyProperties(entity,objetoDTO);

        return objetoDTO;
    }

}
