package com.oc.api_server.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService service;

    @Test
    void signUp() {
        service.SignUp("닉네임입니다1","1234","tlstmdgh369@kangwon.ac.kr");
        System.out.println("실행");
    }
}