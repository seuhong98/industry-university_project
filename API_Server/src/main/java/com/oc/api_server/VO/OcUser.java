package com.oc.api_server.VO;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class OcUser {
    @Id
    @Column(length = 125)
    String nickname;
    String email;
    String pw;

    public OcUser() {}

    public OcUser(String nickname , String email , String pw){
        this.nickname = nickname;
        this.email = email;
        this.pw = pw;
    }


}
