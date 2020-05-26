package com.fabrica.food.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "cozinhas_id_seq")
    @SequenceGenerator(name = "cozinhas_id_seq", sequenceName = "cozinhas_id_seq")
    @EqualsAndHashCode.Include
    private Long id;

    @NotEmpty(message = "Informe o nome da Cozinha")
    @NotNull(message = "Informe o nome da Cozinha")
    @Column(length = 60, nullable = false)
    private String nome;

    @OneToMany(mappedBy = "cozinha")
    @JsonIgnore
    private List<Restaurante> restaurantes;
}
