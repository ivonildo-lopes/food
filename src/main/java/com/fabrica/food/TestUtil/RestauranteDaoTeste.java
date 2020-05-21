package com.fabrica.food.TestUtil;

import com.fabrica.food.FoodApplication;
import com.fabrica.food.domain.dao.CozinhaDao;
import com.fabrica.food.domain.dao.RestauranteDao;
import com.fabrica.food.domain.model.Restaurante;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class RestauranteDaoTeste {

    public static void main(String[] args) {
        ApplicationContext applicationContext =  new SpringApplicationBuilder(FoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        /**
         * pega o bean tipo RestauranteDao
         */

        RestauranteDao dao = applicationContext.getBean(RestauranteDao.class);
        CozinhaDao cozinhaDao = applicationContext.getBean(CozinhaDao.class);

        // Criando Objeto
        Restaurante rest = new Restaurante();
        rest.setNome("ponto camarao");
        rest.setAberto(true);
        rest.setAtivo(true);
        rest.setDataAtualizacao(new Date());
        rest.setDataCadastro(new Date());
        rest.setTaxaFrete(new BigDecimal(5.5));
        rest.setCozinha(cozinhaDao.findById(2L).orElse(null));

        dao.save(rest);


        // pesquisando objeto por ID
        Restaurante cc = dao.findById(1l).orElse(null);
        System.out.println( cc.getId() + " - " + cc.getNome());

        // Alterando o Objeto
        rest.setNome("PONTO CAMARAO");
        dao.save(rest);


        // deletando objeto
        dao.deleteById(3l);


        // listando os objetos
        System.out.println("==================  LISTANDO TODAS ===========================");

        List<Restaurante> lista = dao.findAll();

        for (Restaurante c: lista) {
            System.out.println( c.getId() + " - " + c.getNome() + " - R$" + c.getTaxaFrete());
        }


        System.out.println("==================  contando restaurantes por cozinha ===========================");

        int qtd = dao.countByCozinhaId(3l);
        System.out.println( "Quantidade de restaurantes por cozinha: " + qtd);

        System.out.println("==================  LISTANO RESTAURANTE POR NOME E ID COZINHA ===========================");

        List<Restaurante> lista2 = dao.findByNameAndCozinhaId("CAMARAO",2L);

        for (Restaurante c: lista2) {
            System.out.println( c.getId() + " - " + c.getNome() + " - R$" + c.getTaxaFrete());
        }

    }
}
