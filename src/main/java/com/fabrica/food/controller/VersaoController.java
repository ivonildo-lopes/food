package com.fabrica.food.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/versao")
public class VersaoController {

    @GetMapping
    public String getVersao() {
        return "0.0.1";
    }
}
