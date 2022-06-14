package com.oc.api_server.VO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter
@NoArgsConstructor
public class OrUser {
    String nickname; //닉네임
    @Id
    @Column(length = 125)
    String email; //이메일
    String pw; //비밀번호

    public OrUser(String nickname , String email , String pw){
        this.nickname = nickname;
        this.email = email;
        this.pw = pw;
    }

}
