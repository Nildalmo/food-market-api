package com.academinadodesenvolvedor.market.controllers;
import com.academinadodesenvolvedor.market.DTOs.StoreDTO;
import com.academinadodesenvolvedor.market.models.Store;
import com.academinadodesenvolvedor.market.requests.CreateStoreRequest;
import com.academinadodesenvolvedor.market.services.contracts.StoreServiceContract;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping( "/stores")
@RestController

public class StoreController {
    private final StoreServiceContract storeService;

        @Autowired
        public StoreController(StoreServiceContract storeService){
            this.storeService = storeService;
        }
    @PostMapping
    public ResponseEntity<StoreDTO> create(@RequestBody @Valid CreateStoreRequest request){
        Store store = this.storeService.createStore(request.convert());
        return new ResponseEntity<>(new StoreDTO(store), HttpStatus.CREATED);
    }
        @PutMapping( "/{id}")


    public ResponseEntity<StoreDTO> update(@PathVariable Long id, @RequestBody @Valid CreateStoreRequest request){
         Store store = this.storeService.updateStore(id, request);
         return new ResponseEntity<>(new StoreDTO(store), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Store store = this.storeService.getStoreById(id);
        this.storeService.deleteStore(store);
        return  new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    }




