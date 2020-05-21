package com.fabrica.food.anotation;

import com.fabrica.food.domain.enums.TipoNotificador;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface TipoDoNotificador {

    TipoNotificador value();
}
