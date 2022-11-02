package com.app.or.Activity.Main;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.app.or.Activity.Review.DetailActivity;
import com.app.or.Config.Universal;
import com.app.or.DTO.SimpleReview;
import com.app.or.R;
import com.app.or.Universal.ListViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class ShowMapActivity extends AppCompatActivity {




    ListView myList ;
    ListViewAdapter adapter;

    Button backward_review;

    String code;

    List<String> param;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        code=  i.getStringExtra("xy");
        setContentView(R.layout.activity_main_mapshow);
        backward_review = findViewById(R.id.backward_review);
        myList = findViewById(R.id.myList);

        String[] t = code.split(" ");
        param = new ArrayList<>();
        param.add(t[0]);
        param.add(t[1]);

        List<SimpleReview> simpleReviews = Universal.NETWORK.RequestSimpleReview("Review/GetXYList",param);
        drawList(simpleReviews);

        backward_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        List<SimpleReview> simpleReviews = Universal.NETWORK.RequestSimpleReview("Review/GetXYList",param);
        drawList(simpleReviews);
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