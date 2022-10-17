package com.oc.api_server.Useful;

import com.oc.api_server.VO.Review;
import com.oc.api_server.VO.SimpleReview;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DataMapper {

    public String Separator = "|Sep|";

    public String ReviewSerialization(Review review){
        StringBuffer sb = new StringBuffer();
        sb.append(review.getId()+Separator);
        sb.append(review.getTitle()+Separator);
        sb.append(review.getPreview()+Separator);
        sb.append(review.getIs_image()+Separator);
        sb.append(review.getSimple_image()+Separator);
        sb.append(review.getMain()+Separator);
        sb.append(review.getAddress()+Separator);
        sb.append(review.getX()+Separator);
        sb.append(review.getY()+Separator);
        sb.append(review.getReview_type()+Separator);
        sb.append(review.getGuarantee()+Separator);
        sb.append(review.getMoney()+Separator);
        sb.append(review.getManagement()+Separator);
        sb.append(review.getGood()+Separator);
        sb.append(review.getReview_owner()+Separator);
        sb.append(review.getOwner_rating()+Separator);
        sb.append(review.getCreate_day()+Separator);
        return sb.toString();
    }

    public Review ReviewDeserialization(String[] data){
        Review review = new Review();
        review.setTitle(data[0].equals("null") ? null : data[0]);
        review.setPreview(data[1].equals("null") ? null : data[1]);
        review.setIs_image(data[2].equals("null") ? null : Integer.parseInt(data[2]));
        review.setSimple_image(data[3].equals("null") ? null : data[3]);
        review.setMain(data[4].equals("null") ? null : data[4]);
        review.setAddress(data[5].equals("null") ? null : Integer.parseInt(data[5]));
        review.setX(data[6].equals("null") ? null : Double.parseDouble(data[6]));
        review.setY(data[7].equals("null") ? null : Double.parseDouble(data[7]));
        review.setReview_type(data[8].equals("null") ? null : Integer.parseInt(data[8]));
        review.setGuarantee(data[9].equals("null") ? null : Integer.parseInt(data[9]));
        review.setMoney(data[10].equals("null") ? null : Integer.parseInt(data[10]));
        review.setManagement(data[11].equals("null") ? null : Integer.parseInt(data[11]));
        review.setGood(0);
        review.setOwner_rating(data[12].equals("null") ? null : Float.parseFloat(data[12]));
        review.setCreate_day(LocalDate.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        return review;
    }

    public String SimpleReviewSerialization(SimpleReview simpleReview){
        StringBuffer sb = new StringBuffer();
        sb.append(simpleReview.getId()+Separator);
        sb.append(simpleReview.getTitle()+Separator);
        sb.append(simpleReview.getPreview()+Separator);
        sb.append(simpleReview.getIs_image()+Separator);
        sb.append(simpleReview.getSimple_image()+Separator);
        sb.append(simpleReview.getAddress()+Separator);
        sb.append(simpleReview.getX()+Separator);
        sb.append(simpleReview.getY()+Separator);
        sb.append(simpleReview.getReview_type()+Separator);
        sb.append(simpleReview.getGuarantee()+Separator);
        sb.append(simpleReview.getMoney()+Separator);
        sb.append(simpleReview.getManagement()+Separator);
        sb.append(simpleReview.getGood()+Separator);
        sb.append(simpleReview.getReview_owner()+Separator);
        sb.append(simpleReview.getOwner_rating()+Separator);
        return sb.toString();
    }

    public SimpleReview SimpleReviewDeserialization(String[] data){
        SimpleReview review = new SimpleReview();
        review.setTitle(data[0].equals("null") ? null : data[0]);
        review.setPreview(data[1].equals("null") ? null : data[1]);
        review.setIs_image(data[2].equals("null") ? null : Integer.parseInt(data[2]));
        review.setSimple_image(data[3].equals("null") ? null : data[3]);
        review.setAddress(data[5].equals("null") ? null : Integer.parseInt(data[5]));
        review.setX(data[6].equals("null") ? null : Double.parseDouble(data[6]));
        review.setY(data[7].equals("null") ? null : Double.parseDouble(data[7]));
        review.setReview_type(data[8].equals("null") ? null : Integer.parseInt(data[8]));
        review.setGuarantee(data[9].equals("null") ? null : Integer.parseInt(data[9]));
        review.setMoney(data[10].equals("null") ? null : Integer.parseInt(data[10]));
        review.setManagement(data[11].equals("null") ? null : Integer.parseInt(data[11]));
        review.setGood(0);
        review.setOwner_rating(data[12].equals("null") ? null : Float.parseFloat(data[12]));
        return review;
    }
}
