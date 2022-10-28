package com.app.or.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;

import com.app.or.Activity.Login.Login;
import com.app.or.Activity.Main.MainActivity;
import com.app.or.Config.Universal;
import com.app.or.FileSystem.FileSystem;

public class Stater extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //퍼미션 받기
        checkPermission();

        //파일 저장을 위한 위치 경로 저장
        Universal.memory.setFileDir(getFilesDir()+"/");


        //글씨 크기 지정을 위한 행위
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        float density = getResources().getDisplayMetrics().density;
        Universal.memory.setTextSizeDP((int)((metrics.heightPixels/density)/30));

        //Universal 초기화
        Universal.UniversalInit();
        System.out.println(Universal.security.temp());
        Universal.NETWORK.MakeSession();



        //화면 지정 -로그인 유무
        Intent intent;
        if( Universal.fileSystem.isAccount() ){
            Universal.NETWORK.AutoLogin();
            intent = new Intent(getApplicationContext(), MainActivity.class);
        }else{
            intent = new Intent(getApplicationContext(), Login.class);
        }
        startActivity(intent);
    }

    /**
     * 저장 권한 얻기
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void checkPermission(){
        if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
        if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        if(checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
    }
}
