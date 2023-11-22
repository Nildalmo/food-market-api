package com.academinadodesenvolvedor.market.services;

import com.academinadodesenvolvedor.market.repositories.UserRepository;
import com.academinadodesenvolvedor.market.services.contracts.UserServiceContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceContract {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository repository) {
        this.userRepository =  repository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(username)
                .orElseThrow(() ->new UsernameNotFoundException("Ussuário invalido"));
    }
}