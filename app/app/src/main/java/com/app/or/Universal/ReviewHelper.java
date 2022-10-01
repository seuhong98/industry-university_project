package com.app.or.Universal;

import com.app.or.Config.Universal;
import com.app.or.DTO.MakeReview1;
import com.app.or.DTO.Review;
import com.app.or.DTO.Review_preview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReviewHelper {
    static final ImageHelper image = new ImageHelper();

    public static Review getReview(long id){
        return  null;
    }

    public static List<Review_preview> getPreviewReview(byte division, int paging){
        return  null;
    }

    public static boolean makeReview(MakeReview1 mr1){
        List<String> params = new ArrayList<>();
        params.add("title");
        params.add(ConvertToServer(mr1.getTitle()));

        params.add("preview");
        for(int i=0;i<mr1.getMain().length;i++){
            if(mr1.getMain()[i].length() != 0){
                if(mr1.getMain()[i].length()>10){
                    params.add(ConvertToServer(mr1.getMain()[i].substring(0,10)));
                }else{
                    params.add(ConvertToServer(mr1.getMain()[i]));
                }
                break;
            }
        }
        params.add("is_image");
        if(mr1.getImageAddress().length != 0){
            params.add("true");
        }else{
            params.add("false");
        }
        params.add("address1");
        params.add(mr1.getAddress1());
        params.add("address2");
        params.add(mr1.getAddress2());
        params.add("x");
        params.add(mr1.getX()+"");
        params.add("y");
        params.add(mr1.getY()+"");

        params.add("guarantee");
        params.add(mr1.getGuarantee()+"");

        params.add("price");
        params.add(mr1.getPrice()+"");

        params.add("money");
        params.add(mr1.getMoney()+"");

        params.add("management");
        params.add(mr1.getManagement()+"");

        params.add("total");
        params.add(mr1.getTotal()+"");

        params.add("main");
        StringBuffer sb = new StringBuffer();
        for(int i =0 ;i<mr1.getMain().length;i++){
            sb.append(ConvertToServer(mr1.getMain()[i]));
            if(i<mr1.getImageAddress().length){
                sb.append("|*|"+ImageHelper.ImageToString(mr1.getImageAddress()[i])+ "|*|");
            }
        }
        params.add(sb.toString());
        try {
//            String answer = HttpsHelper.HttpConnectWithParams("Review1/saveReview1",params).readLine();
//            if(answer.equals("Pass")){
//                return true;
//            }else {
//                return false;
//            }
            Universal.httpsHelper.HttpConnectWithParams("Review1/saveReview1",params);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    private static String ConvertToServer(String input){
        return input.replace("&","a**b**a").replace("=","b**a**b").replace("%","c**b**c");
    }
    private static String ConvertToApp(String input){
        return input.replace("a**b**a","&").replace("b**a**b","=").replace("c**b**c","%");
    }
}
