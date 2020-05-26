package com.fabrica.food.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "grupos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public @Data class Grupo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "grupo_id_seq")
    @SequenceGenerator(name = "grupo_id_seq", sequenceName = "grupo_id_seq", initialValue = 1, allocationSize = 1)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(message = "Favor informe o nome do Grupo")
    @NotEmpty(message = "Favor informe o nome do Grupo")
    @Column(length = 60, nullable = false)
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
