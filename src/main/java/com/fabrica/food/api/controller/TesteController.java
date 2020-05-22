package com.fabrica.food.api.controller;

import com.fabrica.food.domain.dto.ResponseBodyDto;
import com.fabrica.food.domain.dto.ResponseDto;
import com.fabrica.food.domain.model.Produto;
import com.fabrica.food.domain.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@RequestMapping("/test")
public class TesteController extends CustomCrudController<Produto, Long> implements Serializable {

    @Qualifier("produtoService")
    private ProdutoService produtoService;

   @Autowired
    TesteController(ProdutoService produtoService){
        super(produtoService);
        this.produtoService = produtoService;
    }


    @GetMapping("/iddd/{id}")
    public ResponseDto findById(@PathVariable(value = "id") Long id) {
        Produto object = this.produtoService.findById(id);
        return ResponseDto.response(HttpStatus.OK, ResponseBodyDto.body(object,"Objeto Encontrado",HttpStatus.OK.value()));
    }
}
