package com.fabrica.food.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "restaurantes")
public @Data class Restaurante extends BaseModel {

    private String nome;

    private BigDecimal taxaFrete;

    private Boolean aberto;

    private Date dataCadastro;
    private Date dataAtualizacao;

    private Cozinha cozinha;

    @OneToMany
    @JoinColumn(name = "id_forma_pagamento", referencedColumnName = "id")
    private List<FormaPagamento> formaPagamentos;

    private Boolean ativo;
}
