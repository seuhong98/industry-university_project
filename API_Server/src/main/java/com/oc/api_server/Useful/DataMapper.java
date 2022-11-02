package com.oc.api_server.Useful;

import com.oc.api_server.VO.Review;
import com.oc.api_server.VO.SimpleReview;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataMapper {

    public String Separator = "|Sep|";
    public Review ReviewDeserialization(String[] data){
        Review review = new Review();
        review.setId(data[0].equals("null") ? null : Long.parseLong(data[0]));
        review.setTitle(data[1].equals("null") ? null : data[1]);
        review.setPreview(data[2].equals("null") ? null : data[2]);
        review.setMain(data[3].equals("null") ? null : data[3]);
        review.setImage_txt(data[4].equals("null") ? null : data[4]);

        review.setAddress(data[5].equals("null") ? null : Integer.parseInt(data[5]));
        review.setX(data[6].equals("null") ? null : Double.parseDouble(data[6]));
        review.setY(data[7].equals("null") ? null : Double.parseDouble(data[7]));

        review.setReview_type(data[8].equals("null") ? null : Integer.parseInt(data[8]));
        review.setGuarantee(data[9].equals("null") ? null : Integer.parseInt(data[9]));
        review.setMoney(data[10].equals("null") ? null : Integer.parseInt(data[10]));
        review.setGood(data[11].equals("null") ? null : Integer.parseInt(data[11]));

        review.setReview_owner(data[12].equals("null") ? null : Long.parseLong(data[12]));

        review.setOwner_rating(data[13].equals("null") ? null : Float.parseFloat(data[13]));
        review.setSize_room(data[14].equals("null") ? null : Float.parseFloat(data[14]));
        review.setNoise(data[15].equals("null") ? null : Float.parseFloat(data[15]));
        review.setService(data[16].equals("null") ? null : Float.parseFloat(data[16]));
        review.setHygiene(data[17].equals("null") ? null : Float.parseFloat(data[17]));
        review.setSafety(data[18].equals("null") ? null : Float.parseFloat(data[18]));
        review.setTemperature(data[19].equals("null") ? null : Float.parseFloat(data[19]));

        review.setCreate_day(data[20].equals("null") ? null : data[20]);
        review.setInput_address(data[21].equals("null") ? null : data[21]);
        return review;
    }

    public String SimpleReviewSerialization(SimpleReview simpleReview){
        StringBuffer sb = new StringBuffer();
        sb.append(simpleReview.getId()+Separator);
        sb.append(simpleReview.getTitle()+Separator);
        sb.append(simpleReview.getPreview()+Separator);
        sb.append(simpleReview.getGood()+Separator);
        sb.append(simpleReview.getOwner_rating()+Separator);
        sb.append(simpleReview.getAddress()+Separator);
        sb.append(simpleReview.getCreate_day());
        return sb.toString();
    }
    public String ReviewSerialization(Review review){
        StringBuffer sb = new StringBuffer();
        sb.append(review.getId()+Separator);
        sb.append(review.getTitle()+Separator);
        sb.append(review.getPreview()+Separator);

        sb.append(review.getMain()+Separator);

        sb.append(review.getImage_txt()+Separator);

        sb.append(review.getAddress()+Separator);
        sb.append(review.getX()+Separator);
        sb.append(review.getY()+Separator);

        sb.append(review.getReview_type()+Separator);
        sb.append(review.getGuarantee()+Separator);
        sb.append(review.getMoney()+Separator);

        sb.append(review.getGood()+Separator);

        sb.append(review.getReview_owner()+Separator);

        sb.append(review.getOwner_rating()+Separator);
        sb.append(review.getSize_room()+Separator);
        sb.append(review.getNoise()+Separator);
        sb.append(review.getService()+Separator);
        sb.append(review.getHygiene()+Separator);
        sb.append(review.getSafety()+Separator);
        sb.append(review.getTemperature()+Separator);

        sb.append(review.getCreate_day()+Separator);

        sb.append(review.getInput_address()+"");
        return sb.toString();
    }

}
