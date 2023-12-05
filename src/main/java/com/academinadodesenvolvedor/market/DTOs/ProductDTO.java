package com.academinadodesenvolvedor.market.DTOs;

import com.academinadodesenvolvedor.market.models.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private List<MediaDTO> medias;

    public ProductDTO(Product produto){
        this.id = produto.getId();
        this.description = produto.getDescription();
        this.name = produto.getName();
        this.price = produto.getPrice();
        this.medias = produto.getMedias()
                .stream()
                .map(MediaDTO:: new)
                .toList();
    }

}
