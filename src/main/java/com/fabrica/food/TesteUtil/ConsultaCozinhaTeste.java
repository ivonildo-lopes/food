package com.fabrica.food.TesteUtil;

import com.fabrica.food.FoodApplication;
import com.fabrica.food.dao.CozinhaHibernate;
import com.fabrica.food.domain.model.Cozinha;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;


import java.util.List;

public class ConsultaCozinhaTeste {

    public static void main(String[] args) {
        ApplicationContext applicationContext =  new SpringApplicationBuilder(FoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        /**
         * pega o bean tipo CozinhaHibernate
         */

        CozinhaHibernate hibernate = applicationContext.getBean(CozinhaHibernate.class);

        List<Cozinha> lista = hibernate.listar();

        for (Cozinha c: lista) {
            System.out.println(c.getNome());
        }
    }
}
