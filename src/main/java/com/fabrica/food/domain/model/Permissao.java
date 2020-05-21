package com.fabrica.food.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "permissoes")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public @Data class Permissao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(message = "Favor Informe o nome da Permissao")
    @NotEmpty(message = "Favor Informe o nome da Permissao")
    private String nome;
    private String descricao;

//    @ManyToOne
//    @JoinColumn(name = "id_grupo", referencedColumnName = "id")
//    private Grupo grupo;
}
