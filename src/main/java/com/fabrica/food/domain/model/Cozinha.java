package com.fabrica.food.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cozinhas")
public @Data class Cozinha extends BaseModel {

    private String nome;
}
