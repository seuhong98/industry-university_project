package com.oc.api_server.Config;

import com.oc.api_server.Repository.Review2Repository;
import com.oc.api_server.Repository.UserRepository;
import com.oc.api_server.Repository.Review1Repository;
import com.oc.api_server.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class MainConfig {

    //소스=========================================================================================================

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private JavaMailSender emailSender;



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //레포지토리=========================================================================================================

    @Bean
    public UserRepository userRepository(){
        return new UserRepository(em,passwordEncoder());
    }

    //서비스=========================================================================================================

    @Bean
    public UserService userService(){
        return new UserService(userRepository());
    }

    @Bean
    public CertificationService certificationService(){
        return new CertificationService(emailSender);
    }

    @Bean
    public Review1Service review1Service(){
        return new Review1Service();
    }

    @Bean
    public Review2Service review2Service(){
        return new Review2Service();
    }

    @Bean
    Security security(){
        return new Security();
    }
}
