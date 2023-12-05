package com.academinadodesenvolvedor.market.services.contracts;

import com.academinadodesenvolvedor.market.models.Media;

public interface MediaServiceContract {
    Media createMedia(Media media);

    void deleteMedia(Media media);

    Media getMediaById(Long mediaId);
}
