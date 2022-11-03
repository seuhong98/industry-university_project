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
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
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
import com.app.or.Activity.Login.SingUp;
import com.app.or.Config.Universal;
import com.app.or.DTO.Address;
import com.app.or.DTO.Review;
import com.app.or.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 리뷰 쓰는 곳
 */
public class ReviewWriteActivity extends AppCompatActivity {

    TextInputEditText review_title;
    TextInputEditText review_all_money;
    TextInputEditText review_month_money;
    TextInputEditText review_all_money2;
    TextInputEditText review_month_money2;

    RadioButton month;
    RadioButton year;
    RadioButton school;

    Button backward_review;
    Button findMap;
    Button findPhoto;
    Button send_review;

    EditText Text;
    ImageView ImageArray[] = new ImageView[5];

    LinearLayout review_month_box;
    LinearLayout review_year_box;

    Address addressData;

    List<Bitmap> SaveImage = new ArrayList<>();



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
        review_all_money2 = (TextInputEditText) findViewById(R.id.review_all_money2);
        review_month_money2 = (TextInputEditText) findViewById(R.id.review_month_money2);

        month = (RadioButton)findViewById(R.id.month);
        year = (RadioButton)findViewById(R.id.year);
        school = (RadioButton)findViewById(R.id.school);

        backward_review = (Button)findViewById(R.id.backward_review);
        findMap = (Button)findViewById(R.id.findMap);
        findPhoto = (Button)findViewById(R.id.findPhoto);
        send_review = (Button)findViewById(R.id.send_review);

        Text = (EditText) findViewById(R.id.review_txt);
        for(int i=0;i<5;i++){
            ImageArray[i] = (ImageView) findViewById(imageId[i]);
        }

        review_month_box = (LinearLayout)findViewById(R.id.review_month_box);
        review_year_box = (LinearLayout) findViewById(R.id.review_year_box);

        //====맵핑 완료 ====//
        count = Universal.memory.getTextBox();

        backward_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                review_year_box.setVisibility(View.GONE);
                review_month_box.setVisibility(View.VISIBLE);
            }
        });

        year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                review_month_box.setVisibility(View.GONE);
                review_year_box.setVisibility(View.VISIBLE);
            }
        });

        school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                review_month_box.setVisibility(View.GONE);
                review_year_box.setVisibility(View.INVISIBLE);
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

        send_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(review_title.length() == 0){
                    Toast.makeText(getApplicationContext(),"제목을 입력해주세요",Toast.LENGTH_SHORT).show();
                }else if(month.isChecked()&&(review_all_money.length()==0||review_month_money.length()==0)){
                    Toast.makeText(getApplicationContext(),"보증금과 월세(+관리비) 모두 입력해주세요",Toast.LENGTH_SHORT).show();
                }else if(year.isChecked()&&(review_all_money2.length()==0||review_month_money2.length()==0)){
                    Toast.makeText(getApplicationContext(),"전세금과 관리비 모두 입력해주세요",Toast.LENGTH_SHORT).show();
                }else if(Text.length()<5){
                    Toast.makeText(getApplicationContext(),"5글자 이상 부탁 드립니다.",Toast.LENGTH_SHORT).show();
                }else if(!school.isChecked()&&(addressData == null || addressData.isNull())){
                    Toast.makeText(getApplicationContext(),"주소 선택으로 주소를 지정해주세요.",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getApplicationContext(), RatingActivity.class);
                    startActivityForResult(intent,300);
                }
            }
        });

        ImageArray[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveImage.remove(0);
                printPhoto();
            }
        });
        ImageArray[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveImage.remove(1);
                printPhoto();
            }
        });
        ImageArray[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveImage.remove(2);
                printPhoto();
            }
        });
        ImageArray[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveImage.remove(3);
                printPhoto();
            }
        });
        ImageArray[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveImage.remove(4);
                printPhoto();
            }
        });


    }

    private void printPhoto(){
        int i=0;
        for(Bitmap bitmap :SaveImage){
            ImageArray[i].setImageBitmap(bitmap);
            ImageArray[i].setVisibility(View.VISIBLE);
            i++;
        }
        for(;i<5;i++){
            ImageArray[i].setVisibility(View.GONE);
        }
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
                    SaveImage.add(img);
                    printPhoto();
                }catch (Exception e){
                    Toast.makeText(this,"이미지 불러오기에 실패 했습니다.",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                };
            }
        }

        if(requestCode == 200){
            if(resultCode == RESULT_OK){
                addressData = (Address)data.getParcelableExtra("address");
            }else{
                Toast.makeText(this,"위치 저장에 실패 했습니다.",Toast.LENGTH_SHORT).show();
            }
        }

        if(requestCode == 300){
            Review review = new Review();
            String read[] = data.getStringExtra("score").split(" ");
            int down=0;
            float total = 0;
            for(String t: read){
                if(!t.equals("null")){
                    down++;
                    total += Float.parseFloat(t);
                }
            }
            if(total != 0){
                total = total/down;
                review.setOwner_rating(total);
            }
            review.setSize(read[0].equals("null") ? null : Float.parseFloat(read[0]));
            review.setNoise(read[1].equals("null") ? null : Float.parseFloat(read[1]));
            review.setService(read[2].equals("null") ? null : Float.parseFloat(read[2]));
            review.setHygiene(read[3].equals("null") ? null : Float.parseFloat(read[3]));
            review.setSafety(read[4].equals("null") ? null : Float.parseFloat(read[4]));
            review.setTemperature(read[5].equals("null") ? null : Float.parseFloat(read[5]));

            if(month.isChecked()){
                review.setReview_type(0);
                review.setGuarantee(Integer.parseInt(review_all_money.getText().toString()));
                review.setMoney(Integer.parseInt(review_month_money.getText().toString()));
                review.setX(addressData.getX());
                review.setY(addressData.getY());
                review.setInput_address(addressData.getSearchTxt());
                if(Universal.memory.RegionToCode(addressData.getAddress1()) != null){
                    review.setAddress(Universal.memory.RegionToCode(addressData.getAddress1()));
                }else{
                    review.setAddress(999);
                }
            }else if(year.isChecked()){
                review.setReview_type(1);
                review.setGuarantee(Integer.parseInt(review_all_money2.getText().toString()));
                review.setMoney(Integer.parseInt(review_month_money2.getText().toString()));
                review.setX(addressData.getX());
                review.setY(addressData.getY());
                review.setInput_address(addressData.getSearchTxt());
                if(Universal.memory.RegionToCode(addressData.getAddress1()) != null){
                    review.setAddress(Universal.memory.RegionToCode(addressData.getAddress1()));
                }else{
                    review.setAddress(999);
                }
            }else{
                review.setReview_type(3);
                review.setY(Universal.memory.getUni_y());
                review.setX(Universal.memory.getUni_x());
                review.setInput_address("기숙사");
                review.setAddress(Universal.memory.RegionToCode("기숙사"));
            }

            review.setMain(Text.getText().toString());
            if(Text.getText().toString().split("\\n")[0].length()>10){
                review.setPreview((Text.getText().toString().split("\\n")[0]).substring(0,9)+"...");
            }else{
                review.setPreview(Text.getText().toString().split("\\n")[0]);
            }
            review.setTitle(review_title.getText().toString());
            review.setImage_txt(SaveImage);


            Universal.NETWORK.Request("Review/saveReview",Universal.dataMapper.ReviewSerialization(review));
            finish();

        }
    }
}
