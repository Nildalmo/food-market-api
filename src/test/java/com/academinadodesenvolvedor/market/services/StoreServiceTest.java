package com.academinadodesenvolvedor.market.services;
import com.academinadodesenvolvedor.market.execptions.ResourceNotFoundException;
import com.academinadodesenvolvedor.market.models.Store;
import com.academinadodesenvolvedor.market.requests.CreateStoreRequest;
import com.academinadodesenvolvedor.market.services.contracts.StoreServiceContract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StoreServiceTest {
    @Autowired
    private StoreServiceContract storeService;

    @Test
    public void shouldCreateAStore(){
        Store store = this.getStoreModel();

        this.storeService.createStore(store);

        Assertions.assertNotNull(store.getId());
    }

    @Test
    public  void shouldUpdateAStore(){
        Store store = this.getStoreModel();
        this.storeService.createStore(store);
        CreateStoreRequest request = new CreateStoreRequest();
        request.setName("Lojinha do Japonêis");
        Store updatetedStore = this.storeService.updateStore(store.getId(), request);

        Assertions.assertEquals(request.getName(), updatetedStore.getName());
    }

    @Test
    public void shouldDeleteAStore(){
        Store store = this.getStoreModel();
        this.storeService.createStore(store);

        this.storeService.deleteStore(store);


        Assertions.assertThrows(ResourceNotFoundException.class,() -> {
            this.storeService.getStoreById(store.getId());
        });
    }

    public  Store getStoreModel() {
        Store store = new Store();
        store.setName("Loja Desajustada");
        store.setDescription("Loja que vende de tudo e muito caro sem nota");
        return store;
    }
}
