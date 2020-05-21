package com.fabrica.food.infrastructure.daoImpl;

import com.fabrica.food.domain.dao.RestauranteDao;
import com.fabrica.food.domain.dao.RestauranteDaoQueries;
import com.fabrica.food.domain.model.Restaurante;
import static com.fabrica.food.infrastructure.spec.RestauranteFabricaSpecs.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class RestauranteDaoImpl implements RestauranteDaoQueries {

    @PersistenceContext
    private EntityManager manager;

    @Autowired @Lazy
    private RestauranteDao dao;

    @Override
    public List<Restaurante> findParams(String nome, BigDecimal taxaFrete, BigDecimal taxaFreteFinal){

        Map<String, Object> parameters = new HashMap<>();

        StringBuilder jpql = new StringBuilder();
        jpql.append("FROM Restaurante r where 1=1 ");

        if(!StringUtils.isEmpty(nome)){
            jpql.append(" and r.nome like :nome");

            parameters.put("nome", "%"+nome+"%");
        }

        if(Objects.nonNull(taxaFrete) && Objects.nonNull(taxaFreteFinal)) {
            jpql.append(" and r.taxaFrete between :taxaFrete and :taxaFreteFinal");

            parameters.put("taxaFrete", taxaFrete);
            parameters.put("taxaFreteFinal",taxaFreteFinal);
        }

        Query query = this.manager.createQuery(jpql.toString(),Restaurante.class);
        parameters.forEach((chave,valor) -> query.setParameter(chave,valor));
        return query.getResultList();
    }

    @Override
    public List<Restaurante> findSpecification(String nome) {
        return dao.findAll(comFrenteGratis().and(comNomeSemelhante(nome)));
    }
}
