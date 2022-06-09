package com.oc.api_server.Repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @Commit
    void createUser() {
        System.out.println("출력 : "+userRepository.CreateUser("hihi","apple","tlstmdgh369@naver.com"));
    }

    @Test
    void findByNickname() {
    }
}