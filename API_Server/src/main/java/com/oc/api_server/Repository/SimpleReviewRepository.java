package com.oc.api_server.Repository;


import com.oc.api_server.VO.Review;
import com.oc.api_server.VO.SimpleReview;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Mapper
public interface SimpleReviewRepository {

    void saveReview(SimpleReview simpleReview);

    List<SimpleReview> getCurrent();
    List<SimpleReview> getList(Long id);


    List<SimpleReview> getCurrentListByPlace(Integer place);
    List<SimpleReview> getListByPlace(Integer place,Long id);


    List<SimpleReview> getCurrentListByRating();
    List<SimpleReview> getListByRating(Long id);

    List<SimpleReview> getMyList(Long id);
}
