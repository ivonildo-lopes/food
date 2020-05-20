package com.fabrica.food.notificacao;

import com.fabrica.food.anotacao.TipoDoNotificador;
import com.fabrica.food.enums.TipoNotificador;
import com.fabrica.food.domain.model.Cliente;
import org.springframework.stereotype.Component;

@TipoDoNotificador(TipoNotificador.URGENTE)
@Component
public class NotificadorSms implements Notificador {

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Olá %s vc esta recebendo esse SMS de confirmação: %s", cliente.getNome(),mensagem);
    }
}
