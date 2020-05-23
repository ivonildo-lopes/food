package com.fabrica.food.api.controller;

import com.fabrica.food.domain.model.Estado;
import com.fabrica.food.domain.model.FormaPagamento;
import com.fabrica.food.domain.service.EstadoService;
import com.fabrica.food.domain.service.FormasPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
@RequestMapping(value = "/v1/formasPagamento")
public class FormaPagamentoController extends CustomCrudController<FormaPagamento, Long> implements Serializable{

    @Qualifier("formaPagamentoService")
    private FormasPagamentoService service;

    @Autowired
    FormaPagamentoController(FormasPagamentoService service) {
        super(service);
        this.service = service;
    }


}
