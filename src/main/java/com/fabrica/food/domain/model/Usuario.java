package com.fabrica.food.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "usuarios")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public @Data class Usuario  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "usuarios_id_seq")
    @SequenceGenerator(name = "usuarios_id_seq", sequenceName = "usuarios_id_seq", initialValue = 1, allocationSize = 1)
    @EqualsAndHashCode.Include
    private Long id;

    @NotEmpty(message = "Favor informe o nome do usuario")
    @NotNull(message = "Favor informe o nome do usuario")
    @Column(length = 100, nullable = false)
    private String nome;

    @NotEmpty(message = "Favor informe o email do usuario")
    @NotNull(message = "Favor informe o email do usuario")
    @Column(length = 100, nullable = false)
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
