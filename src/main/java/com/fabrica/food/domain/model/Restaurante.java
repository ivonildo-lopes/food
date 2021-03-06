package com.fabrica.food.domain.model;

import com.fabrica.food.group.Groups;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "restaurantes")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public @Data class Restaurante  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurantes_id_seq")
    @SequenceGenerator(name = "restaurantes_id_seq", sequenceName = "restaurantes_id_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "Informe o nome do restaurante")
    @Column(length = 100, nullable = false)
    private String nome;

//    @DecimalMin(value = "0", message = "O valor minimo da taxa é 0")
    @PositiveOrZero(message = "O valor minimo da taxa é 0")
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

//    @JsonIgnore
    @Valid
    @JsonIgnoreProperties(value = {"nome"}, allowGetters = true)
    @ManyToOne
    @JoinColumn(name = "id_cozinha", referencedColumnName = "id")
    @NotNull(message = "Informe a cozinha do restaurante")
    @ConvertGroup(from = Default.class,to = Groups.idCozinha.class)
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

    @JsonIgnore
    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos;
}
