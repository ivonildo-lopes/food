package com.fabrica.food.controller;

import com.fabrica.food.domain.model.Restaurante;
import com.fabrica.food.dto.ResponseBodyDto;
import com.fabrica.food.dto.ResponseDto;
import com.fabrica.food.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/v1/restaurantes")
public class RestauranteController implements Serializable {

    @Autowired
    private RestauranteService service;

    @GetMapping
    public ResponseDto findAll() {
        List<Restaurante> restaurantes = this.service.findAll();
        return ResponseDto.response(HttpStatus.OK, ResponseBodyDto.body(restaurantes,"Todos os restaurantes",HttpStatus.OK.value()));
    }

    @GetMapping("/{id}")
    public ResponseDto findById(@PathVariable(value = "id") Long id) {
        Restaurante restaurante = this.service.findById(id);
        return ResponseDto.response(HttpStatus.OK, ResponseBodyDto.body(restaurante,"Restaurante Encontrado",HttpStatus.OK.value()));
    }

    @PostMapping
    public ResponseDto save(@RequestBody Restaurante restaurante) {
        Restaurante rest = this.service.save(restaurante);
        return ResponseDto.response(HttpStatus.CREATED, ResponseBodyDto.body(rest,"Restaurante criado com sucesso!",HttpStatus.CREATED.value()));
    }

    @PutMapping("/{id}")
    public ResponseDto update(@PathVariable Long id, @RequestBody Restaurante restaurante) {
        Restaurante rest = this.service.update(id, restaurante);
        return ResponseDto.response(HttpStatus.OK, ResponseBodyDto.body(rest,"Restaurante alterado com sucesso!",HttpStatus.OK.value()));
    }

    @DeleteMapping("/{id}")
    public ResponseDto delete(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseDto.response(HttpStatus.OK, ResponseBodyDto.body(null,"Restaurante removido com sucesso!",HttpStatus.NO_CONTENT.value()));
    }
}