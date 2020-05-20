package com.fabrica.food.controller;

import com.fabrica.food.domain.model.Cliente;
import com.fabrica.food.service.AtivacaoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {

    @Autowired
    private AtivacaoClienteService ativacaoClienteService;

    @GetMapping(value = "/")
    public String helloWord() {

    Cliente c = new Cliente("ivo","ivo@gmail.com","85 999343911", false);

    ativacaoClienteService.ativar(c);

    return "Hello World";
    }
}
