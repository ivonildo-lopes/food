package com.fabrica.food.TesteUtil;

import com.fabrica.food.FoodApplication;
import com.fabrica.food.domain.dao.FormaPagamentoDao;
import com.fabrica.food.domain.dao.EstadoDao;
import com.fabrica.food.domain.model.FormaPagamento;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class FormaPagamentoDaoTeste {

    public static void main(String[] args) {
        ApplicationContext applicationContext =  new SpringApplicationBuilder(FoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        /**
         * pega o bean tipo FormaPagamentoDao
         */

        FormaPagamentoDao dao = applicationContext.getBean(FormaPagamentoDao.class);
        

        // Criando Objeto
        FormaPagamento pagamento = new FormaPagamento();
        pagamento.setDescricao("pic-pay");

        dao.save(pagamento);


        // pesquisando objeto por ID
        FormaPagamento cc = dao.findById(1l).orElse(null);
        System.out.println( cc.getId() + " - " + cc.getDescricao());

        // Alterando o Objeto
        pagamento.setDescricao("PIC-PAY");
        dao.save(pagamento);


        // deletando objeto
        dao.deleteById(3l);


        // listando os objetos
        System.out.println("==================  LISTANDO TODAS ===========================");

        List<FormaPagamento> lista = dao.findAll();

        for (FormaPagamento c: lista) {
            System.out.println( c.getId() + " - " + c.getDescricao());
        }

    }
}
