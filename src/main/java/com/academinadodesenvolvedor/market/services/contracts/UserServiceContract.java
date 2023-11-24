package com.academinadodesenvolvedor.market.services.contracts;

import com.academinadodesenvolvedor.market.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServiceContract extends UserDetailsService {

    User createUser(User user);

    User updateUser(User user);

    User changePassword(User user, String Password);

}
