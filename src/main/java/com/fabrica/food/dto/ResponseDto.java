package com.fabrica.food.dto;

import com.google.common.collect.Lists;
import com.sun.jndi.toolkit.url.Uri;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

@Getter
public class ResponseDto<T> extends ResponseEntity implements Serializable {

    private T data;
    private ResponseEntity<T> reponseEntity;
    private URI uri;
    private String menssage;
    private Integer status;

    private List<String> errors;
    private List<String> warns;
    private List<String> infos;

    public ResponseDto(HttpStatus status) {
        super(status);
    }


    public static ResponseDto response(HttpStatus status, ResponseEntity<?> reponseEntity, Object data, String menssage, URI uri, Integer status1){
        return new ResponseDto(status)
                .setResponseEntity(reponseEntity)
                .setData(data)
                .setMenssage(menssage)
                .setUri(uri)
                .setStatus(status1);
    }

    public static ResponseDto response(ResponseEntity<?> reponseEntity, Object data, String menssage, Integer status){
        return new ResponseDto()
                .setResponseEntity(reponseEntity)
                .setData(data)
                .setMenssage(menssage)
                .setStatus(status);
    }

    public static ResponseDto response(ResponseEntity<?> reponseEntity, Object data, String menssage){
        return new ResponseDto()
                .setResponseEntity(reponseEntity)
                .setData(data)
                .setMenssage(menssage);
    }

    public static ResponseDto response(Object data, String menssage, Integer status){
        return new ResponseDto()
                .setData(data)
                .setMenssage(menssage)
                .setStatus(status);
    }

    public static ResponseDto response(ResponseEntity<?> reponseEntity, String menssage, Integer status){
        return new ResponseDto()
                .setResponseEntity(reponseEntity)
                .setMenssage(menssage)
                .setStatus(status);
    }

    public static ResponseDto response(ResponseEntity<?> reponseEntity){
        return new ResponseDto()
                .setResponseEntity(reponseEntity);
    }

    public static ResponseDto response(ResponseEntity<?> reponseEntity, Object data){
        return new ResponseDto()
                .setResponseEntity(reponseEntity)
                .setData(data);
    }

    public static ResponseDto response(ResponseEntity<?> reponseEntity, Object data,  String menssage, String... errors){
        return new ResponseDto()
                .setResponseEntity(reponseEntity)
                .setData(data)
                .setMenssage(menssage)
                .setInfos(errors);
    }


    // SETTERS

    public ResponseDto<T> setMenssage(String menssage){
        this.menssage = menssage;
        return this;
    }

    public ResponseDto<T> setData(T data){
        this.data = data;
        return this;
    }

    public ResponseDto<T> setResponseEntity(ResponseEntity<T> responseEntity){
        this.reponseEntity = responseEntity;
        return this;
    }

    public ResponseDto<T> setUri(URI uri){
        this.uri = uri;
        return this;
    }

    public ResponseDto<T> setStatus(Integer status){
        this.status = status;
        return this;
    }


    public ResponseDto<T> setErrors(String... errors){
        this.errors = Lists.newArrayList(errors);
        return this;
    }

    public ResponseDto<T> setWarns(String... warns){
        this.warns = Lists.newArrayList(warns);
        return this;
    }

    public ResponseDto<T> setInfos(String... infos){
        this.infos = Lists.newArrayList(infos);
        return this;
    }

}
