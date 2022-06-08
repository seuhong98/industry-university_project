package com.oc.api_server.VO;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OCUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String nickname;
    String email;
    String pw;

    public OCUser(String nickname ,String email , String pw){
        this.nickname = nickname;
        this.email = email;
        this.pw = pw;
    }
}
