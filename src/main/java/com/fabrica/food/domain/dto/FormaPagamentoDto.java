package com.fabrica.food.domain.dto;

import com.fabrica.food.domain.model.Estado;
import com.fabrica.food.domain.model.FormaPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public @Data class FormaPagamentoDto implements Serializable {

    @EqualsAndHashCode.Include
    private Long id;

    @NotEmpty(message = "Favor informe a descrição da forma de pagamento")
    @NotNull(message = "Favor informe a descrição da forma de pagamento")
    private String descricao;

    public FormaPagamentoDto(FormaPagamento formaPagamento){
       this.id = formaPagamento.getId();
       this.descricao = formaPagamento.getDescricao();
    }
}
