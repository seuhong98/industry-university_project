package com.app.or.Activity.Main;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.app.or.Activity.Review.DetailActivity;
import com.app.or.Activity.Review.ReviewWriteActivity;
import com.app.or.Config.Universal;
import com.app.or.DTO.SimpleReview;
import com.app.or.R;
import com.app.or.Universal.ListViewAdapter;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    TextView uni_name;

    Button main_menu;
    Button main_filter;
    Button main_map;
    Button main_write_bt;

    ListView myList ;
    ListViewAdapter adapter;

    String search_code = "-1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_main);
        uni_name = findViewById(R.id.uni_name);

        main_menu = findViewById(R.id.main_menu);
        main_filter = findViewById(R.id.main_filter);
        main_map = findViewById(R.id.main_map);
        main_write_bt = findViewById(R.id.main_write_bt);

        myList = findViewById(R.id.myList);

        List<SimpleReview> simpleReviews = Universal.NETWORK.RequestSimpleReview("Review/GetCurrentList",null);
        drawList(simpleReviews);

        uni_name.setText(Universal.memory.getUniName());

        main_write_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ReviewWriteActivity.class);
                startActivity(intent);
            }
        });

        main_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),FilterActivity.class);
                startActivityForResult(intent,500);
            }
        });

        main_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),StettingActivity.class);
                startActivity(intent);
            }
        });

        main_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MapActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if(search_code.equals("-1")){
            List<SimpleReview> simpleReviews = Universal.NETWORK.RequestSimpleReview("Review/GetCurrentList",null);
            drawList(simpleReviews);
        }else{
            List<String> p = new ArrayList<>();
            p.add(search_code);
            System.out.println(search_code);
            List<SimpleReview> simpleReviews = Universal.NETWORK.RequestSimpleReview("Review/GetFilterList",p);
            drawList(simpleReviews);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 500){
            search_code=data.getStringExtra("check");
        }
    }



    private void drawList(List<SimpleReview> simpleReviews){
        if(simpleReviews != null){
            adapter = new ListViewAdapter(simpleReviews);
            myList.setAdapter(adapter);

            myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                    intent.putExtra("id",l);
                    startActivity(intent);
                }
            });
        }
    }

}