package com.fabrica.food.domain.model;

import lombok.Data;

@Data
public class FotoProduto {

    private String nome;
    private String descricao;
    private String contentType;
    private Long tamanho;
}
