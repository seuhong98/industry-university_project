package com.oc.api_server.Service;


import com.oc.api_server.Repository.UserRepository;
import com.oc.api_server.VO.OrUser;

import java.util.Optional;

public class UserService {
    private final UserRepository ur;

    private final Security security;

    public UserService(UserRepository userRepository,Security security){
        this.ur = userRepository;
        this.security = security;
    }

    /**
     * 로그인 요청
     * @param nickName 닉네임
     * @param PW 비밀번호
     * @param Email 이메일
     * @return
     */
    public boolean SignUp(String nickName, String PW, String Email){
        ur.CreateUser(nickName,security.makeOneWayEncrypt(PW),Email);
        return false;
    }


    public Optional<OrUser> SingIn(String PW, String Email){
        Optional<OrUser> read = ur.findByEmail(security.TwoWayEncrypt(Email));
        if(read.isPresent()){
            if(read.get().getPw().equals(security.makeOneWayEncrypt(PW))){
                return read;
            }else {
                return Optional.empty();
            }
        }else{
            return Optional.empty();
        }
    }

    public boolean Resign(String PW, String Email){
        if(SingIn(PW,Email).isPresent()){
            ur.DeleteUser(Email);
            return true;
        }else{
            return false;
        }
    }

    public void SetNickName(long id,String want){
        ur.SetNickName(id, want);
    }



}
