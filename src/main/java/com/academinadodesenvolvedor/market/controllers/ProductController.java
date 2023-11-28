package com.academinadodesenvolvedor.market.controllers;

import com.academinadodesenvolvedor.market.DTOs.ProductDTO;
import com.academinadodesenvolvedor.market.models.Product;
import com.academinadodesenvolvedor.market.models.Store;
import com.academinadodesenvolvedor.market.requests.CreateProductRequest;
import com.academinadodesenvolvedor.market.services.contracts.ProductServiceContract;
import com.academinadodesenvolvedor.market.services.contracts.StoreServiceContract;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/products")
@RestController
public class ProductController {

    private final ProductServiceContract productService;

    private final StoreServiceContract storeService;

    public ProductController(ProductServiceContract productService,
                             StoreServiceContract storeService) {
        this.productService = productService;
        this.storeService = storeService;
    }


    @PostMapping
    private ResponseEntity<ProductDTO> create(@Valid @RequestBody CreateProductRequest request) {
        Product product = request.convert();
        Store store = this.storeService.getStoreById(request.getStoreId());
        product.setStore(store);
        Product productSaved = this.productService.createProduct(product);

        return new ResponseEntity<>(new ProductDTO(productSaved), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    private ResponseEntity<ProductDTO> update(@PathVariable Long id,
                                              @RequestBody CreateProductRequest request){

        Product product = this.productService.getProductById(id);
        Store store = this.storeService.getStoreById(request.getStoreId());
        product.setStore(store);
        product = this.productService.updateProduct(request.update(product));

        return  new ResponseEntity<>(new ProductDTO(product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity delete(@PathVariable Long id){
        Product product = this.productService.getProductById(id);
        this.productService.deleteProduct(product);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}