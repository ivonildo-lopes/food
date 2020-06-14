package com.fabrica.food.domain.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CozinhaIdRequestDto {

    @NotNull(message = "Informe o Codigo da cozinha")
    private Long id;
}
