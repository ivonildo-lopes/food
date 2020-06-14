package com.fabrica.food.domain.dto;

import com.fabrica.food.domain.model.Cozinha;
import com.fabrica.food.domain.model.Endereco;
import com.fabrica.food.domain.model.FormaPagamento;
import com.fabrica.food.domain.model.Produto;
import com.fabrica.food.group.Groups;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public @Data class RestauranteDto {

    @EqualsAndHashCode.Include
    private Long id;

    private String nome;

    private BigDecimal taxaFrete;

    private Boolean aberto;

//    @CreationTimestamp
//    private LocalDateTime dataCadastro;
//
//    @UpdateTimestamp
//    private LocalDateTime dataAtualizacao;

    private CozinhaDto cozinha;

//    @JsonIgnore
//    private List<FormaPagamento> formasPagamento;
//
//    @JsonIgnore
//    private Endereco endereco;
//
//    private Boolean ativo;
//
//    @JsonIgnore
//    private List<Produto> produtos;
}
