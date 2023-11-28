package com.academinadodesenvolvedor.market.services.contracts;

import com.academinadodesenvolvedor.market.models.Product;

import java.util.Optional;

public interface ProductServiceContract {
    Product createProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Product product);

    Product getProductById(Long productId);


}

