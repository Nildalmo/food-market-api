package com.academinadodesenvolvedor.market.execptions.handler;

import com.academinadodesenvolvedor.market.DTOs.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UsernameNotFoundHandler {
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(UsernameNotFoundException.class)
    public ExceptionDTO handle(UsernameNotFoundException error){
        return new ExceptionDTO(error.getMessage());
    }
}
