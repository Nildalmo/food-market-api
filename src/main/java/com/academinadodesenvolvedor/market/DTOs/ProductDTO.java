package com.academinadodesenvolvedor.market.DTOs;

import com.academinadodesenvolvedor.market.models.Product;
import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private Double price;
    private String description;

    public ProductDTO(Product produto){
        this.id = produto.getId();
        this.description = produto.getDescription();
        this.name = produto.getName();
        this.price = produto.getPrice();
    }

}
