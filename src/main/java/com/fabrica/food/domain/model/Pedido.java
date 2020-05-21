package com.fabrica.food.domain.model;

import com.fabrica.food.domain.enums.StatusPedido;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Pedido {

    private String codigo;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal vaolorTotal;
    private Date dataCriacao;
    private Date dataConfirmacao;
    private Date dataEntrega;
    private Date dataCancelamento;

    private Endereco endereco;
    private List<ItemPedido> itemPedidos;
    private StatusPedido statusPedido;
}
