package com.oc.api_server.Repository;

import com.oc.api_server.VO.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Transactional
public class UserRepository{
    private PasswordEncoder encoder;
    private EntityManager em;

    public UserRepository(EntityManager em,PasswordEncoder encoder){
        this.encoder = encoder;
        this.em = em;
    }


    public boolean CreateUser(String nickName, String PW, String Email) {
        try {
            User user = new User(nickName,Email,encoder.encode(PW));
            em.persist(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    public boolean RemoveUser() {
        return false;
    }


    public boolean UpdateUser() {
        return false;
    }


    public boolean DeleteUser() {
        return false;
    }

    public User findByNickname(String nickname){
        return em.createQuery("select user from User user where user.nickname = :nickname", User.class).setParameter("nickname",nickname).getSingleResult();
    }
}
