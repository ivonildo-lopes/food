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

//    @GetMapping
//    public ResponseDto findAll() {
//        List<Estado> estados = this.service.findAll();
//        return ResponseDto.response(HttpStatus.OK, ResponseBodyDto.body(estados,"Todos os estados",HttpStatus.OK.value()));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseDto findById(@PathVariable(value = "id") Long id) {
//        Estado estado = this.service.findById(id);
//        return ResponseDto.response(HttpStatus.OK, ResponseBodyDto.body(estado,"Estado Encontrado",HttpStatus.OK.value()));
//    }
//
//    @PostMapping
//    public ResponseDto save(@RequestBody Estado estado) {
//        Estado est = this.service.save(estado);
//        return ResponseDto.response(HttpStatus.CREATED, ResponseBodyDto.body(est,"Estado criado com sucesso!",HttpStatus.CREATED.value()));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseDto update(@PathVariable Long id, @RequestBody Estado estado) {
//        Estado est = this.service.update(id, estado);
//        return ResponseDto.response(HttpStatus.OK, ResponseBodyDto.body(est,"Estado alterado com sucesso!",HttpStatus.OK.value()));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseDto delete(@PathVariable Long id) {
//        this.service.delete(id);
//        return ResponseDto.response(HttpStatus.OK, ResponseBodyDto.body(null,"Estado removido com sucesso!",HttpStatus.NO_CONTENT.value()));
//    }
}
