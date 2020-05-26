package com.fabrica.food.domain.model;

import com.fabrica.food.domain.enums.StatusPedido;
import com.fabrica.food.domain.service.FormasPagamentoService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "pedidos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public @Data class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "pedidos_id_seq")
    @SequenceGenerator(name = "pedidos_id_seq", sequenceName = "pedidos_id_seq")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private BigDecimal subtotal;

    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @Column(name = "data_criacao", nullable = false)
    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @Column(name = "data_confirmacao")
    private LocalDateTime dataConfirmacao;

    @Column(name = "data_entrega")
    private LocalDateTime dataEntrega;

    @Column(name = "data_cancelamento")
    private LocalDateTime dataCancelamento;

    @Embedded
    @Column(name = "endereco_entrega", nullable = false)
    private Endereco enderecoEntrega;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario cliente;

    @ManyToOne
    @JoinColumn(name = "id_restaurante", nullable = false)
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "id_forma_pagamento", nullable = false)
    private FormaPagamento formaPagamento;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itemPedidos;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private StatusPedido status;
}
