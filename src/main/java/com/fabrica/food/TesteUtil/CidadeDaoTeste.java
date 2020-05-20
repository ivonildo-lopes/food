package com.fabrica.food.TesteUtil;

import com.fabrica.food.FoodApplication;
import com.fabrica.food.domain.dao.EstadoDao;
import com.fabrica.food.domain.dao.CidadeDao;
import com.fabrica.food.domain.model.Cidade;
import com.fabrica.food.domain.model.Restaurante;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CidadeDaoTeste {

    public static void main(String[] args) {
        ApplicationContext applicationContext =  new SpringApplicationBuilder(FoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        /**
         * pega o bean tipo CidadeDao
         */

        CidadeDao dao = applicationContext.getBean(CidadeDao.class);
        EstadoDao estadoDao = applicationContext.getBean(EstadoDao.class);

        // Criando Objeto
        Cidade cidade = new Cidade();
        cidade.setNome("jeri");
        cidade.setEstado(estadoDao.findById(3L).orElse(null));

        dao.save(cidade);


        // pesquisando objeto por ID
        Cidade cc = dao.findById(1l).orElse(null);
        System.out.println( cc.getId() + " - " + cc.getNome());

        // Alterando o Objeto
        cidade.setNome("JERICOACORA");
        dao.save(cidade);


        // deletando objeto
        dao.deleteById(3l);


        // listando os objetos
        System.out.println("==================  LISTANDO TODAS ===========================");

        List<Cidade> lista = dao.findAll();

        for (Cidade c: lista) {
            System.out.println( c.getId() + " - " + c.getNome() + " - " + c.getEstado().getNome());
        }

    }
}
