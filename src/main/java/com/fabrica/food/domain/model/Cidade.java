package com.fabrica.food.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Table(name = "cidades")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public @Data class Cidade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotEmpty(message = "Favor informe o nome da cidade")
    @NotNull(message = "Favor informe o nome da cidade")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_estado", referencedColumnName = "id")
    @NotNull(message = "Favor informe o estado da cidade")
    private Estado estado;
}
