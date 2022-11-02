package com.oc.api_server.VO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.annotations.Mapper;


@Setter @Getter
@NoArgsConstructor
public class OrUser {

    Long id;

    String email; //이메일
    String nickname; //닉네임
    String pw; //비밀번호

    Byte use_agree; //이용약관
    Byte security_agree; //개인정보 취급
    Byte marketing_agree; //마케팅
    Byte age_agree; //만 14세 이상

    int uni_code;

    public OrUser(Long id, String email, String nickname, String pw, Byte use_agree, Byte security_agree, Byte marketing_agree, Byte age_agree, int uni_code) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.pw = pw;
        this.use_agree = use_agree;
        this.security_agree = security_agree;
        this.marketing_agree = marketing_agree;
        this.age_agree = age_agree;
        this.uni_code = uni_code;
    }
}
