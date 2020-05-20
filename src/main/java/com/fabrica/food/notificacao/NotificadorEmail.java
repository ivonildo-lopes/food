package com.fabrica.food.notificacao;

import com.fabrica.food.anotacao.TipoDoNotificador;
import com.fabrica.food.enums.TipoNotificador;
import com.fabrica.food.domain.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@TipoDoNotificador(TipoNotificador.NORMAL)
@Component
public class NotificadorEmail implements Notificador {

//    @Value("${notificador.email.host-servidor}")
//    private String host;
//
//    @Value("${notificador.email.porta-servidor}")
//    private Integer port;

    @Autowired
    private NotificadorProperties properties;

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.println("host: " + properties.getHostServidor() + " porta: " + properties.getPortaServidor());
        System.out.printf("Olá %s vc esta recebendo esse email de confirmação: %s", cliente.getNome(),mensagem);
    }
}
