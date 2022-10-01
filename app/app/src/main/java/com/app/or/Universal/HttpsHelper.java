package com.app.or.Universal;

import android.os.StrictMode;
import android.webkit.CookieManager;

import com.app.or.Config.Universal;
import com.app.or.DTO.Address;
import com.app.or.Library.GetConnection;
import com.app.or.Library.KakaoApi;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class HttpsHelper{

    long beforeTime;










    public String AutoLogin(){
        try {
            StringTokenizer str = new ShareData().GetEmailAndPassword();
            List<String> parms = new ArrayList<>();
            parms.add(str.nextToken());
            parms.add(str.nextToken());
            GetConnection getConnection = new GetConnection(parms);
            getConnection.start();
            getConnection.join();
            beforeTime = System.currentTimeMillis();
            return getConnection.answer;
        }catch (Exception e){
            return "ERR";
        }
    }

    public String Login(String Email , String Pw){
        try {
            List<String> parms = new ArrayList<>();
            parms.add(Email);
            parms.add(Pw);
            GetConnection getConnection = new GetConnection(parms);
            getConnection.start();
            getConnection.join();
            beforeTime = System.currentTimeMillis();
            if(getConnection.answer.equals("True")){
                ShareData t = new ShareData();
                t.SaveEmailAndPassword(Email,Pw);
            }
            return getConnection.answer;
        }catch (Exception e){
            return "ERR";
        }
    }




    public BufferedReader HttpConnectWithParams(String Request, List<String> Params){
        if((Universal.abbr.getId() == null) || System.currentTimeMillis() -  beforeTime > 800000  ){
            String answer =  AutoLogin();
            if(!answer.equals("True")){
                return null;
            }
        }
        try {
            GetConnection connection = new GetConnection(Request,Params);
            connection.start();
            connection.join();
            beforeTime = System.currentTimeMillis();
            return connection.br;
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }

    public Address KakaoKeywordSearch(String query){
        StrictMode.ThreadPolicy strictMode = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(strictMode);
        Address address = null;
        try {
            KakaoApi kakaoApi = new KakaoApi(query);
            kakaoApi.run();
            kakaoApi.join();
            address = new Address(kakaoApi.br.readLine());
        }catch (Exception e){
            e.printStackTrace();
        }
        return address;
    }
}
