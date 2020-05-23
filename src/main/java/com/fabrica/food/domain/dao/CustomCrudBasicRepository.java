package com.fabrica.food.domain.dao;

import com.fabrica.food.domain.model.Cidade;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Service;

import java.util.List;

@NoRepositoryBean
public interface CustomCrudBasicRepository<T, ID> {

    T save(T entity);
    Object saveCustom(Object dto);

    T update(Long id, T entity);
    Object updateCustom(Long id, Object objeto);

    void delete(Long id);

    T findById(Long id);

    List<T> findAll();


}
