package com.fabrica.food.controller;

import com.fabrica.food.domain.model.Cliente;
import com.fabrica.food.domain.model.Cozinha;
import com.fabrica.food.dto.ResponseBodyDto;
import com.fabrica.food.dto.ResponseDto;
import com.fabrica.food.service.CozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/v1/cozinhas")
public class CozinhaController implements Serializable {

    @Autowired
    private CozinhaService service;

    @GetMapping
    public ResponseDto findAll() {
        List<Cozinha> cozinhas = this.service.findAll();
        return ResponseDto.response(HttpStatus.OK, ResponseBodyDto.body(cozinhas,"Todas as cozinhas",HttpStatus.OK.value()));
    }

    @GetMapping("/{id}")
    public ResponseDto findById(@PathVariable(value = "id") Long id) {
        Cozinha cozinha = this.service.findById(id);
        return ResponseDto.response(HttpStatus.OK, ResponseBodyDto.body(cozinha,"Cozinha Encontrada",HttpStatus.OK.value()));
    }

    @PostMapping
    public ResponseDto save(@RequestBody Cozinha cozinha) {
        Cozinha coz = this.service.save(cozinha);
        return ResponseDto.response(HttpStatus.CREATED, ResponseBodyDto.body(coz,"Cozinha criada com sucesso!",HttpStatus.CREATED.value()));
    }

    @PutMapping("/{id}")
    public ResponseDto update(@PathVariable Long id, @RequestBody Cozinha cozinha) {
        Cozinha coz = this.service.update(id, cozinha);
        return ResponseDto.response(HttpStatus.OK, ResponseBodyDto.body(coz,"Cozinha alterada com sucesso!",HttpStatus.OK.value()));
    }

    @DeleteMapping("/{id}")
    public ResponseDto delete(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseDto.response(HttpStatus.OK, ResponseBodyDto.body(null,"Cozinha removida com sucesso!",HttpStatus.NO_CONTENT.value()));
    }
}