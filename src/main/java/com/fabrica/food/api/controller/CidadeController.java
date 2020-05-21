package com.fabrica.food.api.controller;

import com.fabrica.food.domain.model.Cidade;
import com.fabrica.food.domain.dto.ResponseBodyDto;
import com.fabrica.food.domain.dto.ResponseDto;
import com.fabrica.food.domain.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/cidades")
public class CidadeController implements Serializable{

    @Autowired
    private CidadeService service;

    @GetMapping
    public ResponseDto findAll() {
        List<Cidade> cidades = this.service.findAll();
        return ResponseDto.response(HttpStatus.OK, ResponseBodyDto.body(cidades,"Todas os cidades",HttpStatus.OK.value()));
    }

    @GetMapping("/{id}")
    public ResponseDto findById(@PathVariable(value = "id") Long id) {
        Cidade cidade = this.service.findById(id);
        return ResponseDto.response(HttpStatus.OK, ResponseBodyDto.body(cidade,"Cidade Encontrada",HttpStatus.OK.value()));
    }

    @PostMapping
    public ResponseDto save(@RequestBody Cidade cidade) {
        Cidade cid = this.service.save(cidade);
        return ResponseDto.response(HttpStatus.CREATED, ResponseBodyDto.body(cid,"Cidade criada com sucesso!",HttpStatus.CREATED.value()));
    }

    @PutMapping("/{id}")
    public ResponseDto update(@PathVariable Long id, @RequestBody Cidade cidade) {
        Cidade cid = this.service.update(id, cidade);
        return ResponseDto.response(HttpStatus.OK, ResponseBodyDto.body(cid,"Cidade alterada com sucesso!",HttpStatus.OK.value()));
    }

    @DeleteMapping("/{id}")
    public ResponseDto delete(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseDto.response(HttpStatus.OK, ResponseBodyDto.body(null,"Cidade removida com sucesso!",HttpStatus.NO_CONTENT.value()));
    }
    
}
