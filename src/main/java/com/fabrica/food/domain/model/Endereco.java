package com.fabrica.food.domain.model;

import lombok.Data;

public @Data class Endereco {

    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private Cidade cidade;
}
