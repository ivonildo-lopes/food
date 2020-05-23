package com.fabrica.food.api.controller;

import com.fabrica.food.domain.dao.CustomCrudBasicRepository;
import com.fabrica.food.domain.dto.ResponseBodyDto;
import com.fabrica.food.domain.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
public class CustomCrudController<T, ID> implements Serializable {

    private CustomCrudBasicRepository<T, ID> service;

    CustomCrudController(CustomCrudBasicRepository service){
        this.service = service;
    }

    @GetMapping
    public ResponseDto findAll() {
        List<T> lista = this.service.findAll();
        return ResponseDto.response(HttpStatus.OK, ResponseBodyDto.body(lista,"Todo(a)s listados",HttpStatus.OK.value()));
    }

    @GetMapping("/{id}")
    public ResponseDto findById(@PathVariable(value = "id") Long id) {
        T object = this.service.findById(id);
        return ResponseDto.response(HttpStatus.OK, ResponseBodyDto.body(object,"Objeto Encontrado",HttpStatus.OK.value()));
    }

    @PostMapping("/")
    public ResponseDto save(@RequestBody T entity) {
        T coz = this.service.save(entity);
        return ResponseDto.response(HttpStatus.CREATED, ResponseBodyDto.body(coz,"Salvo(a) com sucesso!",HttpStatus.CREATED.value()));
    }

    @PutMapping("/{id}")
    public ResponseDto update(@PathVariable Long id, @RequestBody T entity) {
        T coz = this.service.update(id, entity);
        return ResponseDto.response(HttpStatus.OK, ResponseBodyDto.body(coz,"Alterado(a) com sucesso!",HttpStatus.OK.value()));
    }

    @DeleteMapping("/{id}")
    public ResponseDto delete(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseDto.response(HttpStatus.OK, ResponseBodyDto.body(null,"Removido(a) com sucesso!",HttpStatus.NO_CONTENT.value()));
    }

    @PostMapping("/save-custom/")
    public ResponseDto saveCustom(@RequestBody Object objeto) {
        Object coz = this.service.saveCustom(objeto);
        return ResponseDto.response(HttpStatus.CREATED, ResponseBodyDto.body(coz,"Salvo(a) com sucesso!",HttpStatus.CREATED.value()));
    }

    @PutMapping("/update-custom/{id}")
    public ResponseDto updateCustom(@PathVariable Long id, @RequestBody Object objeto) {
        Object coz = this.service.updateCustom(id,objeto);
        return ResponseDto.response(HttpStatus.CREATED, ResponseBodyDto.body(coz,"Atualizado(a) com sucesso!",HttpStatus.CREATED.value()));
    }
}
