package com.academinadodesenvolvedor.market.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "medias")
public class Media {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String filename;
   private String filePath;
   @ManyToOne
   @JoinColumn(name = "product_id")
   private Product product;

}

