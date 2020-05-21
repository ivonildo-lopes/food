package com.fabrica.food.dao;

import com.fabrica.food.domain.model.Cozinha;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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


    public Cozinha findById(Long id) {
        TypedQuery<Cozinha> query = manager.createQuery("from Cozinha c where c.id =:id",Cozinha.class);
        query.setParameter("id",id);

        return (Cozinha) query.getSingleResult();
    }

    public List<Cozinha> findByName(String name) {
        return  manager.createQuery("from Cozinha c where c.nome LIKE :nome",Cozinha.class)
                .setParameter("nome","%" +name +"%")
                .getResultList();
    }

    public Cozinha findOne(Long id) {
        return manager.find(Cozinha.class,id);
    }

    @Transactional
    public Cozinha save(Cozinha cozinha) {
        return this.manager.merge(cozinha);
    }


    @Transactional
    public Cozinha update(Long id, Cozinha cozinha) {

        Cozinha coz = this.findOne(id);
        BeanUtils.copyProperties(cozinha,coz,"id");

        return this.manager.merge(coz);
    }

    @Transactional
    public void delete(Cozinha cozinha) {
        this.manager.remove(cozinha);
    }

    @Transactional
    public void delete(Long id) {
        Cozinha cozinha = this.findById(id);
        this.manager.remove(cozinha);
    }



}
