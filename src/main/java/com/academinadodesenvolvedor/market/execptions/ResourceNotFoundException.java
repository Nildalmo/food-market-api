package com.academinadodesenvolvedor.market.execptions;


public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message){
        super(message);
    }
}
