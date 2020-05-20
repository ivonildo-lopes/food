package com.fabrica.food.controller;

import com.fabrica.food.domain.model.Cidade;
import com.fabrica.food.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/cidades")
public class CidadeController implements Serializable{

    @Autowired
    private CidadeService service;

    @GetMapping
    public List<Cidade> findAll(){
        return this.service.findAll();
    }

    @GetMapping(value = "/{id}")
    public Cidade findById(@PathVariable(value = "id")  Long id){
        return this.service.findById(id);
    }

    
}
