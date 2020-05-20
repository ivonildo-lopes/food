package com.fabrica.food.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "restaurantes")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public @Data class Restaurante  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(message = "Informe o nome do restaurante")
    @NotEmpty(message = "Informe o nome do restaurante")
    private String nome;

    @Column(name = "taxa_frete")
    @NotNull(message = "Informe a taxa do restaurante")
    private BigDecimal taxaFrete;

    private Boolean aberto;

    private Date dataCadastro;
    private Date dataAtualizacao;

    @ManyToOne
    @JoinColumn(name = "id_cozinha", referencedColumnName = "id")
    @NotNull(message = "Informe a cozinha do restaurante")
    private Cozinha cozinha;

//    @OneToMany
//    @JoinColumn(name = "id_forma_pagamento", referencedColumnName = "id")
//    private List<FormaPagamento> formaPagamentos;

    private Boolean ativo;
}
