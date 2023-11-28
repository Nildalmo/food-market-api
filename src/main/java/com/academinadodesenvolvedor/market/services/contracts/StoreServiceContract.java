package com.academinadodesenvolvedor.market.services.contracts;
import com.academinadodesenvolvedor.market.models.Store;
import com.academinadodesenvolvedor.market.requests.CreateStoreRequest;

public interface StoreServiceContract {

    Store createStore(Store store);

    Store updateStore(Long id,CreateStoreRequest store);


    Store getStoreById(Long id);

    void deleteStore(Store store);

}
