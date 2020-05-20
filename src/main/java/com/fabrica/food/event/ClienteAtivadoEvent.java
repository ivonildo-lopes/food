package com.fabrica.food.event;

import com.fabrica.food.domain.model.Cliente;
import lombok.Getter;

@Getter
public class ClienteAtivadoEvent {

    private Cliente cliente;

    public ClienteAtivadoEvent(Cliente cliente) {
        super();
        this.cliente = cliente;
    }

}
