package com.oc.api_server.Repository;

import com.oc.api_server.VO.OrUser;
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
            OrUser user = new OrUser(nickName,Email,encoder.encode(PW));
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

    public OrUser findByNickname(String nickname){
        return em.createQuery("select oc from OrUser oc where oc.nickname = :nickname", OrUser.class).setParameter("nickname",nickname).getSingleResult();
    }
}
