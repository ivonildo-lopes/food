package com.fabrica.food.TestUtil;

import com.fabrica.food.FoodApplication;
import com.fabrica.food.domain.dao.CozinhaDao;
import com.fabrica.food.domain.model.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.function.BinaryOperator;

public class CozinhaDaoTeste {

    public static void main(String[] args) {
        ApplicationContext applicationContext =  new SpringApplicationBuilder(FoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        /**
         * pega o bean tipo CozinhaDao
         */

        CozinhaDao dao = applicationContext.getBean(CozinhaDao.class);

        Cozinha coz = new Cozinha();
        coz.setNome("Brasileira");

        Cozinha coz1 = new Cozinha();
        coz1.setNome("Argentina");

        dao.save(coz);
        dao.save(coz1);

        Cozinha cc = dao.findById(2l).orElse(null);
        System.out.println( cc.getId() + " - " + cc.getNome());

        Cozinha ccc = dao.findById(3l).orElse(null);
        System.out.println( ccc.getId() + " - " + ccc.getNome());

        coz.setNome("BRASILEIRA");
        dao.save(coz);

        dao.deleteById(4l);

        System.out.println("==================  LISTANDO TODAS ===========================");

        List<Cozinha> lista = dao.findAll();

        for (Cozinha c: lista) {
            System.out.println( c.getId() + " - " + c.getNome());
        }

        System.out.println("==================  VERIFICANDO SE NOME JA EXISTE ===========================");

        boolean exists = dao.existsByNome("ARGENTINA");

        String result = exists ? "SIM" : "NAO";
        System.out.println("NOME EXIST? " + result);


    }
}
