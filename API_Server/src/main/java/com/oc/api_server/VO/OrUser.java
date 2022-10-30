package com.oc.api_server.VO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.annotations.Mapper;


@Setter @Getter
@NoArgsConstructor
public class OrUser {

    long id;

    String email; //이메일
    String nickname; //닉네임
    String pw; //비밀번호

    byte use_agree; //이용약관
    byte security_agree; //개인정보 취급
    byte marketing_agree; //마케팅
    byte age_agree; //만 14세 이상

    public OrUser(long id, String email, String nickname, String pw, byte use_agree, byte security_agree, byte marketing_agree, byte age_agree) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.pw = pw;
        this.use_agree = use_agree;
        this.security_agree = security_agree;
        this.marketing_agree = marketing_agree;
        this.age_agree = age_agree;
    }
}
