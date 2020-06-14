package com.fabrica.food.api.controller;

import com.fabrica.food.domain.dto.RestauranteDto;
import com.fabrica.food.domain.dto.request.RestauranteRequestDto;
import com.fabrica.food.domain.model.Restaurante;
import com.fabrica.food.domain.dto.ResponseBodyDto;
import com.fabrica.food.domain.dto.ResponseDto;
import com.fabrica.food.domain.service.RestauranteService;
import com.fabrica.food.infrastructure.spec.RestauranteFabricaSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/restaurantes")
public class RestauranteController implements Serializable {

    @Autowired
    private RestauranteService service;

    @GetMapping
    public ResponseDto findAll() {
        List<RestauranteDto> restaurantes = this.service.findAll();
        return ResponseDto.response(HttpStatus.OK, ResponseBodyDto.body(restaurantes,"Todos os restaurantes",HttpStatus.OK.value()));
    }

    @GetMapping("/{id}")
    public ResponseDto findById(@PathVariable(value = "id") Long id) {
        RestauranteDto restaurante = this.service.findByIdDto(id);
        return ResponseDto.response(HttpStatus.OK, ResponseBodyDto.body(restaurante,"Restaurante Encontrado",HttpStatus.OK.value()));
    }

    @PostMapping
    public ResponseDto save(@RequestBody @Valid RestauranteRequestDto restauranteRequest) {
        RestauranteDto restaurante = this.service.saveDto(restauranteRequest);
        return ResponseDto.response(HttpStatus.CREATED, ResponseBodyDto.body(restaurante,"Restaurante criado com sucesso!",HttpStatus.CREATED.value()));
    }

    @PutMapping("/{id}")
    public ResponseDto update(@PathVariable Long id, @RequestBody @Valid RestauranteRequestDto restauranteDto) {
        RestauranteDto restaurante = this.service.updateDto(id, restauranteDto);
        return ResponseDto.response(HttpStatus.OK, ResponseBodyDto.body(restaurante,"Restaurante alterado com sucesso!",HttpStatus.OK.value()));
    }

    @DeleteMapping("/{id}")
    public ResponseDto delete(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseDto.response(HttpStatus.OK, ResponseBodyDto.body(null,"Restaurante removido com sucesso!",HttpStatus.NO_CONTENT.value()));
    }

    @PatchMapping("/{id}")
    public ResponseDto updateParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
        Restaurante rest = this.service.updateParcial(id, campos);
        return ResponseDto.response(HttpStatus.OK, ResponseBodyDto.body(rest,"Restaurante alterado com sucesso!",HttpStatus.OK.value()));
    }

    @GetMapping("/com-frete-gratis")
    public ResponseDto findAllRestaurantesFreteGratis(String nome) {
        List<Restaurante> restaurantes = this.service.findAllSpecifitaion(nome);
        return ResponseDto.response(HttpStatus.OK, ResponseBodyDto.body(restaurantes,"Todos os restaurantes",HttpStatus.OK.value()));
    }
}
