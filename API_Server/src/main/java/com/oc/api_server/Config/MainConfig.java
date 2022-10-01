package com.oc.api_server.Config;

import com.oc.api_server.Repository.ReviewRepository;
import com.oc.api_server.Repository.SessionRepository;
import com.oc.api_server.Repository.UserRepository;
import com.oc.api_server.Service.*;
import com.oc.api_server.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MainConfig implements WebMvcConfigurer {

    //소스=========================================================================================================
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private SessionRepository sessionRepository;




    //서비스=========================================================================================================

    @Bean
    public UserService userService(){
        return new UserService(userRepository,security());
    }

    @Bean
    public CertificationService certificationService(){
        return new CertificationService(emailSender,security(),userRepository);
    }

    @Bean
    public ReviewService reviewService(){
        return new ReviewService(reviewRepository);
    }


    //특수기능=========================================================================================================
    @Bean
    Security security(){
        return new Security();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("으딜 감히");
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/Review/**");
    }
}
