package com.oc.api_server.Service;


import com.oc.api_server.Repository.UserRepository;

public class UserService {
    private UserRepository ur;

    public UserService(UserRepository userRepository){
        this.ur = userRepository;
    }

    /**
     * 로그인 요청
     * @param nickName 닉네임
     * @param PW 비밀번호
     * @param Email 이메일
     * @return
     */
    public boolean SignIn(String nickName, String PW, String Email){
        ur.CreateUser(nickName,PW,Email);
        return false;
    }



}
