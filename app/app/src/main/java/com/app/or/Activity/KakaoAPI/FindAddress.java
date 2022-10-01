package com.app.or.Activity.KakaoAPI;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.or.Config.Universal;
import com.app.or.DTO.Address;
import com.app.or.R;
import com.app.or.Universal.HttpsHelper;


import net.daum.mf.map.api.MapCircle;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.io.BufferedReader;


/**
 * 리뷰 쓰는 곳
 */
public class FindAddress extends AppCompatActivity {

    MapView mapView;

    EditText AddressText;

    Button AddressFind;
    Button AddressSubmit;

    Context context;
    Address address;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kakaoapi_findaddress);
        context  = getApplicationContext();


        AddressText = findViewById(R.id.AddressText);
        AddressFind = findViewById(R.id.AddressFind);
        AddressSubmit = findViewById(R.id.AddressSubmit);
        mapView = new MapView(this);

        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);
        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(37.86920, 127.74479),4, true);
        mapView.zoomIn(true);
        mapView.zoomOut(true);

        AddressFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(AddressText.getText().toString().length() == 0){
                    Toast.makeText(context,"검색어를 입력해주세요",Toast.LENGTH_SHORT).show();
                }else {
                    address =  Universal.httpsHelper.KakaoKeywordSearch(AddressText.getText().toString());
                    if(address.isNull()){
                        Toast.makeText(context,"유효한 주소가 없습니다.",Toast.LENGTH_SHORT).show();
                    }else {
                        MapPoint MARKER_POINT1 = MapPoint.mapPointWithGeoCoord(address.getY(), address.getX());
                        MapPOIItem marker = new MapPOIItem();
                        marker.setItemName("검색한 주소");
                        marker.setMapPoint(MARKER_POINT1);
                        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.

                        mapView.addPOIItem(marker);
                        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(address.getY(), address.getX()),0, true);
                    }
                }
            }
        });

        AddressSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent retIntent = new Intent();
                retIntent.putExtra("address",address);
                if(address.isNull()){
                    setResult(RESULT_CANCELED);
                }else{
                    setResult(RESULT_OK,retIntent);
                }
                finish();
            }
        });
    }
}
