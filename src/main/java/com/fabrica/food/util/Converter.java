package com.fabrica.food.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

@Component
public  class Converter<T> {

    public  void mapToObject(Map<String, Object> campos, T object, Class<T> clazz) {

        ObjectMapper mapper = new ObjectMapper();

        Object ObjetoCampos = mapper.convertValue(campos,clazz);

        campos.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(clazz, key);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field,ObjetoCampos);

            ReflectionUtils.setField(field, object, novoValor);
        });
    }
}
