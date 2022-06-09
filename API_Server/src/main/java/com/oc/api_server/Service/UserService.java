package com.oc.api_server.Service;


import com.oc.api_server.Repository.UserRepository;

public class UserService {
    private UserRepository ur;

    public UserService(UserRepository userRepository){
        this.ur = userRepository;
    }

    public boolean SignIn(String nickName, String PW, String Email){
        ur.CreateUser(nickName,PW,Email);
        return false;
    }

}
