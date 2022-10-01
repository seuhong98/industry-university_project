package com.oc.api_server.VO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Setter @Getter
@NoArgsConstructor
public class OrUser {

    long id;
    String nickname; //닉네임
    String email; //이메일
    String pw; //비밀번호

    public OrUser(String nickname , String email , String pw){
        this.nickname = nickname;
        this.email = email;
        this.pw = pw;
    }

}
