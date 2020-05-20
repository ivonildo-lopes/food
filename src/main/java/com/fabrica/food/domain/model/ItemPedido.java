package com.fabrica.food.domain.model;

import lombok.Data;

import java.math.BigDecimal;


public @Data class ItemPedido {

    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal precoTotal;
    private String observacao;
    private ItemPedido pedido;
}
