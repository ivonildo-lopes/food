package com.fabrica.food.notificacao;

import com.fabrica.food.domain.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public interface Notificador {

    void notificar(Cliente cliente, String mensagem);
}
