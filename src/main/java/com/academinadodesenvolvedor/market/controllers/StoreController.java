package com.academinadodesenvolvedor.market.controllers;
import com.academinadodesenvolvedor.market.DTOs.StoreDTO;
import com.academinadodesenvolvedor.market.models.Store;
import com.academinadodesenvolvedor.market.requests.CreateStoreRequest;
import com.academinadodesenvolvedor.market.services.contracts.StoreServiceContract;
import com.academinadodesenvolvedor.market.utils.UploadFile;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("/stores")
@RestController
public class StoreController {
    private final StoreServiceContract storeService;

    @Autowired
    public StoreController(StoreServiceContract storeService){
        this.storeService = storeService;
    }

    @PostMapping
    public ResponseEntity<StoreDTO> create(@RequestBody @Valid CreateStoreRequest request){
        Store store = request.convert();
        try {
            if (request.getCover() != null) {
                String coverName = UploadFile.storeFile(request.getCover().split(",")[1],
                        new String[]{"stores", "logos"});
                store.setCoverUrl("stores/logos/"+coverName);
            }
            //@alexjuniorreal @santiago_luiseduardo @__matheusaugusto__ @nildalmosilva
            if(request.getLogo() != null){
                String logoName = UploadFile.storeFile(request.getLogo().split(",")[1],
                        new String[]{"stores", "covers"});
                store.setLogoUrl("stores/logos/"+logoName);
            }
        }catch (IOException exception){
            throw new RuntimeException("Erro ao salvar arquivos");
        }

        this.storeService.createStore(store);
        return new ResponseEntity<>(new StoreDTO(store), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<StoreDTO>> list(Pageable page){
        Page<Store> stores = this.storeService.getStores(page);

        Page<StoreDTO> storeDTOS = stores.map(StoreDTO::new);

        return new ResponseEntity<>(storeDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDTO> store(@PathVariable Long id){
        Store store  = this.storeService.getStoreById(id);

        return new ResponseEntity<>(new StoreDTO(store), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreDTO> update(@PathVariable Long id, @RequestBody CreateStoreRequest request){
        Store store = this.storeService.updateStore(id,request);
        return new ResponseEntity<>(new StoreDTO(store), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        Store store = this.storeService.getStoreById(id);
        this.storeService.deleteStore(store);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}