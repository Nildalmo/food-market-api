package com.academinadodesenvolvedor.market.DTOs;

import com.academinadodesenvolvedor.market.models.Store;
import lombok.Data;

@Data
public class StoreDTO {
    private Long id;
    private String name;
    private String description;
    private String coverUrl;
    private String logUrl;

    public StoreDTO(Store store ){
        this.coverUrl = store.getCoverUrl();
        this.logUrl = store.getLogoUrl();
        this.id = store.getId();
        this.name = store.getName();
        this.description = store.getDescription();
    }

}
