package com.oc.api_server.Service;

import com.oc.api_server.VO.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReviewServiceTest {

    @Autowired
    ReviewService service;

    @Test
    void saveReview() {
        Review review = new Review();




        service.SaveReview(review,3);
        System.out.println("실행");
    }
}