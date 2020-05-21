package com.fabrica.food.TestUtil;

import com.fabrica.food.FoodApplication;
import com.fabrica.food.domain.dao.CozinhaDao;
import com.fabrica.food.domain.dao.RestauranteDao;
import com.fabrica.food.domain.dao.RestauranteDaoQueries;
import com.fabrica.food.domain.model.Restaurante;
import com.fabrica.food.infrastructure.daoImpl.RestauranteDaoImpl;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class RestauranteDaoImplTeste {

    public static void main(String[] args) {
        ApplicationContext applicationContext =  new SpringApplicationBuilder(FoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        /**
         * pega o bean tipo RestauranteDao
         */

        RestauranteDaoImpl dao = applicationContext.getBean(RestauranteDaoImpl.class);
        CozinhaDao cozinhaDao = applicationContext.getBean(CozinhaDao.class);


        // listando os objetos
        System.out.println("==================  LISTANDO RESTAURANTE POR NOME E TAXAS ===========================");

        List<Restaurante> lista = dao.findParams("ordones",BigDecimal.valueOf(1l),BigDecimal.valueOf(10l));

        for (Restaurante c: lista) {
            System.out.println( c.getId() + " - " + c.getNome() + " - R$" + c.getTaxaFrete());
        }

    }
}
