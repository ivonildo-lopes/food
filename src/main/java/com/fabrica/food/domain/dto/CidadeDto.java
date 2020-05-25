package com.fabrica.food.domain.dto;

import com.fabrica.food.domain.model.Cidade;
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
public @Data class CidadeDto implements Serializable {

    @EqualsAndHashCode.Include
    private Long id;

    @NotEmpty(message = "Favor informe o nome do estado")
    @NotNull(message = "Favor informe o nome do estado")
    private String nome;

    @NotNull(message = "Favor informe o código do estado")
    private Long idEstado;

    private EstadoDto estado;

    public CidadeDto(Cidade cidade){
        this.id = cidade.getId();
        this.nome = cidade.getNome();
        this.idEstado = cidade.getEstado().getId();
        this.estado = new EstadoDto(cidade.getEstado());
    }
}
