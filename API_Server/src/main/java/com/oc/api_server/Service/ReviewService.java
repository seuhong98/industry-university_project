package com.oc.api_server.Service;

import com.oc.api_server.Repository.GetSequence;
import com.oc.api_server.Repository.ReviewRepository;
import com.oc.api_server.Repository.SimpleReviewRepository;
import com.oc.api_server.Useful.DataMapper;
import com.oc.api_server.VO.Review;
import com.oc.api_server.VO.SimpleReview;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ReviewService {

    private String ListSeparator = "|List|";
    private final ReviewRepository repository;
    private final SimpleReviewRepository simpleReview;
    private final GetSequence getSequence;
    @Autowired
    private DataMapper dataMapper;

    public ReviewService(ReviewRepository repository, SimpleReviewRepository simpleReview, GetSequence getSequence) {
        this.repository = repository;
        this.simpleReview = simpleReview;
        this.getSequence = getSequence;
    }

//    public String getCurrentList(){
//        StringBuffer sb = new StringBuffer();
//        List<SimpleReview> list = simpleReview.getCurrent();
//        for(SimpleReview get : list){
//            sb.append(dataMapper.SimpleReviewSerialization(get)+ListSeparator);
//        }
//        return sb.toString();
//    }
//
//    public String getList(Long id){
//        StringBuffer sb = new StringBuffer();
//        List<SimpleReview> list = simpleReview.getList(id);
//        for(SimpleReview get : list){
//            sb.append(dataMapper.SimpleReviewSerialization(get)+ListSeparator);
//        }
//        return sb.toString();
//    }
//
//    public String getCurrentListByPlace(Integer place){
//        StringBuffer sb = new StringBuffer();
//        List<SimpleReview> list = simpleReview.getCurrentListByPlace(place);
//        for(SimpleReview get : list){
//            sb.append(dataMapper.SimpleReviewSerialization(get)+ListSeparator);
//        }
//        return sb.toString();
//    }
//    public String getListByPlace(Integer place,Long id){
//        StringBuffer sb = new StringBuffer();
//        List<SimpleReview> list = simpleReview.getListByPlace(place,id);
//        for(SimpleReview get : list){
//            sb.append(dataMapper.SimpleReviewSerialization(get)+ListSeparator);
//        }
//        return sb.toString();
//    }
//
//
//    public String getCurrentListByRating(){
//        StringBuffer sb = new StringBuffer();
//        List<SimpleReview> list = simpleReview.getCurrentListByRating();
//        for(SimpleReview get : list){
//            sb.append(dataMapper.SimpleReviewSerialization(get)+ListSeparator);
//        }
//        return sb.toString();
//    }
//    public String getListByRating(Long id){
//        StringBuffer sb = new StringBuffer();
//        List<SimpleReview> list = simpleReview.getListByRating(id);
//        for(SimpleReview get : list){
//            sb.append(dataMapper.SimpleReviewSerialization(get)+ListSeparator);
//        }
//        return sb.toString();
//    }
//
//    public String getMyList(Long id){
//        StringBuffer sb = new StringBuffer();
//        List<SimpleReview> list = simpleReview.getMyList(id);
//        for(SimpleReview get : list){
//            sb.append(dataMapper.SimpleReviewSerialization(get)+ListSeparator);
//        }
//        return sb.toString();
//    }

    public void saveReview(String[] data, long id){
        Review review = dataMapper.ReviewDeserialization(data);
        review.setReview_owner(id);
        repository.SaveReview(review);
    }




}
