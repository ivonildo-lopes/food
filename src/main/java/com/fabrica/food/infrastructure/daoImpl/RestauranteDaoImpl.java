package com.fabrica.food.infrastructure.daoImpl;

import com.fabrica.food.domain.dao.RestauranteDaoQueries;
import com.fabrica.food.domain.model.Restaurante;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Repository
public class RestauranteDaoImpl implements RestauranteDaoQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurante> findParams(String nome, BigDecimal taxaFrete, BigDecimal taxaFreteFinal){

        String jpql = "FROM Restaurante r where 1=1";

        if(!StringUtils.isEmpty(nome)) jpql += " and r.nome like :nome";

        if(Objects.nonNull(taxaFrete) && Objects.nonNull(taxaFreteFinal))
            jpql += " and r.taxaFrete between :taxaFrete and :taxaFreteFinal";

        Query query = this.manager.createQuery(jpql,Restaurante.class);

        if(!StringUtils.isEmpty(nome))
            query.setParameter("nome", "%"+nome+"%");

        if(Objects.nonNull(taxaFrete) && Objects.nonNull(taxaFreteFinal)){
            query.setParameter("taxaFrete", taxaFrete);
            query.setParameter("taxaFreteFinal",taxaFreteFinal);
        }

        return query.getResultList();
//        return this.manager.createQuery(jpql, Restaurante.class)
//                .setParameter("nome", "%"+nome+"%")
//                .setParameter("taxaFrete", taxaFrete)
//                .setParameter("taxaFreteFinal",taxaFreteFinal)
//                .getResultList();
    }
}
