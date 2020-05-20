package com.fabrica.food.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "forma_pagamentos")
public @Data class FormaPagamento extends BaseModel{

    private String descricao;
}
