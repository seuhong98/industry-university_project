package com.app.or.Library;

import com.app.or.Config.Universal;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class KakaoApi extends Thread{

    public BufferedReader br;
    String ReQuestQuery;
    int code;



    @Override
    public void run() {
        switch (code){
            case 1:
                findKeyword();
                break;
        }
    }

    public KakaoApi(String ReQuestQuery){
        code = 1;
        this.ReQuestQuery = ReQuestQuery;
    }


    public void findKeyword(){
        try {
            URL obj = new URL("https://dapi.kakao.com/v2/local/search/address.json");
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization","KakaoAK "+ Universal.abbr.GetKakaoKey());

            conn.setDoOutput(true);

            System.out.println("api query: "+ReQuestQuery);

            if(conn.getDoOutput()) {
                conn.getOutputStream().write(("query="+ReQuestQuery).getBytes());
                conn.getOutputStream().flush();
                conn.getOutputStream().close();
            }

            conn.connect();

            InputStream response = conn.getInputStream();
            InputStreamReader reader = new InputStreamReader(response,"UTF-8");
            br = new BufferedReader(reader);
        }catch (Exception e){e.printStackTrace();}
    }
}
