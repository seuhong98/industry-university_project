package com.app.or.Network;

import android.os.StrictMode;

import com.app.or.Config.Universal;
import com.app.or.DTO.Address;
import com.app.or.FileSystem.FileSystem;

import java.util.ArrayList;
import java.util.List;

public class Network {

    long beforeTime;
    private String UnListSeparator = "\\|List\\|";
    public String UnSeparator = "\\|Sep\\|";

    /**
     * 퍼블릭키 버전
     * @return 버전
     */
    public int PublicKeyVersion(){
        try {
            GetConnection getConnection = new GetConnection("Session/TouchPublicKey",null,false);
            return Integer.parseInt(getConnection.data.split(UnSeparator)[0]);
        }catch (Exception e){
            return -1;
        }

    }


    /**
     * 퍼블릭키 받아오기
     */
    public void SettingPublicKey(){
        try {
            GetConnection getConnection = new GetConnection("Session/GetPublicKey",null,false);
            getConnection.start();
            getConnection.join();
            Universal.fileSystem.SetPublicKey(getConnection.data.split(UnSeparator));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void MakeSession(){
        try {
            List<String> params = new ArrayList<>();
            Universal.security.init();
            params.add(Universal.security.GetSessionKeyByRSA());
            beforeTime = System.currentTimeMillis();
            GetConnection getConnection = new GetConnection("Session/MakeSession",params,false);
            getConnection.start();
            getConnection.join();
            String read[] = (Universal.security.decryptionBySessionKey(getConnection.data)).split(UnSeparator);
            Universal.memory.setOrder(Integer.parseInt(read[1]));
            Universal.memory.setId(read[0]);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String Login(String Email , String Pw){
        try {
            if(System.currentTimeMillis() - beforeTime > Universal.memory.getSessionTime()){
                MakeSession();
            }
            List<String> params = new ArrayList<>();
            params.add(Email);
            params.add(Pw);
            beforeTime = System.currentTimeMillis();
            GetConnection getConnection = new GetConnection("Login/SignIn",params,true);
            getConnection.start();
            getConnection.join();
            String data = getConnection.data.split(UnSeparator)[0];
            if(getConnection.answer){
                if(data.equals("TRUE")){
                    Universal.fileSystem.SetAccount(Email,Pw);
                    return "TRUE";
                }else if(data.equals("FALSE")){
                    return "FALSE";
                }else if (data.equals("TooManyWrong")){
                    return "TooManyWrong";
                }else{
                    return "ERR";
                }
            }else {
                return "ERR";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "ERR";
        }
    }

    public String AutoLogin(){
        String[] read =  Universal.fileSystem.GetAccount();
        return Login(read[0],read[1]);
    }


    public String[] Request(String Request, List<String> Params){
        try {
            if(System.currentTimeMillis() - beforeTime > Universal.memory.getSessionTime()){
                MakeSession();
                AutoLogin();
            }
            beforeTime = System.currentTimeMillis();
            GetConnection getConnection = new GetConnection(Request,Params,true);
            getConnection.start();
            getConnection.join();
            if(getConnection.answer){
                return getConnection.data.split(UnSeparator);
            }else{
                return null;
            }
        }catch (Exception e){
            return null;
        }
    }

    public List<String[]> RequestArray(String Request, List<String> Params){
        try {
            if(System.currentTimeMillis() - beforeTime > Universal.memory.getSessionTime()){
                MakeSession();
                AutoLogin();
            }
            beforeTime = System.currentTimeMillis();
            GetConnection getConnection = new GetConnection(Request,Params,true);
            getConnection.start();
            getConnection.join();
            String[] read = getConnection.data.split(UnListSeparator);
            if(getConnection.answer){
                List<String[]> answer = new ArrayList<>();
                for(String t :read){
                    answer.add(t.split(UnSeparator));
                }
                return answer;
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Address KakaoKeywordSearch(String query){
        StrictMode.ThreadPolicy strictMode = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(strictMode);
        Address address = null;
        try {
            KakaoApi kakaoApi = new KakaoApi(query);
            kakaoApi.start();
            kakaoApi.join();
            address = new Address(kakaoApi.br.readLine(),query);
        }catch (Exception e){
            e.printStackTrace();
        }
        return address;
    }
}
