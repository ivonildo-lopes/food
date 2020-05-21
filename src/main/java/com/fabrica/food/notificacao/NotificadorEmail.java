package com.fabrica.food.notificacao;

import com.fabrica.food.anotation.TipoDoNotificador;
import com.fabrica.food.domain.model.Usuario;
import com.fabrica.food.domain.enums.TipoNotificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@TipoDoNotificador(TipoNotificador.NORMAL)
@Component
public class NotificadorEmail implements Notificador {

    @Autowired
    private NotificadorProperties properties;

    @Override
    public void notificar(Usuario usuario, String mensagem) {
        System.out.println("host: " + properties.getHostServidor() + " porta: " + properties.getPortaServidor());
        System.out.printf("Olá %s vc esta recebendo esse email de confirmação: %s", usuario.getNome(),mensagem);
    }
}
