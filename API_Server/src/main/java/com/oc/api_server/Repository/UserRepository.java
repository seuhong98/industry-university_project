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
    public void CreateUser(String nickname, String pw, String email);

    // public boolean UpdateUser();


    public void DeleteUser(String Email);

    public OrUser findByEmail(String Email);

    public void SetNickName(long id,String Want);

    public void SetEmail(long id,String Want);


    public void SetPw(long id, String want);
}
