package com.fabrica.food.core;

import com.fabrica.food.api.model.RestauranteMixim;
import com.fabrica.food.domain.model.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMiximModule extends SimpleModule {

    public JacksonMiximModule(){
        setMixInAnnotation(Restaurante.class, RestauranteMixim.class);
    }
}
