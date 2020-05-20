package com.fabrica.food.dto;

import com.google.common.collect.Lists;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;

@Getter
public class ResponseBodyDto<T> {

    private T data;
    private URI uri;
    private String menssage;
    private Integer status;

    private List<String> errors;
    private List<String> warns;
    private List<String> infos;
    //    private ResponseEntity<T> reponseEntity;
    
    public ResponseBodyDto(){}

    public static ResponseBodyDto body(Object data, String menssage, URI uri, Integer status){
        return new ResponseBodyDto()
                .setData(data)
                .setMenssage(menssage)
                .setUri(uri)
                .setStatus(status);
    }

    public static ResponseBodyDto body(Object data, String menssage, Integer status){
        return new ResponseBodyDto()
                .setData(data)
                .setMenssage(menssage)
                .setStatus(status);
    }

    public static ResponseBodyDto body(String data, HttpStatus badRequest, String s, List<String> erros) {
        return new ResponseBodyDto().setData(data).setStatus(badRequest.value()).setMenssage(s).setErrors(erros);
    }

//    public static ResponseBodyDto body(HttpStatus status,ResponseEntity<?> reponseEntity, Object data, String menssage, Integer status1){
//        return new ResponseBodyDto()
//                .setResponseEntity(reponseEntity)
//                .setData(data)
//                .setMenssage(menssage)
//                .setStatus(status1);
//    }
//
//    public static ResponseBodyDto body(HttpStatus status,ResponseEntity<?> reponseEntity, Object data, String menssage){
//        return new ResponseBodyDto()
//                .setResponseEntity(reponseEntity)
//                .setData(data)
//                .setMenssage(menssage);
//    }
//
//    public static ResponseBodyDto body(HttpStatus status,Object data, String menssage, Integer status1){
//        return new ResponseBodyDto()
//                .setData(data)
//                .setMenssage(menssage)
//                .setStatus(status1);
//    }
//
//    public static ResponseBodyDto body(ResponseEntity<?> reponseEntity, String menssage, Integer status1){
//        return new ResponseBodyDto()
//                .setResponseEntity(reponseEntity)
//                .setMenssage(menssage)
//                .setStatus(status1);
//    }
//
//    public static ResponseBodyDto body(ResponseEntity<?> reponseEntity){
//        return new ResponseBodyDto()
//                .setResponseEntity(reponseEntity);
//    }
//
//    public static ResponseBodyDto body(ResponseEntity<?> reponseEntity, Object data){
//        return new ResponseBodyDto()
//                .setResponseEntity(reponseEntity)
//                .setData(data);
//    }
//
//    public static ResponseBodyDto body(ResponseEntity<?> reponseEntity, Object data,  String menssage, String... errors){
//        return new ResponseBodyDto()
//                .setResponseEntity(reponseEntity)
//                .setData(data)
//                .setMenssage(menssage)
//                .setInfos(errors);
//    }

    // SETTERS

    public ResponseBodyDto<T> setMenssage(String menssage){
        this.menssage = menssage;
        return this;
    }

    public ResponseBodyDto<T> setData(T data){
        this.data = data;
        return this;
    }

    public ResponseBodyDto<T> setUri(URI uri){
        this.uri = uri;
        return this;
    }

    public ResponseBodyDto<T> setStatus(Integer status){
        this.status = status;
        return this;
    }


    public ResponseBodyDto<T> setErrors(List<String> errors){
        this.errors = errors;
        return this;
    }

    public ResponseBodyDto<T> setWarns(String... warns){
        this.warns = Lists.newArrayList(warns);
        return this;
    }

    public ResponseBodyDto<T> setInfos(String... infos){
        this.infos = Lists.newArrayList(infos);
        return this;
    }



//    public ResponseBodyDto<T> setResponseEntity(ResponseEntity<T> responseEntity){
//        this.reponseEntity = responseEntity;
//        return this;
//    }

}
