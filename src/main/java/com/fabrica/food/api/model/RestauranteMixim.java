package com.fabrica.food.api.model;

import com.fabrica.food.domain.model.Cozinha;
import com.fabrica.food.domain.model.Endereco;
import com.fabrica.food.domain.model.FormaPagamento;
import com.fabrica.food.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RestauranteMixim {

    @JsonIgnore
    private LocalDateTime dataCadastro;

//    @JsonIgnore
    private LocalDateTime dataAtualizacao;

    @JsonIgnoreProperties(value = {"nome"}, allowGetters = true)
    private Cozinha cozinha;

    @JsonIgnore
    private List<FormaPagamento> formasPagamento;

    @JsonIgnore
    private Endereco endereco;

    @JsonIgnore
    private List<Produto> produtos;
}
