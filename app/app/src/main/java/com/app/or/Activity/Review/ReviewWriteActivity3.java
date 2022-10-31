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
import com.app.or.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 리뷰 쓰는 곳
 */
public class ReviewWriteActivity3 extends AppCompatActivity {


    Address addressData;

    int price = -1;
    EditText Guarantee0;
    EditText money0;
    EditText management0;

    EditText Guarantee1;
    EditText management1;

    RadioButton is_monthlyR;
    RadioButton is_charterR;
    RadioButton is_dormitoryR;

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
        setContentView(R.layout.activity_review_review_write);

        try {
            loadImage = findViewById(R.id.review1LoadImage);
            editText = new EditText[12];
            imageView = new ImageView[11];
            title = findViewById(R.id.review1Title);
            save = findViewById(R.id.review1Save);
            address = findViewById(R.id.review1findAddress);
            is_charterR = findViewById(R.id.is_charterR);
            is_monthlyR = findViewById(R.id.is_monthlyR);
            is_dormitoryR = findViewById(R.id.is_dormitoryR);

            is_monthly = findViewById(R.id.is_monthly);
            is_charter = findViewById(R.id.is_charter);

            Guarantee0 = findViewById(R.id.Guarantee0);
            Guarantee1 = findViewById(R.id.Guarantee1);
            money0 = findViewById(R.id.money0);
            management0 = findViewById(R.id.management0);
            management1 = findViewById(R.id.management1);
            ratingBar = findViewById(R.id.review1Total);

            for(int i=0;i<=10;i++){
                editText[i] = (EditText)findViewById(textId[i]);
                editText[i].setTextSize(Universal.memory.getTextSizeDP());
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
                                List<String> params = new ArrayList<>();
                                params.add(title.getText().toString());
                                for(int i=0;i<=textVisibility;i++){
                                    if(editText[i].getText().toString().length() != 0){
                                        if(editText[i].getText().toString().length()>10){
                                            params.add(editText[i].getText().toString().substring(0,10));
                                        }else{
                                            params.add(editText[i].getText().toString());
                                        }
                                        break;
                                    }
                                }
                                if(imageVisibility == -1){
                                    params.add("0");
                                    params.add("null");
                                }else {
                                    params.add("1");
                                    params.add(Universal.imageHelper.Thumbnail(imgArray.get(0)));
                                }
                                StringBuffer temp = new StringBuffer();
                                for(int i=0;i<=textVisibility;i++){
                                    temp.append(editText[i].getText().toString());
                                    if(imageVisibility>=i){
                                        temp.append(Universal.imageHelper.ImageToString(imgArray.get(i)));
                                    }
                                }
                                params.add(temp.toString());
                                if(is_dormitoryR.isActivated()){
                                    params.add("7");
                                }else {
                                    //Integer read =  Universal.memory.RegionToCode().get(addressData.getAddress1());
                                    //if(read != null){
                                    //    params.add(read+"");
                                    //}else{
                                    //    params.add("8");
                                    //}
                                }
                                params.add(addressData.getX()+"");
                                params.add(addressData.getY()+"");

                                if(is_monthlyR.isActivated()){
                                    params.add("0");
                                    params.add(Integer.parseInt(Guarantee0.getText().toString())+"");
                                    params.add(Integer.parseInt(money0.getText().toString())+"");
                                    params.add(Integer.parseInt(management0.getText().toString())+"");
                                }else if(is_charterR.isActivated()){
                                    params.add("1");
                                    params.add(Integer.parseInt(Guarantee1.getText().toString())+"");
                                    params.add("null");
                                    params.add(Integer.parseInt(management1.getText().toString())+"");
                                }else {
                                    params.add("2");
                                    params.add("null");
                                    params.add("null");
                                    params.add("null");
                                }
                                params.add(ratingBar.getRating()+"");

                                Universal.NETWORK.Request("Review/saveReview",params);
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
        }catch (Exception e){}



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
