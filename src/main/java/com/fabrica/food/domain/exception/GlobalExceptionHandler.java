package com.fabrica.food.domain.exception;

import com.fabrica.food.domain.dto.ResponseBodyDto;
import com.fabrica.food.domain.dto.ResponseDto;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.apache.commons.lang3.exception.ExceptionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private UnrecognizedPropertyException getErrorRoot(HttpMessageNotReadableException ex) {
        return (UnrecognizedPropertyException) ExceptionUtils.getRootCause(ex);
    }

    private List<String> getCamposAceitos(HttpMessageNotReadableException ex) {
        return getPropriedadeErro(ex).stream().map(campo -> campo.toString()).collect(Collectors.toList());
    }

    private Collection<Object> getPropriedadeErro(HttpMessageNotReadableException ex) {
        return ((UnrecognizedPropertyException) ExceptionUtils.getRootCause(ex)).getKnownPropertyIds();
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        LOGGER.error(" =============== Cliente passando campos desconhecido ==========================");

        List<String> camposAceitos = getCamposAceitos(ex);

        String camposNaoAceito = getErrorRoot(ex).getPropertyName();

        List<String> erros = Arrays.asList("Campos não aceitos: " +camposNaoAceito, "Campos aceitos: " + camposAceitos);

        ResponseBodyDto res =
                    ResponseBodyDto.body(ex.getCause() != null ? ex.getCause().getMessage() : ex.toString(),
                    HttpStatus.BAD_REQUEST,
                    "Passando campo desconhecido: " + camposNaoAceito,
                    erros);

        return handleExceptionInternal(ex, res, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        LOGGER.error(" =============== Validation ==========================");

        List<String> erros = ex.getBindingResult().getFieldErrors().stream().map(fieldError -> {
          return  fieldError.getDefaultMessage();
        }).collect(Collectors.toList());

        List<String> campos = ex.getBindingResult().getFieldErrors().stream().map(fieldError -> {
            return  fieldError.getField().toString();
        }).collect(Collectors.toList());

        String camposErrorValidation = campos.stream().map(campo -> campo).collect(Collectors.joining(", "));

        ResponseBodyDto res =
                ResponseBodyDto.body(ex.getCause() != null ? ex.getCause().getMessage() : ex.toString(),
                        HttpStatus.BAD_REQUEST,
                        "Os Seguinte(s) campo(s): " + camposErrorValidation  +" estão com erro de validação",
                        erros);

        return handleExceptionInternal(ex, res, headers, status, request);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
                                                                        WebRequest request) {
        LOGGER.error(" =============== DataIntegrityViolationException========= DELETE OR UPDATE OR SAVE=================");
        Object obj =  ResponseBodyDto.body(ExceptionUtils.getRootCauseMessage(ex),
                HttpStatus.CONFLICT,"Não é posssível Deletar, pois esta associada", Arrays.asList(ex.getMessage()));
        return handleExceptionInternal(ex, obj, null, HttpStatus.CONFLICT, request);
    }


    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleJdbcSQLIntegrityConstraintViolationException(ConstraintViolationException ex,
                                                                        WebRequest request) {
        LOGGER.error(" =============== JdbcSQLIntegrityConstraintViolationException @valid===  POST=======================");

        List<String> erros = ex.getConstraintViolations().stream().map(mes ->mes.getMessageTemplate()).collect(Collectors.toList());

        Object obj =  ResponseBodyDto.body(ExceptionUtils.getRootCauseMessage(ex),
                HttpStatus.BAD_REQUEST,
                ex.getConstraintViolations().stream().findFirst().orElse(null).getMessageTemplate(),
                erros);
        return handleExceptionInternal(ex, obj, null, HttpStatus.BAD_REQUEST, request);
    }


    @ExceptionHandler({TransactionSystemException.class})
    public ResponseEntity<Object> handleIllegalStateException(TransactionSystemException ex,WebRequest request) {
        LOGGER.error(" =============== IllegalStateException @valid=== PUT=======================");

        List<String> erros = new ArrayList<>();

        for (Throwable t = ex.getCause(); t != null; t = t.getCause()) {
            if(t instanceof ConstraintViolationException){
                ConstraintViolationException violantion = (ConstraintViolationException) t;
                erros = violantion.getConstraintViolations().stream().map(err -> err.getMessageTemplate()).collect(Collectors.toList());
            }
        }

        Object obj =  ResponseBodyDto.body(ExceptionUtils.getRootCauseMessage(ex), HttpStatus.BAD_REQUEST,
                "Erro ao tentar Alterar, passe todos os valores obrigatórios",erros);
        return handleExceptionInternal(ex, obj, null, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({InvalidFormatException.class})
    public ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, WebRequest request) {
        LOGGER.error(" =============== InvalidFormatException =======================");

        List<String> erros = new ArrayList<>();

        String field = ex.getPath().get(0).getFieldName();
        String type = ex.getTargetType().getName();

        String msg = "Valor informado no campo: "+ field  + " tem que ser do tipo " + type;

        erros.add(msg);

        Object obj =  ResponseBodyDto.body(ExceptionUtils.getRootCauseMessage(ex), HttpStatus.BAD_REQUEST,
                msg,erros);
        return handleExceptionInternal(ex, obj, null, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * EXCEÇÕES PERSONALIZADAS
     **/
    @ExceptionHandler({BadValueException.class})
    public ResponseEntity<Object> handleBadValueException(BadValueException ex,WebRequest request) {
        LOGGER.error(" =============== Bad request front ==========================");
        Object obj =  ResponseBodyDto.body(ex.toString(),
                HttpStatus.BAD_REQUEST,ex.getMessage(), Arrays.asList(ex.getMessage()));
        return handleExceptionInternal(ex, obj, null, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({NoContentException.class})
    public ResponseEntity<Object> handleNoContentException(NoContentException ex,WebRequest request) {
        LOGGER.error(" =============== No content request front ==========================");
        Object obj =  ResponseBodyDto.body(ex.toString(),
                HttpStatus.NOT_FOUND,ex.getMessage(), Arrays.asList(ex.getMessage()));
//        return handleExceptionInternal(ex, obj, null, HttpStatus.NO_CONTENT, request);
        return ResponseDto.response(HttpStatus.NOT_FOUND,obj);
    }

    @ExceptionHandler({NegocioException.class})
    public ResponseEntity<Object> handleNegocioException(NegocioException ex,WebRequest request) {
        LOGGER.error(" =============== NegocioException ==========================");
        Object obj =  ResponseBodyDto.body(ex.toString(),
                HttpStatus.BAD_REQUEST,ex.getMessage(), Arrays.asList(ex.getMessage()));
        return handleExceptionInternal(ex, obj, null, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ConflictException.class})
    public ResponseEntity<Object> handleConflictException(ConflictException ex,WebRequest request) {
        LOGGER.error(" =============== ConflictException ==========================");
        Object obj =  ResponseBodyDto.body(ex.toString(),
                HttpStatus.CONFLICT,ex.getMessage(), Arrays.asList(ex.getMessage()));
        return handleExceptionInternal(ex, obj, null, HttpStatus.CONFLICT, request);
    }

}
