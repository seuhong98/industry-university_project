package com.oc.api_server.Service;


import com.oc.api_server.Repository.UserRepository;
import com.oc.api_server.VO.OrUser;

public class UserService {
    private final UserRepository ur;

    private final Security security;

    public UserService(UserRepository userRepository,Security security){
        this.ur =  userRepository;
        this.security = security;
    }

    /**
     * 회원가입 요청
     * @return
     */
    public boolean SignUp(String email, String nickname, String pw, byte use_agree, byte security_agree, byte marketing_agree, byte age_agree, int uni_code){
        ur.CreateUser(security.TwoWayEncrypt(email),nickname,security.makeOneWayEncrypt(pw),use_agree,security_agree,marketing_agree,age_agree,uni_code);
        return CheckIsUniqueEmail(email);
    }


    /**
     * 로그인 서비스
     * @param PW plain 비밀번호
     * @param Email 검색을 위한 이메일
     * @return 유저
     */
    public OrUser SingIn(String Email, String PW){
        OrUser read= ur.findByEmail(security.TwoWayEncrypt(Email));
        if(read != null){
            if(security.match(PW,read.getPw())){
                return read;
            }else {
                return null;
            }
        }else{
            return null;
        }
    }

    /**
     * 탈퇴 하기 기능
     */
    public void Resign(Long uid){
        ur.DeleteUser(uid);
    }

    /**
     * 닉네임 변경
     * 로그인 성공시에 진행하는 것으로 진행
     * @param id 로그인시 받아온 유저 객체의 아이디
     * @param want 원하는 닉네임
     */
    public void SetNickName(long id,String want){
        ur.SetNickName(id, want);
    }

    public void SetPw(long id, String want){
        ur.SetPw(id,security.makeOneWayEncrypt(want));
    }


    public boolean CheckIsUniqueEmail(String email){
        OrUser user =  ur.findByEmail(security.TwoWayEncrypt(email));
        if(user != null){
            return false;
        }else {
            return true;
        }
    }

    public boolean CheckIsUniqueNickname(String nickname){
        OrUser user =  ur.findByNickname(nickname);
        if(user != null){
            return false;
        }else {
            return true;
        }
    }




}
