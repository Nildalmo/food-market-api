package com.academinadodesenvolvedor.market.services;

import com.academinadodesenvolvedor.market.execptions.ResourceNotFoundException;
import com.academinadodesenvolvedor.market.models.Product;
import com.academinadodesenvolvedor.market.repositories.ProductRepository;
import com.academinadodesenvolvedor.market.services.contracts.ProductServiceContract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements ProductServiceContract{

    private ProductRepository repository;
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }
    @Override
    public Product createProduct(Product product) {
        return this.repository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return this.repository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        this.repository.delete(product);

    }

    @Override
    public Product getProductById(Long productId) {
        return this.repository.findById(productId)
                .orElseThrow(()->new ResourceNotFoundException("Produto não encontrado."));
    }

    @Override
    public List<Product> getProducts() {
        return this.repository.findAll();
    }

    @Override
    public Page<Product> getProducts(Pageable pageable) {
        return this.repository.findAll(pageable);
    }
}

