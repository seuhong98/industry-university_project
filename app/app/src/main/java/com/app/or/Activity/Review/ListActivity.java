package com.app.or.Activity.Review;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.or.Config.Universal;
import com.app.or.DTO.SimpleReview;
import com.app.or.R;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;


public class ListActivity extends AppCompatActivity {

    Long last;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_review_list);
//        List<SimpleReview> simpleReviews = new ArrayList<>();
//        List<String[]> get =  Universal.NETWORK.RequestArray("Review/GetCurrentList",null);
//        for(String[] g : get){
//            simpleReviews.add(Universal.dataMapper.SimpleReviewDeserialization(g));
//        }
//        List<String> p = new ArrayList<>();
//        p.add(simpleReviews.get(simpleReviews.size()-1).getId()+"");
//        //추가하면
//        List<String[]> more =  Universal.NETWORK.RequestArray("Review/GetList",p);
//        for(String[] g : more){
//            simpleReviews.add(Universal.dataMapper.SimpleReviewDeserialization(g));
//        }
//
//
//        //마을단위 필터 고르면
//        simpleReviews = new ArrayList<>();
//        List<String[]> get2 =  Universal.NETWORK.RequestArray("Review/getCurrentListByPlace",null);
//        for(String[] g : get2){
//            simpleReviews.add(Universal.dataMapper.SimpleReviewDeserialization(g));
//        }
//        List<String> p2 = new ArrayList<>();
//        p2.add(simpleReviews.get(simpleReviews.size()-1).getId()+"");
//        //추가하면
//        List<String[]> more2 =  Universal.NETWORK.RequestArray("Review/getListByPlace",p2);
//        for(String[] g : more2){
//            simpleReviews.add(Universal.dataMapper.SimpleReviewDeserialization(g));
//        }
//
//
//        //별점 순위
//        simpleReviews = new ArrayList<>();
//        List<String[]> get3 =  Universal.NETWORK.RequestArray("Review/getCurrentListByRating",null);
//        for(String[] g : get3){
//            simpleReviews.add(Universal.dataMapper.SimpleReviewDeserialization(g));
//        }
//        List<String> p3 = new ArrayList<>();
//        p3.add(simpleReviews.get(simpleReviews.size()-1).getId()+"");
//        //추가하면
//        List<String[]> more3 =  Universal.NETWORK.RequestArray("Review/getListByRating",p3);
//        for(String[] g : more3){
//            simpleReviews.add(Universal.dataMapper.SimpleReviewDeserialization(g));
//        }
    }
}