package com.fabrica.food.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente extends BaseModel implements Serializable {

    private String nome;
    private String email;
    private String telefone;
    private Boolean ativo = false;


    @Transient
    public void ativaCliente(){
        this.ativo = true;
    }
}
