package com.fabrica.food.domain.model;

import com.fabrica.food.domain.dto.EstadoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "estados")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public @Data class Estado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotEmpty(message = "Favor informe o nome do estado")
    @NotNull(message = "Favor informe o nome do estado")
    @Column(length = 60, nullable = false)
    private String nome;

    public Estado(EstadoDto estadoDto){
        this.id = estadoDto.getId();
        this.nome = estadoDto.getNome();
    }
}
