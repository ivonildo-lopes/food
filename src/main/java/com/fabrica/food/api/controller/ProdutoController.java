package com.fabrica.food.api.controller;

import com.fabrica.food.domain.dto.ResponseBodyDto;
import com.fabrica.food.domain.dto.ResponseDto;
import com.fabrica.food.domain.model.Produto;
import com.fabrica.food.domain.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/v1/produtos")
public class ProdutoController implements Serializable {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public ResponseDto findAll() {
        List<Produto> produtos = this.service.findAll();
        return ResponseDto.response(HttpStatus.OK, ResponseBodyDto.body(produtos,"Todos os produtos",HttpStatus.OK.value()));
    }

    @GetMapping("/{id}")
    public ResponseDto findById(@PathVariable(value = "id") Long id) {
        Produto produto = this.service.findById(id);
        return ResponseDto.response(HttpStatus.OK, ResponseBodyDto.body(produto,"Produto Encontrado",HttpStatus.OK.value()));
    }

    @GetMapping("/by-name")
    public ResponseDto findByName(@RequestParam("nome") String nome) {
        List<Produto> produtos = this.service.findByName(nome);
        return ResponseDto.response(HttpStatus.OK, ResponseBodyDto.body(produtos,"Produto Encontrado",HttpStatus.OK.value()));
    }

    @PostMapping
    public ResponseDto save(@RequestBody Produto produto) {
        Produto coz = this.service.save(produto);
        return ResponseDto.response(HttpStatus.CREATED, ResponseBodyDto.body(coz,"Produto criado com sucesso!",HttpStatus.CREATED.value()));
    }

    @PutMapping("/{id}")
    public ResponseDto update(@PathVariable Long id, @RequestBody Produto produto) {
        Produto coz = this.service.update(id, produto);
        return ResponseDto.response(HttpStatus.OK, ResponseBodyDto.body(coz,"Produto alterado com sucesso!",HttpStatus.OK.value()));
    }

    @DeleteMapping("/{id}")
    public ResponseDto delete(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseDto.response(HttpStatus.OK, ResponseBodyDto.body(null,"Produto removido com sucesso!",HttpStatus.NO_CONTENT.value()));
    }
}
