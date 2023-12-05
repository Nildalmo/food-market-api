package com.academinadodesenvolvedor.market.services;

import com.academinadodesenvolvedor.market.execptions.ResourceNotFoundException;
import com.academinadodesenvolvedor.market.models.Media;
import com.academinadodesenvolvedor.market.repositories.MediaRepository;
import com.academinadodesenvolvedor.market.services.contracts.MediaServiceContract;
import org.springframework.stereotype.Service;

@Service
public class MediaService implements MediaServiceContract {

    private  final MediaRepository repository;

    public MediaService(MediaRepository mediaRepository){
        this.repository = mediaRepository;
    }

    @Override
    public Media createMedia(Media media) {

        return this.repository.save(media);

    }

    @Override
    public void deleteMedia(Media media) {

        this.repository.delete(media);

    }

    @Override
    public Media getMediaById(Long mediaId) {
        return this.repository.findById(mediaId)
                .orElseThrow(()-> new ResourceNotFoundException("Midia não encontrada."));
    }
}
