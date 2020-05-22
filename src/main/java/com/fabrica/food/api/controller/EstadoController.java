package com.fabrica.food.api.controller;

import com.fabrica.food.domain.model.Estado;
import com.fabrica.food.domain.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@RequestMapping(value = "/v1/estados")
public class EstadoController extends CustomCrudController<Estado, Long> implements Serializable{

    @Qualifier("estadoService")
    private EstadoService service;

    @Autowired
    EstadoController(EstadoService service) {
        super(service);
        this.service = service;
    }


}
