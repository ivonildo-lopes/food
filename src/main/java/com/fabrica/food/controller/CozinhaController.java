package com.fabrica.food.controller;

import com.fabrica.food.domain.model.Cliente;
import com.fabrica.food.domain.model.Cozinha;
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
    public List<Cozinha> findAll() {
        return this.service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseDto findById(@PathVariable(value = "id") Long id) {
        Cozinha cozinha = this.service.findById(id);
//        return ResponseEntity.status(HttpStatus.OK).body(cozinha);
        return ResponseDto.response(ResponseEntity.status(HttpStatus.CREATED).body(null),cozinha,"Usuario Encontrado");
    }

    @PostMapping
    public Cozinha save(@RequestBody Cozinha cozinha) {
        return this.service.save(cozinha);
    }

    @PutMapping("/{id}")
    public Cozinha update(@PathVariable(value = "id") Long id, @RequestBody Cozinha cozinha) {
        return this.service.update(id, cozinha);
    }
}
