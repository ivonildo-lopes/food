package com.fabrica.food.api.controller;

import com.fabrica.food.domain.model.Cidade;
import com.fabrica.food.domain.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@RequestMapping(value = "/v1/cidades")
public class CidadeController extends CustomCrudController<Cidade, Long> implements Serializable{

    private CidadeService service;

    @Autowired
    CidadeController(CidadeService service) {
        super(service);
        this.service = service;
    }

}
