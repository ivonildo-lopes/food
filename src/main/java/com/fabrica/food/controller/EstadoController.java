package com.fabrica.food.controller;

import com.fabrica.food.domain.model.Estado;
import com.fabrica.food.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/estados")
public class EstadoController implements Serializable{

    @Autowired
    private EstadoService service;

    @GetMapping
    public List<Estado> findAll(){
        return this.service.findAll();
    }

    @GetMapping(value = "/{id}")
    public Estado findById(@PathVariable(value = "id")  Long id){
        return this.service.findById(id);
    }


}
