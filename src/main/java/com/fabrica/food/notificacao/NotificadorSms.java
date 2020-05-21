package com.fabrica.food.notificacao;

import com.fabrica.food.anotation.TipoDoNotificador;
import com.fabrica.food.domain.model.Usuario;
import com.fabrica.food.domain.enums.TipoNotificador;
import org.springframework.stereotype.Component;

@TipoDoNotificador(TipoNotificador.URGENTE)
@Component
public class NotificadorSms implements Notificador {

    @Override
    public void notificar(Usuario usuario, String mensagem) {
        System.out.printf("Olá %s vc esta recebendo esse SMS de confirmação: %s", usuario.getNome(),mensagem);
    }
}
