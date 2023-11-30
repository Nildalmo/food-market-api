package com.academinadodesenvolvedor.market.execptions.handler;

import com.academinadodesenvolvedor.market.DTOs.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class HttpRequestMethodNotSupporteExceptionHandler {
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public  ExceptionDTO handle(HttpRequestMethodNotSupportedException exception){
        return new ExceptionDTO("Metodo não permitido.");
    }
}
