package com.app.or.Activity.Review;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.Toast;

import com.app.or.Activity.KakaoAPI.FindAddress;
import com.app.or.Config.Universal;
import com.app.or.DTO.Address;
import com.app.or.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 리뷰 쓰는 곳
 */
public class ReviewWriteActivity extends AppCompatActivity {

    TextInputEditText review_title;
    TextInputEditText review_all_money;
    TextInputEditText review_month_money;

    RadioButton month;
    RadioButton year;
    RadioButton school;

    Button backward_review;
    Button findMap;
    Button findPhoto;

    EditText Text;
    ImageView ImageArray[];

    LinearLayout review_money_box;

    ScrollView scroll;

    Address addressData;

    Bitmap[] SaveImage = new Bitmap[5];

    int image_focus = -1;

    int[] imageId = {
            R.id.review_image_1 ,	R.id.review_image_2 ,	R.id.review_image_3 ,	R.id.review_image_4 ,	R.id.review_image_5
    };

    int count=0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_review_write);

        review_title = (TextInputEditText) findViewById(R.id.review_title);
        review_all_money = (TextInputEditText) findViewById(R.id.review_all_money);
        review_month_money = (TextInputEditText) findViewById(R.id.review_month_money);

        month = (RadioButton)findViewById(R.id.month);
        year = (RadioButton)findViewById(R.id.year);
        school = (RadioButton)findViewById(R.id.school);

        backward_review = (Button)findViewById(R.id.backward_review);
        findMap = (Button)findViewById(R.id.findMap);
        findPhoto = (Button)findViewById(R.id.findPhoto);

        Text = (EditText) findViewById(R.id.review_txt);
        for(int i=0;i<5;i++){
            ImageArray[i] = (ImageView) findViewById(imageId[i]);
        }

        review_money_box = (LinearLayout)findViewById(R.id.review_money_box);

        scroll = (ScrollView) findViewById(R.id.scroll);
        //====맵핑 완료 ====//

        month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                review_money_box.setVisibility(View.VISIBLE);
                review_all_money.setHint("보증금(만원)");
            }
        });

        year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                review_money_box.setVisibility(View.VISIBLE);
                review_all_money.setHint("전세금(만원)");
            }
        });

        school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                review_money_box.setVisibility(View.INVISIBLE);
            }
        });


        findMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goAddress = new Intent(getApplicationContext(), FindAddress.class);
                startActivityForResult(goAddress,200);
            }
        });

        findPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 100);
            }
        });

        scroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Text.requestFocus();
            }
        });

    }




    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 100){
            if(resultCode == RESULT_OK){
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();

                    image_focus++;
                    ImageArray[image_focus].setImageBitmap(img);
                    SaveImage[image_focus] = img;
                }catch (Exception e){
                    Toast.makeText(this,"이미지 불러오기에 실패 했습니다.",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                };
            }
        }

        if(requestCode == 200){
            if(resultCode == RESULT_OK){
                addressData = (Address)data.getParcelableExtra("address");
                System.out.println("x : "+addressData.getX()+"  y : "+addressData.getY());
            }else{
                Toast.makeText(this,"위치 저장에 실패 했습니다.",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
