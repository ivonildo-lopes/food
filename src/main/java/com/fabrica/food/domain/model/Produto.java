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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(message = "Favor informe o nome do produto")
    @NotEmpty(message = "Favor informe o nome do produto")
    private String nome;

    private String descricao;

    @NotNull(message = "Favor informe o preço do produto")
    private BigDecimal preco;

    private Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "id_restaurante")
    private Restaurante restaurante;
}
