package com.termproject.route.route;

import android.content.Context;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SharingFragment extends Fragment implements OnMapReadyCallback
{
    private MapView mapView = null;

    //LocationManager mLM = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


    public SharingFragment()
    {

    }
  /*  @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sharing,container,false);

        mapView = (MapView)rootView.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);

        return rootView;
    }
    @Override
    public void onStart(){
        super.onStart();
        mapView.onStart();
    }

    private void init() {

        GooglePlayServicesUtil.isGooglePlayServicesAvailable(CurrentMapActivity.this);
        mGoogleMap = ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map)).getMap();

        // 맵의 이동
        //mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15));

        GpsInfo gps = new GpsInfo(CurrentMapActivity.this);
        // GPS 사용유무 가져오기
        if (gps.isGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            // Creating a LatLng object for the current location
            LatLng latLng = new LatLng(latitude, longitude);

            // Showing the current location in Google Map
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

            // Map 을 zoom 합니다.
            mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(13));

            // 마커 설정.
            MarkerOptions optFirst = new MarkerOptions();
            optFirst.position(latLng);// 위도 • 경도
            optFirst.title("Current Position");// 제목 미리보기
            optFirst.snippet("Snippet");
            optFirst.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher));
            mGoogleMap.addMarker(optFirst).showInfoWindow();
        }
    }



    @Override
    public void onStop(){
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
    @Override
    public void onResume(){
        super.onResume();
        mapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onLowMemory();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //액티비티가 처음 생성될 때 실행되는 함수
        if(mapView != null)
        {
            mapView.onCreate(savedInstanceState);
        }
    }

    public class MapsMarkerActivity extends AppCompatActivity
            implements OnMapReadyCallback {
        // Include the OnCreate() method here too, as described above.
        @Override
        public void onMapReady(GoogleMap googleMap) {
            // Add a marker in Sydney, Australia,
            // and move the map's camera to the same location.
            LatLng sydney = new LatLng(-33.852, 151.211);
            googleMap.addMarker(new MarkerOptions().position(sydney)
                    .title("Marker in Sydney"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // 현재 위도와 경도에서 화면 포인트를 알려준다
        Point screenPt = mGoogleMap.getProjection().toScreenLocation(point);

        // 현재 화면에 찍힌 포인트로 부터 위도와 경도를 알려준다.
        LatLng latLng = mGoogleMap.getProjection().fromScreenLocation(screenPt);

        Log.d("맵좌표", "좌표: 위도(" + String.valueOf(point.latitude) + "), 경도("
                + String.valueOf(point.longitude) + ")");
        Log.d("화면좌표", "화면좌표: X(" + String.valueOf(screenPt.x) + "), Y("
                + String.valueOf(screenPt.y) + ")");

}
