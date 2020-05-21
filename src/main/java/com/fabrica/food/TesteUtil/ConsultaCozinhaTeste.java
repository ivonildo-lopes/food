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



        Cozinha coz = new Cozinha();
        coz.setNome("Brasileira");

        Cozinha coz4 = new Cozinha();
        coz4.setNome("ITALIANA");

        hibernate.save(coz);
        hibernate.save(coz4);


        Cozinha cc = hibernate.findById(2l);
        System.out.println( cc.getId() + " - " + cc.getNome());

        Cozinha ccc = hibernate.findById(3l);
        System.out.println( ccc.getId() + " - " + ccc.getNome());

        hibernate.update(3l,new Cozinha(3l,"BRASILEIRA"));

        hibernate.delete(4l);


        System.out.println("==================  LISTANDO TODAS ===========================");

        List<Cozinha> lista = hibernate.listar();

        for (Cozinha c: lista) {
            System.out.println( c.getId() + " - " + c.getNome());
        }

        System.out.println("==================  LISTANDO POR NOME ===========================");

        List<Cozinha> listaPorNome = hibernate.findByName("BRASIL");

        for (Cozinha c: listaPorNome) {
            System.out.println( c.getId() + " - " + c.getNome());
        }

    }
}
