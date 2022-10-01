package com.oc.api_server.Repository;


import com.oc.api_server.VO.Review;
import com.oc.api_server.VO.SimpleReview;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReviewRepository {
    public List<SimpleReview> getList();

    public List<SimpleReview> getListContinue(long last);

    public void SaveReview(Review review);


    public void SaveImage(Map<String,Object> params);

}
