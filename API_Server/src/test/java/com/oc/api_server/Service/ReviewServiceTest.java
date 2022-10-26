package com.oc.api_server.Service;

import com.oc.api_server.Repository.SessionRepository;
import com.oc.api_server.VO.Review;
import com.oc.api_server.VO.publicKey;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ReviewServiceTest {

    @Autowired
    SessionRepository repository;
    @Test
    void saveReview() {
        List<publicKey> list = repository.getPublicKey();
        System.out.println("answer : ");
        for(publicKey p : list){
            System.out.println(p.getPublickey());
        }
    }
}