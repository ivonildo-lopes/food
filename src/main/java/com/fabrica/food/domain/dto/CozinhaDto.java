package com.fabrica.food.domain.dto;

import com.fabrica.food.domain.model.Restaurante;
import com.fabrica.food.group.Groups;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public @Data class CozinhaDto implements Serializable{

    @EqualsAndHashCode.Include
    @NotNull(groups = Groups.idCozinha.class)
    private Long id;

    @NotBlank(message = "Informe o nome da Cozinha")
    private String nome;

}
