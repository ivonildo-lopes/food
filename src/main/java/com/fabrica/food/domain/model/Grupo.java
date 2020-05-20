package com.fabrica.food.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "grupos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public @Data class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    private String nome;

//    @OneToMany(mappedBy = "grupo")
//    private List<Permissao> permissaos;
}
