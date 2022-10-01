package com.app.or.Activity.Main;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.or.Activity.Review.Review1WriteActivity;
import com.app.or.R;

import java.security.MessageDigest;


public class MainActivity extends AppCompatActivity {


    Button TTEST;
    Button TTEST2;
    TextView testkey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_main);
        //todo 로그인 후 처음 보여지는 화면 작성 해야함
        TTEST = findViewById(R.id.TTEST);
        TTEST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Review1WriteActivity.class);
                startActivity(intent);
            }
        });


        TTEST2 = findViewById(R.id.TTEST2);
        testkey = findViewById(R.id.testkey);

        TTEST2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAppKeyHash_Debug();
            }
        });

    }

    //debug
    private void getAppKeyHash_Debug() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                testkey.setText(something);
            }
        } catch (Exception e) {
            Log.e("name not found", e.toString());
        }
    }
}