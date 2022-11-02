package com.app.or.Activity.Review;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.or.Config.Universal;
import com.app.or.DTO.Review;
import com.app.or.R;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {


    Button backward_signin;
    Button detail_report;
    Button detail_good;

    LinearLayout what_type;

    TextView detail_title;
    TextView detail_where;
    TextView detail_year;
    TextView detail_month;
    TextView detail_main;
    TextView nickname;

    ImageView[] image = new ImageView[5];

    Review review;

    int[] imageId = {
            R.id.detail_image_1 ,	R.id.detail_image_2 ,	R.id.detail_image_3 ,	R.id.detail_image_4 ,	R.id.detail_image_5
    };

    MapView mapView;

    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_detail);
        Intent getId = getIntent();
        id = getId.getLongExtra("id",0);
        backward_signin = findViewById(R.id.backward_signin);
        detail_report = findViewById(R.id.detail_report);
        detail_good = findViewById(R.id.detail_good);

        what_type = findViewById(R.id.what_type);

        detail_title = findViewById(R.id.detail_title);
        detail_where = findViewById(R.id.detail_where);
        detail_year = findViewById(R.id.detail_year);
        detail_month = findViewById(R.id.detail_month);
        detail_main = findViewById(R.id.detail_main);
        nickname = findViewById(R.id.nickname);


        mapView = new MapView(this);

        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.detail_map);
        mapViewContainer.addView(mapView);






        for(int i=0;i<5;i++){
            image[i] = findViewById(imageId[i]);
        }

        List<String> params = new ArrayList<>();
        params.add(id+"");
        review = Universal.NETWORK.RequestReview("Review/getReview",params);

        detail_title.setText(review.getTitle());
        detail_where.setText(review.getInput_address() + "   "+review.getCreate_day());
        nickname.setText(review.getNickname());

        MapPoint MARKER_POINT1 = MapPoint.mapPointWithGeoCoord(review.getY(), review.getX());
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("위치");
        marker.setMapPoint(MARKER_POINT1);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        mapView.addPOIItem(marker);
        if(review.getAddress() == 7){
            mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(review.getY(), review.getX()),4, true);
        }else{
            mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(review.getY(), review.getX()),3, true);
        }
        mapView.zoomIn(true);
        mapView.zoomOut(true);

        if(review.getReview_type() == 0){
            what_type.setBackground(getDrawable(R.drawable.detail_month));
            detail_year.setText("보증금 : "+review.getGuarantee()+"만원");
            detail_year.setTextSize(18);
            detail_month.setText("월세(관리비 포함) : "+review.getMoney()+"만원");
            detail_month.setTextSize(18);
        }else if(review.getReview_type() == 1){
            what_type.setBackground(getDrawable(R.drawable.detail_year));
            detail_year.setText("보증금 : "+review.getGuarantee()+"만원");
            detail_year.setTextSize(18);
            detail_month.setText("월세(관리비 포함) : "+review.getMoney()+"만원");
            detail_month.setTextSize(18);
        }else{
            what_type.setBackground(getDrawable(R.drawable.detail_school));
        }


        detail_main.setText(review.getMain());
        detail_main.setTextSize(20);
        int i=0;
        System.out.println(review.getImage_txt().size());
        for(Bitmap bt : review.getImage_txt()){
            image[i].setImageBitmap(bt);
            image[i].setVisibility(View.VISIBLE);
            i++;
        }

        detail_good.setText("좋아요  "+review.getGood());


        if (review.getOwn()){
            detail_report.setBackground(getDrawable(R.drawable.detail_delete));
        }



        backward_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        detail_good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> t = new ArrayList<>();
                t.add(id+"");
                String[] get = Universal.NETWORK.Request("Review/good",t);
                if(get[0].equals("True")){
                    detail_good.setText("좋아요  "+(review.getGood()+1));
                }else{
                    Toast.makeText(getApplicationContext(),"이미 누르셨습니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        detail_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(review.getOwn()){
                    List<String> t = new ArrayList<>();
                    t.add(id+"");
                    String[] get =Universal.NETWORK.Request("Review/delete",t);
                    finish();
                }else{
                    List<String> t = new ArrayList<>();
                    t.add(id+"");
                    String[] get =Universal.NETWORK.Request("Review/bad",t);
                    if(get[0].equals("True")){
                        Toast.makeText(getApplicationContext(),"신고 했습니다.",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"이미 신고 했습니다.",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}
