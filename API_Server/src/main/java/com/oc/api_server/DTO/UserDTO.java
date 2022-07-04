package com.oc.api_server.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDTO {
    long id;
    String Email;

    public UserDTO setID(long id){
        this.id = id;
        return this;
    }

    public UserDTO setEmail(String Email){
        this.Email = Email;
        return this;
    }
}
