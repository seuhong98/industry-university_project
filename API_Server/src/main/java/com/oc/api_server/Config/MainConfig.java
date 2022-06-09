package com.oc.api_server.Config;

import com.oc.api_server.Repository.UserRepository;
import com.oc.api_server.Repository.BoardRepository;
import com.oc.api_server.Service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class MainConfig {

    @PersistenceContext
    private EntityManager em;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public com.oc.api_server.Repository.BoardRepository boardRepository(){
        return new BoardRepository(em);
    }
    @Bean
    public UserRepository userRepository(){
        return new UserRepository(em,passwordEncoder());
    }


    @Bean
    public UserService userService(){
        return new UserService(userRepository());
    }
}
