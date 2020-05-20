package com.fabrica.food.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "clientes")
public class Cliente extends BaseModel {

    private String nome;
    private String email;
    private String telefone;
    private Boolean ativo = false;


    @Transient
    public void ativaCliente(){
        this.ativo = true;
    }
}
