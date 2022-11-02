package com.oc.api_server.Repository;


import com.oc.api_server.VO.Review;
import com.oc.api_server.VO.SimpleReview;
import com.oc.api_server.VO.XY;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReviewRepository {


    public void SaveReview(Review review, int uni_code);
    public Review getReview(Long id, int uni_code);


    List<SimpleReview> getCurrent(int uni_code);

    List<SimpleReview> getMyCurrent(long uid);

    List<SimpleReview> getMyGood(long uid);
    List<SimpleReview> getFilterList(int uni_code,String order, List<Integer> list);

    public void Good(Long id);
    public void Good_Count(Long id,Long uid);

    public int is_good(Long id,Long uid);

    public void Bad(Long id);
    public void Bad_Count(Long id,Long uid);

    public int is_Bad(Long id,Long uid);

    public Long Delete(Long id, Long uid);

    public String GetNickName(Long uid);

    public List<XY> getXY(int uni_code);

    List<SimpleReview> GetXYList(double x,double y);

}
