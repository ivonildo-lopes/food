package com.fabrica.food.service;

import com.fabrica.food.event.ClienteAtivadoEvent;
import com.fabrica.food.domain.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class AtivacaoClienteService implements Serializable {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void ativar(Cliente cliente) {
        cliente.ativaCliente();
        eventPublisher.publishEvent(new ClienteAtivadoEvent(cliente));
    }

    //    @Autowired
//    @TipoDoNotificador(TipoNotificador.NORMAL)
//    private Notificador notificador;
//
//    public Cliente ativaCliente(Cliente cliente) {
//        cliente.ativaCliente();
//        notificador.notificar(cliente, "sucesso");
//        return cliente;
//    }
}
