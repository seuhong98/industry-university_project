package com.app.or.Activity.Review;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import com.app.or.R;

public class RatingActivity extends AppCompatActivity {

    RatingBar size;
    RatingBar noise;
    RatingBar service;
    RatingBar hygiene;
    RatingBar safety;
    RatingBar temperature;

    Button send_review_fin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_rating);
        size = (RatingBar)findViewById(R.id.size);
        noise = (RatingBar) findViewById(R.id.noise);
        service = (RatingBar) findViewById(R.id.service);
        hygiene = (RatingBar) findViewById(R.id.hygiene);
        safety = (RatingBar) findViewById(R.id.safety);
        temperature = (RatingBar) findViewById(R.id.temperature);
        send_review_fin = (Button) findViewById(R.id.send_review_fin);

        send_review_fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent retIntent = new Intent();
                StringBuffer sb = new StringBuffer();
                sb.append(size.getRating() !=0 ? size.getRating()+" " : "null ");
                sb.append(noise.getRating() !=0 ? noise.getRating()+" " : "null ");
                sb.append(service.getRating() !=0 ? service.getRating()+" " : "null ");
                sb.append(hygiene.getRating() !=0 ? hygiene.getRating()+" " : "null ");
                sb.append(safety.getRating() !=0 ? safety.getRating()+" " : "null ");
                sb.append(temperature.getRating() !=0 ? temperature.getRating()+" " : "null ");
                retIntent.putExtra("score",sb.toString());
                setResult(RESULT_OK,retIntent);
                finish();
            }
        });
    }
}
