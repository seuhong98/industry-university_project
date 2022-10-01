package com.oc.api_server.Service;

import com.oc.api_server.Repository.ReviewRepository;
import com.oc.api_server.VO.Review;
import com.oc.api_server.VO.SimpleReview;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ReviewService {
    private final ReviewRepository repository;

    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public String getList(){
        List<SimpleReview> list = repository.getList();
        StringBuffer sb = new StringBuffer();
        for(SimpleReview t : list){
            sb.append(t.getId()+" "+t.getTitle()+" "+t.getSimple_main()+" "+t.isIs_image()+"\n");
        }
        return sb.toString();
    }

    public String GetListContinue(long id){
        List<SimpleReview> list = repository.getListContinue(id);
        StringBuffer sb = new StringBuffer();
        for(SimpleReview t : list){
            sb.append(t.getId()+" "+t.getTitle()+" "+t.getSimple_main()+" "+t.isIs_image()+"\n");
        }
        return sb.toString();
    }

    public void SaveReview(Review review, long id){
        review.setReview_owner(id);

        repository.SaveReview(review);
    }

    public void SaveImage(String image,int count, long userid, long id){
        Map<String,Object> params = new HashMap<>();
        params.put("image_txt",image);
        params.put("count_num",count);
        params.put("userid",userid);
        params.put("id",id);
        repository.SaveImage(params);
    }
}
