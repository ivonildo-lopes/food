package com.fabrica.food.domain.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;

@Getter
public class ResponseDto<T> extends ResponseEntity implements Serializable {

    private T data;

    public ResponseDto(HttpStatus status) {
        super(status);
    }

    public ResponseDto(@Nullable Object body, HttpStatus status) {
        super(body, status);
    }

    public ResponseDto(MultiValueMap headers, HttpStatus status) {
        super(headers, status);
    }

    public ResponseDto(@Nullable Object body, @Nullable MultiValueMap headers, HttpStatus status) {
        super(body, headers, status);
    }

    public static ResponseDto response(HttpStatus status,Object data, MultiValueMap headers){
        return new ResponseDto(data, headers, status).setData(data);
    }

    public static ResponseDto response(HttpStatus status,Object data){
        return new ResponseDto(data, status).setData(data);
    }

    public ResponseDto<T> setData(T data){
        this.data = data;
        return this;
    }

}
