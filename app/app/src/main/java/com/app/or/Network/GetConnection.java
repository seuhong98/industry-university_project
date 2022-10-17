package com.app.or.Network;

import com.app.or.Config.Universal;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetConnection extends Thread{

    public String Separator = "|Sep|";

    String Request;

    /**
     * 1 : Non_Session Connection
     * 2 : Encrypt Connection
     */
    boolean Encrypt;

    List<String> params;

    public boolean answer;

    public String data;

    public GetConnection(String Request, List<String> params,boolean Encrypt){
        this.Request = Request;
        this.params = params;
        this.Encrypt = Encrypt;
    }

    @Override
    public void run() {
        if(Encrypt){
            EncryptConnection();
        }else {
            NonEncryptConnection();
        }
    }


    public void NonEncryptConnection(){
        try {
            URL obj = new URL(Universal.memory.URL()+Request);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
            conn.setRequestProperty("Cookie", "JSESSIONID="+Universal.memory.getId());
            conn.setDefaultUseCaches(false);
            conn.setDoOutput(true);

            if(params != null){
                StringBuffer sb = new StringBuffer();

                sb.append("params=");
                StringBuffer StringParams = new StringBuffer();

                for(String t : params){
                    StringParams.append(t+Separator);
                }
                sb.append(ConvertSpecialToNormal(StringParams.toString()));
                if(conn.getDoOutput()) {
                    conn.getOutputStream().write((sb.toString()).getBytes());
                    conn.getOutputStream().flush();
                    conn.getOutputStream().close();
                }
            }
            conn.connect();

            InputStream response = conn.getInputStream();
            InputStreamReader reader = new InputStreamReader(response,"UTF-8");
            data = new BufferedReader(reader).readLine();
            answer = true;
        }catch (Exception e){
            e.printStackTrace();
            answer= false;
        }
    }

    public void EncryptConnection(){
        try {
            URL obj = new URL(Universal.memory.URL()+Request);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
            conn.setRequestProperty("Cookie", "JSESSIONID="+Universal.memory.getId());
            conn.setDefaultUseCaches(false);
            conn.setDoOutput(true);

            StringBuffer sb = new StringBuffer();


            if(params != null){
                sb.append("params=");
                StringBuffer StringParams = new StringBuffer();

                for(String t : params){
                    StringParams.append(t+Separator);
                }
                sb.append(ConvertSpecialToNormal(Universal.security.encryptionBySessionKey(StringParams.toString())));
                sb.append("&Signature=");
                sb.append(ConvertSpecialToNormal(Universal.security.Signature(StringParams.toString())));
                if(conn.getDoOutput()) {
                    conn.getOutputStream().write((sb.toString()).getBytes());
                    conn.getOutputStream().flush();
                    conn.getOutputStream().close();
                }
            }
            conn.connect();

            InputStream response = conn.getInputStream();
            InputStreamReader reader = new InputStreamReader(response,"UTF-8");
            String input =  new BufferedReader(reader).readLine();
            data = Universal.security.decryptionBySessionKey(input);
            answer = true;
        }catch (Exception e){
            e.printStackTrace();
            answer= false;
        }
    }

    private String ConvertSpecialToNormal(String Special){
        return Special.replace("&","a**b**a").replace("=","b**a**b").replace("%","c**b**c");
    }

}
