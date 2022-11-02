package com.app.or.Activity.Main;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.or.Config.Universal;
import com.app.or.DTO.Address;
import com.app.or.R;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;
import java.util.List;


/**
 * 리뷰 쓰는 곳
 */
public class MapActivity extends AppCompatActivity  implements MapView.MapViewEventListener, MapView.POIItemEventListener, View.OnClickListener {

    MapView mapView;


    Button backward_review;
    Context context;
    ViewGroup mapViewContainer;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);
        context  = getApplicationContext();

        backward_review = findViewById(R.id.backward_review);
        mapView = new MapView(this);
        mapView.setPOIItemEventListener(this);

        mapViewContainer = (ViewGroup) findViewById(R.id.map_view);

        mapViewContainer.addView(mapView);

        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(Universal.memory.getUni_y(), Universal.memory.getUni_x()),4, true);


        String read[] = Universal.NETWORK.Request("Review/getXY",null);
        MapPOIItem[] markerArr = new MapPOIItem[read.length];
        int i=0;
        for(String t : read){
            String tt[] = t.split(" ");
            MapPOIItem marker = new MapPOIItem();
            marker.setMapPoint(MapPoint.mapPointWithGeoCoord(Double.parseDouble(tt[1]), Double.parseDouble(tt[0])));
            marker.setItemName("선택");
            markerArr[i] = marker;
            marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
            i++;
        }
        mapView.addPOIItems(markerArr);

        mapView.zoomIn(true);
        mapView.zoomOut(true);





        backward_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }



    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {
        MapPoint mapPoint = mapPOIItem.getMapPoint();
        Intent intent = new Intent(getApplicationContext(),ShowMapActivity.class);
        intent.putExtra("xy",mapPoint.getMapPointGeoCoord().longitude+" "+mapPoint.getMapPointGeoCoord().latitude);
        startActivity(intent);
    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onMapViewInitialized(MapView mapView) {

    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

    }
}
