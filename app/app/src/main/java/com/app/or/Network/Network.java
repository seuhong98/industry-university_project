package com.app.or.Network;

import android.os.StrictMode;

import com.app.or.Config.Universal;
import com.app.or.DTO.Address;
import com.app.or.FileSystem.FileSystem;

import java.util.ArrayList;
import java.util.List;

public class Network {

    long beforeTime;

    /**
     * 퍼블릭키 버전
     * @return 버전
     */
    public int PublicKeyVersion(){
        try {
            GetConnection getConnection = new GetConnection("Session/TouchPublicKey",null,false);
            return Integer.parseInt(getConnection.data[0]);
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
            Universal.fileSystem.SetPublicKey(getConnection.data);
        }catch (Exception e){

        }
    }

    public void MakeSession(){
        List<String> params = new ArrayList<>();
        params.add(Universal.security.GetSessionKeyByRSA());
        beforeTime = System.currentTimeMillis();
        GetConnection getConnection = new GetConnection("Session/MakeSession",params,false);
        String read[] = Universal.security.decryptionBySessionKey(getConnection.data[0]).split(getConnection.Separator);
        Universal.memory.setOrder(Integer.parseInt(read[1]));
        Universal.memory.setId(read[0]);
    }

    public String Login(String Email , String Pw){
        if(System.currentTimeMillis() - beforeTime > Universal.memory.getSessionTime()){
            MakeSession();
        }
        List<String> params = new ArrayList<>();
        params.add(Email);
        params.add(Pw);
        beforeTime = System.currentTimeMillis();
        GetConnection getConnection = new GetConnection("Login/SignIn",params,true);
        if(getConnection.answer){
            if(getConnection.data[0].equals("TRUE")){
                Universal.fileSystem.SetAccount(Email,Pw);
                return "TRUE";
            }else if(getConnection.data[0].equals("FALSE")){
                return "FALSE";
            }else if (getConnection.data[0].equals("TooManyWrong")){
                return "TooManyWrong";
            }else{
                return "err";
            }
        }else {
            return "err";
        }
    }

    public String AutoLogin(){
        String[] read =  Universal.fileSystem.GetAccount();
        return Login(read[0],read[1]);
    }

    public String[] Request(String Request, List<String> Params){
        if(System.currentTimeMillis() - beforeTime > Universal.memory.getSessionTime()){
            MakeSession();
            AutoLogin();
        }
        beforeTime = System.currentTimeMillis();
        GetConnection getConnection = new GetConnection(Request,Params,true);
        return getConnection.data;
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
