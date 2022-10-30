package com.oc.api_server.Repository;

import com.oc.api_server.VO.OrUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserRepository {

    /**
     * 유저 만들기
     * @param nickname 입력받은 닉네임
     * @param pw 비밀번호
     * @param email
     * @return
     */
    void CreateUser(String email, String nickname, String pw, byte use_agree, byte security_agree, byte marketing_agree, byte age_agree);

    // public boolean UpdateUser();


    void DeleteUser(String Email);

    OrUser findByEmail(String Email);

    void SetNickName(long id,String Want);

    void SetPw(long id, String want);

    OrUser findByNickname(String Nickname);
}
