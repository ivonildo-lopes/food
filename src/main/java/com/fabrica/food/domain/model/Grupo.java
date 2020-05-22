package com.fabrica.food.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "grupo_permissoes",
            joinColumns = @JoinColumn(name = "id_grupo"),
            inverseJoinColumns = @JoinColumn(name = "id_permissao")
    )
    private List<Permissao> permissaos;
}
