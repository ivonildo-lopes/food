package com.fabrica.food.domain.model;

import com.fabrica.food.domain.dto.CidadeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Table(name = "cidades")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public @Data class Cidade implements Serializable {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @NotEmpty(message = "Favor informe o nome da cidade")
    @NotNull(message = "Favor informe o nome da cidade")
    @Column(length = 60, nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_estado", referencedColumnName = "id")
    @NotNull(message = "Favor informe o estado da cidade")
    private Estado estado;


    public Cidade(CidadeDto cidadeDto){
        this.id = cidadeDto.getId();
        this.nome = cidadeDto.getNome();
    }

}
