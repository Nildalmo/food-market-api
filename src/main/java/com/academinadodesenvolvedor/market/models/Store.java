package com.academinadodesenvolvedor.market.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "stores")
public class Store {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String name;
   private String description;
   private String coverUrl;
   private String logoUrl;
   @OneToMany
   private List<Product> products;
}

