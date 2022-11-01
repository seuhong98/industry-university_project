package com.app.or.Activity.Main;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.app.or.Activity.Review.ReviewWriteActivity;
import com.app.or.R;
import com.app.or.Universal.ListViewAdapter;

import java.security.MessageDigest;


public class MainActivity extends AppCompatActivity {


    TextView uni_name;

    Button main_menu;
    Button main_filter;
    Button main_map;
    Button main_write_bt;

    ListView myList ;
    ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_main);

        adapter = new ListViewAdapter();
        myList = findViewById(R.id.myList);
        myList.setAdapter(adapter);

    }
}