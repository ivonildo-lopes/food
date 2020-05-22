package com.fabrica.food.domain.dto;

import com.fabrica.food.domain.model.Estado;
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
public @Data class EstadoDto implements Serializable {

    @EqualsAndHashCode.Include
    private Long id;

    @NotEmpty(message = "Favor informe o nome do estado")
    @NotNull(message = "Favor informe o nome do estado")
    private String nome;

    public EstadoDto(Estado estado){
       this.id = estado.getId();
       this.nome = estado.getNome();
    }
}
