package com.fabrica.food.infrastructure.spec;

import com.fabrica.food.domain.model.Restaurante;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestauranteFabricaSpecs {

    public static Specification<Restaurante> comFrenteGratis(){
        return (root,query,builder) -> builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
    }

    public static Specification<Restaurante> comNomeSemelhante(String nome){
        return (root,query,builder) -> builder.like(root.get("nome"), "%" + nome + "%");
    }
}
