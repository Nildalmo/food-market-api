package com.academinadodesenvolvedor.market.requests;

import com.academinadodesenvolvedor.market.Validations.EmailIsUnique;
import com.academinadodesenvolvedor.market.models.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;



@Data
public class CreateUserRequest {

    @NotEmpty
    @NotNull
    private String name;
    @NotEmpty
    @NotNull
    @EmailIsUnique(entity = User.class)
    private String email;
    @NotEmpty
    @NotNull
    @Size(min = 8)
    private String password;


    public User convert(){
        User user = new User();
        user.setEmail(this.email);
        user.setName(this.name);
        user.setPassword(this.password);

        return user;

    }
    public User update(User user){
        if(this.name !=null) user.setName(this.name);
        if(this.email!=null) user.setEmail(this.email);
        return user;
    }
}
