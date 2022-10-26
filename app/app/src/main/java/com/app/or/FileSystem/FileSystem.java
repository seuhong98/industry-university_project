package com.app.or.FileSystem;

import android.content.Context;
import android.content.SharedPreferences;

import com.app.or.Config.Universal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class FileSystem {


    // 계정 관련
    public String[] GetAccount(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Universal.memory.getFileDir()+"AccountData.txt"));
            return (Universal.security.decryption(reader.readLine())).split(" ");
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public void SetAccount(String Email,String Password){
        File file = new File(Universal.memory.getFileDir() +"AccountData.txt" );
        if(file.exists()){
            file.delete();
        }
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write(Universal.security.encryption(Email+" "+Password));
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean isAccount(){
        File file = new File(Universal.memory.getFileDir(), "AccountData.txt");
        return file.exists();
    }

    //공유키 관련
    public String[] GetPublicKey(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Universal.memory.getFileDir()+"PublicKeyData.txt"));
            String read;
            List<String> a = new ArrayList<>();
            while ((read = reader.readLine())!=null){
                a.add(read);
            }
            return a.toArray(new String[0]);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public void SetPublicKey(String[] get){
        File file = new File(Universal.memory.getFileDir() +"PublicKeyData.txt" );
        if(file.exists()){
            file.delete();
        }
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fw);
            for(String t : get){
                writer.write(t+"\n");
            }
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean isPublicKey(){
        File file = new File(Universal.memory.getFileDir(), "PublicKeyData.txt");
        return file.exists();
    }
}
