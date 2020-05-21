package com.fabrica.food.domain.dao;

import com.fabrica.food.domain.model.Cozinha;
import com.fabrica.food.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestauranteDao extends JpaRepository<Restaurante, Long> {

//    @Query("from Restaurante r where r.nome like %:nome% and r.cozinha.id = :idCozinha")
    List<Restaurante> findByNameAndCozinhaId(@Param("nome") String nome, @Param("idCozinha") Long idCozinha);

    Boolean existsByNome(String nome);
    int countByCozinhaId(Long idCozinha);
}
