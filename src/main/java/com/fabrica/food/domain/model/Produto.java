package com.fabrica.food.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;


@Entity
@Table(name = "produtos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public @Data class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "produtos_id_seq")
    @SequenceGenerator(name = "produtos_id_seq", sequenceName = "produtos_id_seq")
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(message = "Favor informe o nome do produto")
    @NotEmpty(message = "Favor informe o nome do produto")
    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 150)
    private String descricao;

    @NotNull(message = "Favor informe o preço do produto")
    private BigDecimal preco;

    private Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "id_restaurante")
    private Restaurante restaurante;
}
