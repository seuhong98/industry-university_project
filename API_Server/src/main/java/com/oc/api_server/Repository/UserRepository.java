package com.oc.api_server.Repository;

import com.oc.api_server.Service.Security;
import com.oc.api_server.VO.OrUser;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;


@Transactional
public class UserRepository{
    private Security security;
    private EntityManager em;

    public UserRepository(EntityManager em,Security encoder){
        this.security = encoder;
        this.em = em;
    }

    public boolean CreateUser(String nickName, String PW, String Email) {
        try {
            OrUser user = new OrUser(nickName,Email,security.makeOneWayEncrypt(PW));
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

    public boolean isUser(String Email, String pw){
        OrUser read  =em.createQuery("select oc from OrUser oc where oc.email = :Email", OrUser.class).setParameter("Email",Email).getSingleResult();
        if(security.match(pw,read.getPw())){
            return true;
        }else{
            return false;
        }
    }

    public OrUser findByNickname(String nickname){
        return em.createQuery("select oc from OrUser oc where oc.nickname = :nickname", OrUser.class).setParameter("nickname",nickname).getSingleResult();
    }


}
