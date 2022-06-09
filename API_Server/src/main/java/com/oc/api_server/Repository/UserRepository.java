package com.oc.api_server.Repository;

import com.oc.api_server.VO.OcUser;
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
            OcUser user = new OcUser(nickName,Email,encoder.encode(PW));
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

    public OcUser findByNickname(String nickname){
        return em.createQuery("select oc from OcUser oc where oc.nickname = :nickname", OcUser.class).setParameter("nickname",nickname).getSingleResult();
    }
}
