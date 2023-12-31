package com.academinadodesenvolvedor.market.DTOs;

import com.academinadodesenvolvedor.market.models.User;
import lombok.Data;

@Data

public class UserDTO {
    private String email;
    private String name;
    private Long id;

    public UserDTO(User user){
        this.email = user.getEmail();
        this.id = user.getId();
        this.name = user.getName();
    }
}
