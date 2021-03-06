package com.fabrica.food.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;


@Entity
@Table(name = "itens_pedido")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public @Data class ItemPedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "itens_pedidos_id_seq")
    @SequenceGenerator(name = "itens_pedidos_id_seq", sequenceName = "itens_pedidos_id_seq", initialValue = 1, allocationSize = 1)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private BigDecimal precoUnitario;

    @Column(nullable = false)
    private BigDecimal precoTotal;

    private String observacao;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;
}
