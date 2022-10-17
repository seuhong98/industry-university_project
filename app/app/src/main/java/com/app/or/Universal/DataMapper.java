package com.app.or.Universal;

import com.app.or.DTO.Review;
import com.app.or.DTO.SimpleReview;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DataMapper {
    public Review ReviewDeserialization(String[] data){
        Review review = new Review();
        review.setId(data[0].equals("null") ? null : Long.parseLong(data[0]));
        review.setTitle(data[1].equals("null") ? null : data[1]);
        review.setPreview(data[2].equals("null") ? null : data[2]);
        review.setIs_image(data[3].equals("null") ? null : Integer.parseInt(data[3]));
        review.setSimple_image(data[4].equals("null") ? null : data[4]);
        review.setMain(data[5].equals("null") ? null : data[5]);
        review.setAddress(data[6].equals("null") ? null : Integer.parseInt(data[6]));
        review.setX(data[7].equals("null") ? null : Double.parseDouble(data[7]));
        review.setY(data[8].equals("null") ? null : Double.parseDouble(data[8]));
        review.setReview_type(data[9].equals("null") ? null : Integer.parseInt(data[9]));
        review.setGuarantee(data[10].equals("null") ? null : Integer.parseInt(data[10]));
        review.setMoney(data[11].equals("null") ? null : Integer.parseInt(data[11]));
        review.setManagement(data[12].equals("null") ? null : Integer.parseInt(data[12]));
        review.setGood(data[13].equals("null") ? null : Integer.parseInt(data[13]));
        review.setReview_owner(data[14].equals("null") ? null : Long.parseLong(data[14]));
        review.setOwner_rating(data[15].equals("null") ? null : Float.parseFloat(data[15]));
        review.setCreate_day(data[16].equals("null") ? null : data[16]);
        return review;
    }

    public SimpleReview SimpleReviewDeserialization(String[] data){
        SimpleReview review = new SimpleReview();
        review.setId(data[0].equals("null") ? null : Long.parseLong(data[0]));
        review.setTitle(data[1].equals("null") ? null : data[1]);
        review.setPreview(data[2].equals("null") ? null : data[2]);
        review.setIs_image(data[3].equals("null") ? null : Integer.parseInt(data[3]));
        review.setSimple_image(data[4].equals("null") ? null : data[4]);
        review.setAddress(data[5].equals("null") ? null : Integer.parseInt(data[5]));
        review.setX(data[6].equals("null") ? null : Double.parseDouble(data[6]));
        review.setY(data[7].equals("null") ? null : Double.parseDouble(data[7]));
        review.setReview_type(data[8].equals("null") ? null : Integer.parseInt(data[8]));
        review.setGuarantee(data[9].equals("null") ? null : Integer.parseInt(data[9]));
        review.setMoney(data[10].equals("null") ? null : Integer.parseInt(data[10]));
        review.setManagement(data[11].equals("null") ? null : Integer.parseInt(data[11]));
        review.setGood(data[12].equals("null") ? null : Integer.parseInt(data[12]));
        review.setReview_owner(data[13].equals("null") ? null : Long.parseLong(data[13]));
        review.setOwner_rating(data[14].equals("null") ? null : Float.parseFloat(data[14]));
        return review;
    }
}
