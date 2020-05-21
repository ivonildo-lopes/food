package com.fabrica.food.notificacao;

import com.fabrica.food.domain.model.Cliente;
import com.fabrica.food.domain.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public interface Notificador {

    void notificar(Usuario usuario, String mensagem);
}
