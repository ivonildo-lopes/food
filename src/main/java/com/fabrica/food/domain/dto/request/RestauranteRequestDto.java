package com.fabrica.food.domain.dto.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
public class RestauranteRequestDto {

    private Long id;

    @NotBlank(message = "Informe o nome do restaurante")
    private String nome;

    @PositiveOrZero(message = "O valor minimo da taxa é 0")
    @NotNull(message = "Informe a taxa do restaurante")
    private BigDecimal taxaFrete;

    private boolean aberto;

    private boolean ativo;

    @Valid
    @NotNull(message = "Informe a cozinha do restaurante")
    private CozinhaIdRequestDto cozinha;
}
