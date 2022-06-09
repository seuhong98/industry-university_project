package com.oc.api_server.VO;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class User {
    @Id
    String nickname;
    String email;
    String pw;

    public User() {}

    public User(String nickname , String email , String pw){
        this.nickname = nickname;
        this.email = email;
        this.pw = pw;
    }


}
