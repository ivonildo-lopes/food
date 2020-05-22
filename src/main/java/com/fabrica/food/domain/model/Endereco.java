package com.fabrica.food.domain.model;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public @Data class Endereco implements Serializable {

    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cidade")
    private Cidade cidade;
}
