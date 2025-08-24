package com.project.deliverytracer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroValidacao handleCampoObrigatorio(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErroCampo> listaErros = fieldErrors
                .stream()
                .map(lista -> new ErroCampo(lista.getField(), lista.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ErroValidacao(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação: ", listaErros);
    }

    @ExceptionHandler(IdadeNaoPermitidaException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ErroValidacao handleIdadeNaoPermitida(IdadeNaoPermitidaException e) {
        return new ErroValidacao(HttpStatus.NOT_ACCEPTABLE.value(), "Idade não permitida para cadastrar motorista", List.of());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleErroTratado(RuntimeException e) {
        return e.getMessage();
    }

    @ExceptionHandler(CadastroNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleCadastroNaoEncontrado(CadastroNaoEncontradoException e){
        return e.getMessage();
    }
}
