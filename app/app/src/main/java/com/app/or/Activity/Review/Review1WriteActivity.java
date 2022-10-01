package com.app.or.Activity.Review;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Toast;

import com.app.or.Activity.KakaoAPI.FindAddress;
import com.app.or.Config.Universal;
import com.app.or.DTO.Address;
import com.app.or.DTO.MakeReview1;
import com.app.or.R;
import com.app.or.Universal.ReviewHelper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 리뷰 쓰는 곳
 */
public class Review1WriteActivity extends AppCompatActivity {


    Address addressData;

    int price = -1;
    EditText Guarantee0;
    EditText money0;

    EditText Guarantee1;
    EditText management1;

    RadioButton is_monthlyR;
    RadioButton is_charterR;

    LinearLayout is_monthly;
    LinearLayout is_charter;

    EditText title;
    EditText editText[];
    ImageView imageView[];
    Button loadImage;
    Button save;
    Button address;
    String inputAddress="";

    List<Bitmap> imgArray = new ArrayList<>(11);

    int[] textId = {
            R.id.review1Text0 , R.id.review1Text1 , R.id.review1Text2 , R.id.review1Text3 , R.id.review1Text4 , R.id.review1Text5 , R.id.review1Text6 , R.id.review1Text7 , R.id.review1Text8 , R.id.review1Text9 , R.id.review1Text10 , R.id.review1Text11
    };
    int[] imageId = {
            R.id.review1Image0 ,	R.id.review1Image1 ,	R.id.review1Image2 ,	R.id.review1Image3 ,	R.id.review1Image4 ,	R.id.review1Image5 ,	R.id.review1Image6 ,	R.id.review1Image7 ,	R.id.review1Image8 ,	R.id.review1Image9 ,	R.id.review1Image10
    };

    int textVisibility =0;
    int imageVisibility =-1;

    RatingBar ratingBar;


    //todo 글 지울 때 다 지우면 위 사진도 지워지도록 하기
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_review1write);


        loadImage = findViewById(R.id.review1LoadImage);
        editText = new EditText[12];
        imageView = new ImageView[11];
        title = findViewById(R.id.review1Title);
        save = findViewById(R.id.review1Save);
        address = findViewById(R.id.review1findAddress);
        is_charterR = findViewById(R.id.is_charterR);
        is_monthlyR = findViewById(R.id.is_monthlyR);

        is_monthly = findViewById(R.id.is_monthly);
        is_charter = findViewById(R.id.is_charter);

        Guarantee0 = findViewById(R.id.Guarantee0);
        Guarantee1 = findViewById(R.id.Guarantee1);
        money0 = findViewById(R.id.money0);
        management1 = findViewById(R.id.management1);
        ratingBar = findViewById(R.id.review1Total);


        for(int i=0;i<=10;i++){
            editText[i] = (EditText)findViewById(textId[i]);
            editText[i].setTextSize(Universal.abbr.getTextSizeDP());
            imageView[i] = (ImageView)findViewById(imageId[i]);
        }
        editText[11] = (EditText)findViewById(textId[11]);


        is_monthlyR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                is_charter.setVisibility(View.GONE);
                is_monthly.setVisibility(View.VISIBLE);
                price = 0;
            }
        });

        is_charterR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                is_charter.setVisibility(View.VISIBLE);
                is_monthly.setVisibility(View.GONE);
                price  = 1;
            }
        });




        loadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textVisibility == 0 && editText[0].getText().length() == 0){
                    editText[0].setVisibility(View.GONE);
                }
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 100);

            }
        });
        //todo 자연스럽게 지워지고 하는 부분 추가 해야함


        /**
         * 위치 저장하기
         */
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goAddress = new Intent(getApplicationContext(), FindAddress.class);
                startActivityForResult(goAddress,200);
            }
        });

        /**
         * 저장하는 부분
         */
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(title.getText().length()>0)){
                    //todo 제목을 입력하세요 팝업
                    return;
                }
                if(addressData == null  || addressData.isNull()){
                    //todo 주소를 입력하세요 팝업
                    return;
                }
                boolean is_ok= false;

                for(int i=0;i<12;i++){
                    if(editText[i].getText().length() > 4){
                        is_ok = true;
                        break;
                    }
                }
                if(is_ok){
                    try {
                        if(price == -1){
                            //todo 월세 전세금 설정 안했을 시
                        }else {
                            MakeReview1 makeReview1 = new MakeReview1();
                            makeReview1.setTitle(title.getText().toString());
                            String t[] = new String[textVisibility+1];
                            for(int i=0;i<=textVisibility;i++){
                                t[i] = editText[i].getText().toString();
                            }
                            makeReview1.setMain(t);
                            makeReview1.setImageAddress(imgArray.toArray(new Bitmap[imgArray.size()]));
                            makeReview1.setAddress1(addressData.getAddress1());
                            makeReview1.setAddress2(addressData.getAddress2());
                            makeReview1.setX(addressData.getX());
                            makeReview1.setY(addressData.getY());

                            makeReview1.setPrice(price);
                            makeReview1.setTotal(ratingBar.getRating());

                            if(price == 0){
                                makeReview1.setGuarantee(Integer.parseInt(Guarantee0.getText().toString()));
                                makeReview1.setMoney(Integer.parseInt(money0.getText().toString()));
                                makeReview1.setManagement(0);
                            }else{
                                makeReview1.setGuarantee(Integer.parseInt(Guarantee1.getText().toString()));
                                makeReview1.setMoney(0);
                                makeReview1.setManagement(Integer.parseInt(management1.getText().toString()));
                            }
                            ReviewHelper.makeReview(makeReview1);
                            finish();
                        }
                    }catch (Exception e){
                        //todo 화면 변환 오류면 알려주고 화면 변환
                        e.printStackTrace();
                    }
                }else{
                    //todo 내용을 5자 이상 입력하세요 알람
                    return;
                }
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

                    imageVisibility++;
                    textVisibility++;
                    imageView[imageVisibility].setVisibility(View.VISIBLE);
                    imageView[imageVisibility].setImageBitmap(img);
                    imgArray.add(img);
                    editText[textVisibility].setVisibility(View.VISIBLE);
                    editText[textVisibility].requestFocus();
                }catch (Exception e){
                    e.printStackTrace();
                    //todo 오류 작업 해줘야함
                };
            }else{
                //todo 갤러리 결과 오류 출력 해야함
            }
        }

        if(requestCode == 200){
            if(resultCode == RESULT_OK){
                addressData = (Address)data.getParcelableExtra("address");
                System.out.println("x : "+addressData.getX()+"  y : "+addressData.getY());
                Toast.makeText(this,"위치 저장 성공",Toast.LENGTH_SHORT);
            }else{
                Toast.makeText(this,"위치 저장 실패",Toast.LENGTH_SHORT);
            }
        }
    }
}
