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
     * 로그인 요청
     * @param nickName 닉네임
     * @param PW 비밀번호
     * @param Email 이메일
     * @return
     */
    public boolean SignUp(String nickName, String PW, String Email){
        ur.CreateUser(nickName,security.makeOneWayEncrypt(PW),security.TwoWayEncrypt(Email));
        return false;
    }


    /**
     * 로그인 서비스
     * @param PW plain 비밀번호
     * @param Email 검색을 위한 이메일
     * @return 유저
     */
    public OrUser SingIn(String PW, String Email){
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
     * @param PW plain 비밀번호
     * @param Email 이메일
     * @return 성공여부 보내기(비밀번호가 틀려도 실행)
     */
    public boolean Resign(String PW, String Email){
        if(SingIn(PW,Email) != null){
            ur.DeleteUser(Email);
            return true;
        }else{
            return false;
        }
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

    public void SetEmail(long id,String want){
        ur.SetEmail(id, want);
    }

    public void SetPw(long id, String want){
        ur.SetPw(id,security.makeOneWayEncrypt(want));
    }






}
