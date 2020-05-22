package com.fabrica.food.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "usuarios")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public @Data class Usuario  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String nome;

    private String email;

    @CreationTimestamp
    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "usuario_grupo",
    joinColumns = @JoinColumn(name = "id_usuario"),
    inverseJoinColumns = @JoinColumn(name = "id_grupo"))
    private List<Grupo> grupos;
}
