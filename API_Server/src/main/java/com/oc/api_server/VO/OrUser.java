package com.oc.api_server.VO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class OrUser {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String nickname; //닉네임
    @Column(length = 125,unique = true)
    String email; //이메일
    String pw; //비밀번호

    public OrUser(String nickname , String email , String pw){
        this.nickname = nickname;
        this.email = email;
        this.pw = pw;
    }

}
