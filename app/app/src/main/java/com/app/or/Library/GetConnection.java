package com.app.or.Library;

import com.app.or.Config.Universal;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class GetConnection extends Thread{

    String address;
    String Request;
    List<String> params;

    /**
     * 1 : AutoLogin
     * 2 : Get Data With Params
     * 3 : Get Data
     * 4 : RequestLogin
     */
    int code;


    public String answer;

    public BufferedReader br;


    public GetConnection(String Request , List<String> params){
        this.Request = Request;
        this.params = params;
        this.code = 2;
    }

    public GetConnection(String Request){
        this.Request = Request;
        this.code = 3;
    }

    public GetConnection(List<String> params){
        this.params = params;
        this.code = 1;
    }

    public GetConnection(boolean is_null){
        if(is_null){
            this.code = 4;
        }else{
            this.code = 0;
        }
    }

    @Override
    public void run() {
        switch (code){
            case 1:
                AutoLogin();
                break;
            case 2:
                ReQuestWithParams();
                break;
            case 3:
                ReQuest();
                break;
        }
    }


    /**
     * 모든 트렌잭션 전에 세션을 확인해서 얻는다
     */
    public void AutoLogin(){
        try {
            URL obj = new URL(Universal.abbr.URL()+"Login/SignIn");
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
            conn.setDefaultUseCaches(false);
            conn.setDoOutput(true);


            conn.getOutputStream().write(("Email="+params.get(0)+"&Pw="+params.get(1)).getBytes());
            conn.getOutputStream().flush();
            conn.getOutputStream().close();

            conn.connect();

            InputStream response = conn.getInputStream();
            InputStreamReader reader = new InputStreamReader(response,"UTF-8");
            BufferedReader bf = new BufferedReader(reader);

            String get = bf.readLine();
            if(get.length()>13){
                Universal.abbr.setId(get);
                answer= "True";
            }else{
                answer= get;
            }
        }catch (Exception e){
            e.printStackTrace();
            answer= "Err";
        }
    }


    /**
     * 모든 트렌잭션 전에 세션을 확인해서 얻는다
     */
    private void ReQuestWithParams(){
        try {
            URL obj = new URL(Universal.abbr.URL()+Request);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
            conn.setRequestProperty("Cookie", "JSESSIONID="+Universal.abbr.getId());
            conn.setDefaultUseCaches(false);
            conn.setDoOutput(true);

            StringBuffer sb = new StringBuffer();

            sb.append("params=");
            StringBuffer ssb = new StringBuffer();

            for(String t : params){
                ssb.append(t+"*-*");
            }
            sb.append(ConvertToServer(Universal.security.encryptionBySessionKey(ssb.toString())));
            if(conn.getDoOutput()) {
                conn.getOutputStream().write((sb.toString()).getBytes());
                conn.getOutputStream().flush();
                conn.getOutputStream().close();
            }
            conn.connect();

            InputStream response = conn.getInputStream();
            InputStreamReader reader = new InputStreamReader(response,"UTF-8");
            br = new BufferedReader(reader);
            answer = "True";
        }catch (Exception e){
            e.printStackTrace();
            answer= "Err";
        }
    }

    private void ReQuest(){
        try {
            URL obj = new URL(Universal.abbr.URL()+Request);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
            conn.setRequestProperty("Cookie", "JSESSIONID="+Universal.abbr.getId());
            conn.setDefaultUseCaches(false);
            conn.setDoOutput(true);

            conn.connect();

            InputStream response = conn.getInputStream();
            InputStreamReader reader = new InputStreamReader(response,"UTF-8");
            br = new BufferedReader(reader);
            answer = "True";
        }catch (Exception e){
            e.printStackTrace();
            answer= "Err";
        }
    }


    private String ConvertToServer(String input){
        return input.replace("&","a**b**a").replace("=","b**a**b").replace("%","c**b**c");
    }

}
