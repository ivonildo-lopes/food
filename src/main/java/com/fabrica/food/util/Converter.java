package com.fabrica.food.util;

import com.fabrica.food.domain.dto.CidadeDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Map;

public class Converter<T> {

    public static void mapToObject(Map<String, Object> campos, Object object) {

        ObjectMapper mapper = new ObjectMapper();

        Object ObjetoCampos = mapper.convertValue(campos,Object.class);

        campos.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(CidadeDto.class, nomePropriedade);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field,ObjetoCampos);

            ReflectionUtils.setField(field, object, novoValor);
        });
    }
}
