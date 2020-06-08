package com.fabrica.food.domain.model;

import com.fabrica.food.group.Groups;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cozinhas")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public @Data class Cozinha implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cozinhas_id_seq")
    @SequenceGenerator(name = "cozinhas_id_seq", sequenceName = "cozinhas_id_seq", initialValue = 1, allocationSize = 1)
    @EqualsAndHashCode.Include
    @NotNull(groups = Groups.idCozinha.class)
    private Long id;


    @NotBlank(message = "Informe o nome da Cozinha")
    @Column(length = 60, nullable = false)
    private String nome;

    @OneToMany(mappedBy = "cozinha")
    @JsonIgnore
    private List<Restaurante> restaurantes;
}
