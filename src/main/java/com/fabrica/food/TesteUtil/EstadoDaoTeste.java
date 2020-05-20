package com.fabrica.food.TesteUtil;

import com.fabrica.food.FoodApplication;
import com.fabrica.food.domain.dao.CozinhaDao;
import com.fabrica.food.domain.dao.EstadoDao;
import com.fabrica.food.domain.model.Estado;
import com.fabrica.food.domain.model.Estado;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class EstadoDaoTeste {

    public static void main(String[] args) {
        ApplicationContext applicationContext =  new SpringApplicationBuilder(FoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        /**
         * pega o bean tipo EstadoDao
         */

        EstadoDao dao = applicationContext.getBean(EstadoDao.class);
        CozinhaDao cozinhaDao = applicationContext.getBean(CozinhaDao.class);

        // Criando Objeto
        Estado estado = new Estado();
        estado.setNome("bahia");

        dao.save(estado);


        // pesquisando objeto por ID
        Estado cc = dao.findById(1l).orElse(null);
        System.out.println( cc.getId() + " - " + cc.getNome());

        // Alterando o Objeto
        estado.setNome("BAHIA");
        dao.save(estado);


        // deletando objeto
        dao.deleteById(4l);


        // listando os objetos
        System.out.println("==================  LISTANDO TODAS ===========================");

        List<Estado> lista = dao.findAll();

        for (Estado c: lista) {
            System.out.println( c.getId() + " - " + c.getNome());
        }

    }
}
