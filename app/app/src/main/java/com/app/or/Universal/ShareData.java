package com.app.or.Universal;

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
import java.util.StringTokenizer;

public class ShareData {

    public StringTokenizer GetEmailAndPassword(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Universal.abbr.getFiledir()+"AccountData.txt"));
            return new StringTokenizer(Universal.security.decryption(reader.readLine()));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public void SaveEmailAndPassword(String Email,String Password){
        File file = new File(Universal.abbr.getFiledir() +"AccountData.txt" );
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


    public boolean isSaveAccount(){
        File file = new File(Universal.abbr.getFiledir(), "AccountData.txt");
        return file.exists();
    }

}
