package com.academinadodesenvolvedor.market.execptions.handler;

import com.academinadodesenvolvedor.market.DTOs.ExceptionDTO;
import com.academinadodesenvolvedor.market.execptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourceNotFoundHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ExceptionDTO handle(ResourceNotFoundException exception){
        return new ExceptionDTO(exception.getMessage());
    }

}
