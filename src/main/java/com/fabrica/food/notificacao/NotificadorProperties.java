package com.fabrica.food.notificacao;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("notificador.email")
public @Data class NotificadorProperties {

    private String hostServidor;
    private Integer portaServidor;
}
