package com.fabrica.food.domain.model;

import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public @Data class BaseModel implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    @Column(nullable = false)
    private Long version = 0l;

    public void updateVersion(Long version, Long versionDoBanco) {
        //TODO implementar exveption
        if(Objects.isNull(version)){
//            throw new BadValueException("a versao deve ser informada");
        }

        if(version < versionDoBanco){
//            throw new BadValueException("Registro ja foi alterado, faça uma nova pesquisa");
        }
        this.version = version + 1;
    }
}
