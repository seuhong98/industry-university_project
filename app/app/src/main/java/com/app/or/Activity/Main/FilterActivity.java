package com.app.or.Activity.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RatingBar;

import com.app.or.Config.Universal;
import com.app.or.R;

public class FilterActivity extends AppCompatActivity {

    RadioButton filter_apply;
    RadioButton filter_good;
    RadioButton filter_rating;

    CheckBox[] checkBoxes = new CheckBox[15];

    Button send_review_fin;

    int filter[] = {R.id.filter0,R.id.filter1,R.id.filter2,
            R.id.filter3,R.id.filter4,R.id.filter5,R.id.filter6,
            R.id.filter7,R.id.filter8,R.id.filter9,R.id.filter10,
            R.id.filter11,R.id.filter12,R.id.filter13,R.id.filter14
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_filter);

        filter_apply = findViewById(R.id.filter_apply);
        filter_good = findViewById(R.id.filter_good);
        filter_rating = findViewById(R.id.filter_rating);

        send_review_fin = findViewById(R.id.send_review_fin);

        for(int i=0;i<checkBoxes.length;i++){
            checkBoxes[i] = findViewById(filter[i]);
        }

        for(int i = 0;i<Universal.memory.UniAddressSize();i++){
            checkBoxes[i].setText(Universal.memory.CodeToRegion(i));
            checkBoxes[i].setVisibility(View.VISIBLE);
        }

        send_review_fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent retIntent = new Intent();
                StringBuffer sb = new StringBuffer();
                if(filter_good.isChecked()){
                    sb.append(1);
                }else if(filter_rating.isChecked()){
                    sb.append(2);
                }else{
                    sb.append(0);
                }
                for(int i = 0;i<Universal.memory.UniAddressSize();i++){
                    if(checkBoxes[i].isChecked()){
                        sb.append(1);
                    }else{
                        sb.append(0);
                    }
                }
                retIntent.putExtra("check",sb.toString());
                setResult(RESULT_OK,retIntent);
                finish();
            }
        });


    }
}
