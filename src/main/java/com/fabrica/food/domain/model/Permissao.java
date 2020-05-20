package com.fabrica.food.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "permissoes")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public @Data class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String nome;
    private String descricao;

//    @ManyToOne
//    @JoinColumn(name = "id_grupo", referencedColumnName = "id")
//    private Grupo grupo;
}
