package com.oc.api_server.Repository;

import com.oc.api_server.Service.Security;
import com.oc.api_server.VO.OrUser;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;


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
            OrUser user = new OrUser(nickName,Email,PW);
            em.persist(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean UpdateUser() {
        return false;
    }


    public void DeleteUser(String Email) {
        em.remove(findByEmail(Email).get());
    }

    public Optional<OrUser> findByEmail(String Email){
        return Optional.ofNullable(em.createQuery("select oc from OrUser oc where oc.email = :Email", OrUser.class).setParameter("Email",Email).getSingleResult());
    }

    public void SetNickName(long id,String Want){
        OrUser read = em.find(OrUser.class,id);
        read.setNickname(Want);
    }


}
