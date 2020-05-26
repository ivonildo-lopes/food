package com.fabrica.food.domain.model;

import com.fabrica.food.domain.dto.FormaPagamentoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Table(name = "formas_pagamento")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public @Data class FormaPagamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "formas_pagamento_id_seq")
    @SequenceGenerator(name = "formas_pagamento_id_seq", sequenceName = "formas_pagamento_id_seq", initialValue = 1, allocationSize = 1)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(message = "Informe a descrição da forma de pagamento")
    @NotEmpty(message = "Informe a descrição da forma de pagamento")
    @Column(length = 100, nullable = false)
    private String descricao;

    public FormaPagamento(FormaPagamentoDto formaPagamentoDto){
        this.id = formaPagamentoDto.getId();
        this.descricao = formaPagamentoDto.getDescricao();
    }
}
