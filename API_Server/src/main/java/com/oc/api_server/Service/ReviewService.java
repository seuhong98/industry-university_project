package com.oc.api_server.Service;

import com.oc.api_server.Repository.ReviewRepository;
import com.oc.api_server.Useful.DataMapper;
import com.oc.api_server.VO.Review;
import com.oc.api_server.VO.SimpleReview;
import com.oc.api_server.VO.XY;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;

import java.util.ArrayList;
import java.util.List;


public class ReviewService {

    private String ListSeparator = "|List|";
    private String Separator = "|Sep|";
    private final ReviewRepository repository;
    @Autowired
    private DataMapper dataMapper;

    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public String getCurrentList(int uni_code){
        StringBuffer sb = new StringBuffer();
        List<SimpleReview> list = repository.getCurrent(uni_code);
        for(SimpleReview get : list){
            sb.append(dataMapper.SimpleReviewSerialization(get)+ListSeparator);
        }
        return sb.toString();
    }

    public String getMyCurrent(long uid){
        StringBuffer sb = new StringBuffer();
        List<SimpleReview> list = repository.getMyCurrent(uid);
        for(SimpleReview get : list){
            sb.append(dataMapper.SimpleReviewSerialization(get)+ListSeparator);
        }
        return sb.toString();
    }

    public String getMyGood(long uid){
        StringBuffer sb = new StringBuffer();
        List<SimpleReview> list = repository.getMyGood(uid);
        for(SimpleReview get : list){
            sb.append(dataMapper.SimpleReviewSerialization(get)+ListSeparator);
        }
        return sb.toString();
    }

    public String getFilterList(int uni_code,String code){
        char read[] = code.toCharArray();
        String order = code.charAt(0)+"";
        List<Integer> p = new ArrayList<>();
        for(int i=1;i<read.length;i++){
            if(read[i] == '1'){
                p.add(i-1);
            }
        }
        if(p.size() == 0){
            p.add(99999);
        }
        StringBuffer sb = new StringBuffer();
        List<SimpleReview> list = repository.getFilterList(uni_code,order,p);
        for(SimpleReview get : list){
            sb.append(dataMapper.SimpleReviewSerialization(get)+ListSeparator);
        }
        return sb.toString();
    }



    public void saveReview(String[] data, long id,int uni_code){
        Review review = dataMapper.ReviewDeserialization(data);
        review.setReview_owner(id);
        review.setGood(0);
        repository.SaveReview(review,uni_code);
    }


    public Review getReview(long parseLong,int uni_code) {
        return repository.getReview(parseLong,uni_code);
    }

    public void Good(Long id){
        repository.Good(id);
    }

    public void Good_Count(Long id,Long uid){
        repository.Good_Count(id,uid);
    }

    public int is_good(Long id,Long uid){
        return repository.is_good(id, uid);
    }

    public void Bad(Long id){
        repository.Bad(id);
    }
    public void Bad_Count(Long id,Long uid){
        repository.Bad_Count(id,uid);
    }

    public int is_Bad(Long id,Long uid){
        return repository.is_Bad(id, uid);
    }

    public void Delete(Long id, Long Uid){
        repository.Delete(id,Uid);
    }

    public String GetNickName(Long uid){
        return repository.GetNickName(uid);
    }

    public String getXY(int uni_code){
        StringBuffer sb = new StringBuffer();
        List<XY> read = repository.getXY(uni_code);
        for(XY t : read){
            sb.append(t.getX()+" "+t.getY()+Separator);
        }
        return sb.toString();
    }

    public String GetXYList(double x,double y){
        StringBuffer sb = new StringBuffer();
        List<SimpleReview> list = repository.GetXYList(x,y);
        for(SimpleReview get : list){
            sb.append(dataMapper.SimpleReviewSerialization(get)+ListSeparator);
        }
        return sb.toString();
    }
}
