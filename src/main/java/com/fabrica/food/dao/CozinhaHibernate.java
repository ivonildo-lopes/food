package com.fabrica.food.dao;

import com.fabrica.food.domain.model.Cozinha;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


/**
 * classe criada a titulo de estudo e uma possivel forma de usar hibarte
 */
@Component
public class CozinhaHibernate {

    @PersistenceContext
    private EntityManager manager;

    public List<Cozinha> listar() {
        TypedQuery<Cozinha> query = manager.createQuery("from Cozinha",Cozinha.class);

        return query.getResultList();
    }
}
