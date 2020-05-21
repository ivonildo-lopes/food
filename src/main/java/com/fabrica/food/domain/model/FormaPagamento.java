package com.fabrica.food.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Table(name = "forma_pagamentos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public @Data class FormaPagamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(message = "Informe a descrição da forma de pagamento")
    @NotEmpty(message = "Informe a descrição da forma de pagamento")
    private String descricao;
}
