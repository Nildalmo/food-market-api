package com.academinadodesenvolvedor.market.repositories;

import com.academinadodesenvolvedor.market.models.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository<Media,Long> {
}
