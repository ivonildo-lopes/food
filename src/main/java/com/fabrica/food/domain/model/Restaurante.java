package com.fabrica.food.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "restaurantes")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public @Data class Restaurante  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(message = "Informe o nome do restaurante")
    @NotEmpty(message = "Informe o nome do restaurante")
    private String nome;

    @Column(name = "taxa_frete")
    @NotNull(message = "Informe a taxa do restaurante")
    private BigDecimal taxaFrete;

    private Boolean aberto;

    @CreationTimestamp
    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @UpdateTimestamp
    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;

    @ManyToOne
    @JoinColumn(name = "id_cozinha", referencedColumnName = "id")
    @NotNull(message = "Informe a cozinha do restaurante")
    private Cozinha cozinha;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento",
            joinColumns = @JoinColumn(name = "id_restaurante"),
            inverseJoinColumns = @JoinColumn(name =  "id_forma_pagamento"))
    private List<FormaPagamento> formasPagamento;


    @JsonIgnore
    @Embedded
    private Endereco endereco;

    private Boolean ativo;
}
