package com.academinadodesenvolvedor.market.DTOs;

import com.academinadodesenvolvedor.market.models.Media;
import lombok.Data;


@Data
public class MediaDTO {
    private String filename;
    private String pathName;
    private String url;

    public MediaDTO(Media media){
        this.filename = media.getFilename();
        this.pathName = media.getFilePath();
        this.url = media.getFilePath() + "/" + media.getFilename();
    }

}
