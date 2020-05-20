package com.fabrica.food.listener;

import com.fabrica.food.anotacao.TipoDoNotificador;
import com.fabrica.food.enums.TipoNotificador;
import com.fabrica.food.event.ClienteAtivadoEvent;
import com.fabrica.food.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoListener {

    @Autowired
    @TipoDoNotificador(TipoNotificador.NORMAL)
    private Notificador notificador;

    @EventListener
    public void clienteAtivadoListener(ClienteAtivadoEvent event) {
        notificador.notificar(event.getCliente(), "sucesso");
//        System.out.printf("cliente %s agora esta ativo", event.getCliente().getNome());
    }
}
