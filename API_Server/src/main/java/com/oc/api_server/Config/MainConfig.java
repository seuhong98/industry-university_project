package com.oc.api_server.Config;

import com.oc.api_server.Repository.*;
import com.oc.api_server.Service.*;
import com.oc.api_server.Useful.DataMapper;
import com.oc.api_server.interceptor.LoginInterceptor;
import com.oc.api_server.interceptor.SessionInterceptor;
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

    @Autowired
    private GetSequence getSequence;

    @Autowired
    private SimpleReviewRepository simpleReviewRepository;




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
        return new ReviewService(reviewRepository,simpleReviewRepository,getSequence);
    }

    @Bean
    ConfirmService signatureService(){return new ConfirmService(security());}


    //특수기능=========================================================================================================
    @Bean
    Security security(){
        return new Security();
    }

    @Bean
    DataMapper dataMapper(){return new DataMapper();}


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionInterceptor())
                .addPathPatterns("/Review/**")
                .addPathPatterns("/Login/**");
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/Review/**");
    }
}
