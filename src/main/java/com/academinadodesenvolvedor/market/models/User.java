package com.academinadodesenvolvedor.market.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    @ManyToMany()
    @JoinTable(name = "user_roles")
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){

        return this.getRoles();
    }

        @Override
        public String getUsername() {
            return this.email;
        }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
       public boolean isAccountNonLocked(){
            return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}

