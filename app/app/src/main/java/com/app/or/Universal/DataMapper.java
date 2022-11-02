package com.app.or.Universal;

import android.graphics.Bitmap;

import com.app.or.Config.Universal;
import com.app.or.DTO.Review;
import com.app.or.DTO.SimpleReview;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataMapper {

    String Sep = "|Img|";
    String UnSep = "\\|Img\\|";
    public Review ReviewDeserialization(String[] data){
        Review review = new Review();
        review.setId(data[0].equals("null") ? null : Long.parseLong(data[0]));
        review.setTitle(data[1].equals("null") ? null : data[1]);
        review.setPreview(data[2].equals("null") ? null : data[2]);
        review.setMain(data[3].equals("null") ? null : data[3]);

        List<Bitmap> answer = new ArrayList<>();
        if(!data[4].equals("null")){
            String[] read = data[4].split(UnSep);
            for(String t : read){
                answer.add(Universal.imageHelper.StringToImage(t));
            }
            review.setImage_txt(null);
        }
        review.setImage_txt(answer);

        review.setAddress(data[5].equals("null") ? null : Integer.parseInt(data[5]));
        review.setX(data[6].equals("null") ? null : Double.parseDouble(data[6]));
        review.setY(data[7].equals("null") ? null : Double.parseDouble(data[7]));

        review.setReview_type(data[8].equals("null") ? null : Integer.parseInt(data[8]));
        review.setGuarantee(data[9].equals("null") ? null : Integer.parseInt(data[9]));
        review.setMoney(data[10].equals("null") ? null : Integer.parseInt(data[10]));
        review.setGood(data[11].equals("null") ? null : Integer.parseInt(data[11]));

        review.setReview_owner(data[12].equals("null") ? null : Long.parseLong(data[12]));

        review.setOwner_rating(data[13].equals("null") ? null : Float.parseFloat(data[13]));
        review.setSize(data[14].equals("null") ? null : Float.parseFloat(data[14]));
        review.setNoise(data[15].equals("null") ? null : Float.parseFloat(data[15]));
        review.setService(data[16].equals("null") ? null : Float.parseFloat(data[16]));
        review.setHygiene(data[17].equals("null") ? null : Float.parseFloat(data[17]));
        review.setSafety(data[18].equals("null") ? null : Float.parseFloat(data[18]));
        review.setTemperature(data[19].equals("null") ? null : Float.parseFloat(data[19]));

        review.setCreate_day(data[20].equals("null") ? null : data[20].substring(0,11));

        review.setInput_address(data[21].equals("null") ? null : data[21]);
        review.setOwn(Boolean.parseBoolean(data[22]));
        review.setNickname(data[23]);
        return review;
    }

    public List<String> ReviewSerialization(Review review){
        List<String> array = new ArrayList<>();
        array.add(review.getId()+"");
        array.add(review.getTitle()+"");
        array.add(review.getPreview()+"");
        array.add(review.getMain()+"");

        StringBuffer sb = new StringBuffer();
        for(Bitmap b : review.getImage_txt()){
            sb.append(Universal.imageHelper.ImageToString(b)+Sep);
        }
        if(sb.length() == 0){
            array.add("null");
        }else{
            array.add(sb.toString());
        }


        array.add(review.getAddress()+"");
        array.add(review.getX()+"");
        array.add(review.getY()+"");

        array.add(review.getReview_type()+"");
        array.add(review.getGuarantee()+"");
        array.add(review.getMoney()+"");
        array.add(review.getGood()+"");

        array.add(review.getReview_owner()+"");

        array.add(review.getOwner_rating()+"");
        array.add(review.getSize()+"");
        array.add(review.getNoise()+"");
        array.add(review.getService()+"");
        array.add(review.getHygiene()+"");
        array.add(review.getSafety()+"");
        array.add(review.getTemperature()+"");
        array.add(review.getCreate_day()+"");

        array.add(review.getInput_address());

        return array;
    }

    public SimpleReview SimpleReviewDeserialization(String[] data){
        SimpleReview review = new SimpleReview();
        review.setId(data[0].equals("null") ? null : Long.parseLong(data[0]));
        review.setTitle(data[1].equals("null") ? null : data[1]);
        review.setPreview(data[2].equals("null") ? null : data[2]);
        review.setGood(data[3].equals("null") ? null : Integer.parseInt(data[3]));
        review.setOwner_rating(data[4].equals("null") ? null : Float.parseFloat(data[4]));
        review.setWhere(data[5].equals("null") ? null : Integer.parseInt(data[5]));
        review.setCreateTime(data[6].equals("null") ? null : data[6].substring(0,11));
        return review;
    }
}
