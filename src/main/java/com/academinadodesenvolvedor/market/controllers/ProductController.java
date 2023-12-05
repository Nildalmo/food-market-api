package com.academinadodesenvolvedor.market.controllers;
import com.academinadodesenvolvedor.market.DTOs.ProductDTO;
import com.academinadodesenvolvedor.market.models.Media;
import com.academinadodesenvolvedor.market.models.Product;
import com.academinadodesenvolvedor.market.models.Store;
import com.academinadodesenvolvedor.market.requests.CreateProductRequest;
import com.academinadodesenvolvedor.market.services.contracts.MediaServiceContract;
import com.academinadodesenvolvedor.market.services.contracts.ProductServiceContract;
import com.academinadodesenvolvedor.market.services.contracts.StoreServiceContract;
import com.academinadodesenvolvedor.market.utils.UploadFile;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.Arrays;

@RequestMapping("/products")
@RestController
public class ProductController {
    private final ProductServiceContract productService;
    private final StoreServiceContract storeService;

    private  final MediaServiceContract mediaService;

    public ProductController(ProductServiceContract productService,
                             StoreServiceContract storeService,
                             MediaServiceContract mediaService){
        this.productService = productService;
        this.storeService = storeService;
        this.mediaService = mediaService;
    }

 @GetMapping
 private ResponseEntity<Page<ProductDTO>> getProducts(Pageable pageable){
    Page<Product> productsPage = this. productService.getProducts(pageable);
    Page<ProductDTO> productDTOS = productsPage.map(ProductDTO::new);

    return new ResponseEntity<>(productDTOS, HttpStatus.OK);

 }

    @PostMapping
    private ResponseEntity<ProductDTO> create(@Valid @RequestBody CreateProductRequest request) throws IOException {
        Product product = request.convert();
        Store store = this.storeService.getStoreById(request.getStoreId());
        product.setStore(store);

        String[] filePath = new String[] {"products", "images"};

        if(request.getImages() != null) {
            product = this.uploadFile(product, request.getImages(), filePath);
        }

        Product productSaved = this.productService.createProduct(product);

        return new ResponseEntity<>(new ProductDTO(productSaved), HttpStatus.CREATED);

    }
    private Product uploadFile(Product product,String[] images,String[] filePath) throws IOException {
        for (String image : images){
            String storageUrl = UploadFile.storeFile(image.split(",")[1],filePath);
            Media media = new Media();
            media.setFilePath(String.join("/", Arrays.asList(filePath)));
            media.setFilename(storageUrl);
            this.productService.setMedia(product,this. mediaService.createMedia(media));
        }
        return product;
    }


    @GetMapping("/{id}")
    private ResponseEntity<ProductDTO> getById(@PathVariable Long id) {
        Product product = this.productService.getProductById(id);
        return new ResponseEntity<>(new ProductDTO(product), HttpStatus.OK);

    }
    @PutMapping("/{id}")
    private ResponseEntity<ProductDTO> update(@PathVariable Long id,
                                              @RequestBody CreateProductRequest request) throws IOException {

        Product product = this.productService.getProductById(id);
        Store store = this.storeService.getStoreById(request.getStoreId());
        product.setStore(store);
        String[] filePath = new String[] {"products", "images"};

        if(request.getImages() != null) {
            product = this.uploadFile(product, request.getImages(), filePath);
        }
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
