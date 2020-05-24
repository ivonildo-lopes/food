package com.fabrica.food.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public @Data class Endereco implements Serializable {

    @Column(length = 60)
    private String cep;
    @Column(length = 150)
    private String logradouro;
    @Column(length = 10)
    private String numero;
    @Column(length = 50)
    private String complemento;
    @Column(length = 50)
    private String bairro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cidade")
    private Cidade cidade;
}
