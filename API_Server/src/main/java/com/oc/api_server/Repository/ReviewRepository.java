package com.oc.api_server.Repository;


import com.oc.api_server.VO.Review;
import com.oc.api_server.VO.SimpleReview;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReviewRepository {


    public void SaveReview(Review review);
    public Review getReview(Long id);

}
