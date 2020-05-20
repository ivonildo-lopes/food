package com.fabrica.food.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
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

    private String nome;

    @Column(name = "taxa_frete")
    private BigDecimal taxaFrete;

    private Boolean aberto;

    private Date dataCadastro;
    private Date dataAtualizacao;

    @ManyToOne
    @JoinColumn(name = "id_cozinha", referencedColumnName = "id")
    private Cozinha cozinha;

//    @OneToMany
//    @JoinColumn(name = "id_forma_pagamento", referencedColumnName = "id")
//    private List<FormaPagamento> formaPagamentos;

    private Boolean ativo;
}
