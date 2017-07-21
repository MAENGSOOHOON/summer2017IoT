package appddi.ma_project;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Map extends AppCompatActivity {

    GoogleMap map;
    int number;
    MyLocationListener listener;
    SensorManager sensorManager;
    MySensorListener sensorListener;
    LocationManager manager;
    double latitude_my, longitude_my;
    String title, phone,time2,people,address,time;
    String menu2,payment,imagePath;
    DatabaseUpload temp;
    ArrayList<Item> qq,qq2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map2);
        map = fragment.getMap();
        map.getUiSettings().setRotateGesturesEnabled(true);
        map.getUiSettings().setScrollGesturesEnabled(true);
        map.getUiSettings().setTiltGesturesEnabled(true);
        map.getUiSettings().setZoomGesturesEnabled(true);
        map.getUiSettings().setAllGesturesEnabled(true);




        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);



        // manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        listener = new MyLocationListener();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorListener = new MySensorListener();

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        phone = intent.getStringExtra("phone");
        latitude_my = intent.getDoubleExtra("latitude", 0.1);
        longitude_my = intent.getDoubleExtra("longitude", 0.1);
        imagePath = intent.getStringExtra("imagePath");
        menu2 = intent.getStringExtra("menu");
        payment = intent.getStringExtra("payment");
        time2 = intent.getStringExtra("time2");
        people = intent.getStringExtra("people");
        address= intent.getStringExtra("address");
        time= intent.getStringExtra("time");

        number = intent.getIntExtra("number", 111);  //카테고리 정보, 디폴트 111

        if(number != 111&&number!=999&&number!=998&&number!=997&&number!=996) {     // 전체 리스트 식당 지도에서 올경우
            temp = new DatabaseUpload();
            qq = temp.getM_arr();                                                   // 식당  리스트  맵을 불러옴

            qq2 = new ArrayList<Item>();            // 카테고리 리스트

            for (int i = 0; i < qq.size(); i++) {
                if (qq.get(i).getCategory() == number)
                    qq2.add(qq.get(i));
            }
            for(int i=0; i<qq2.size(); i++)// 마커 생성
                showCurrnetMap(qq2.get(i).getLatitude(),qq2.get(i).getLongitude(),i);
        }else if(number==998){      //동문
            temp = new DatabaseUpload();
            qq = temp.getM_arr();
            for(int i=0; i<qq.size(); i++){
                if(qq.get(i).getLongitude()>128.492011)
                    showCurrnetMap(qq.get(i).getLatitude(),qq.get(i).getLongitude(),i);
            }
        }
        else if(number == 999){                                                     //전체 식당 맵을 불러옴
            temp = new DatabaseUpload();
            qq = temp.getM_arr();

            for(int i=0; i<qq.size(); i++)// 마커 생성
                showCurrnetMap(qq.get(i).getLatitude(),qq.get(i).getLongitude(),i);
        }
        else if(number == 997){                                                     //전체 식당 맵을 불러옴
            temp = new DatabaseUpload();
            qq = temp.getM_arr();

            for(int i=0; i<qq.size(); i++) {    //정문
                if(qq.get(i).getLatitude()<35.852427&&qq.get(i).getLongitude()>128.47988)
                    showCurrnetMap(qq.get(i).getLatitude(), qq.get(i).getLongitude(), i);
            }
        }
        else if(number == 996){                                                     //전체 식당 맵을 불러옴
            temp = new DatabaseUpload();
            qq = temp.getM_arr();

            for(int i=0; i<qq.size(); i++) {
                if(qq.get(i).getLongitude()<128.47988)
                    showCurrnetMap(qq.get(i).getLatitude(), qq.get(i).getLongitude(), i);
            }
        }
        else {                                                                       // 식당 하나만 지도보기
            showCurrnetMap(latitude_my, longitude_my, number);
            //manager.requestLocationUpdates();
        }
        if(number!=111&&number!=999&&number!=998&&number!=997&&number!=996) {                                      //리스트 하나만 할떄  카메라 시작 위치
            double a=0,b=0;

            for(int i=0; i < qq2.size() ; i++){
                a=a+qq2.get(i).getLatitude();
                b=b+qq2.get(i).getLongitude();
            }
            a = a/qq2.size();
            b = b/qq2.size();

            map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(a, b), 14));


        }else if(number==998){
            double a=0,b=0;
            int num=0;
            for(int i=0; i < qq.size() ; i++){
                if(qq.get(i).getLongitude()>128.492011){
                    a=a+qq.get(i).getLatitude();
                    b=b+qq.get(i).getLongitude();
                    num++;
                }
            }

            a = a/num;
            b = b/num;

            map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(a, b), 17));

        }
        else if(number ==999){                                              // 전체 리스트  카메라 시작 위치
            double a=0,b=0;

            for(int i=0; i < qq.size() ; i++){
                a=a+qq.get(i).getLatitude();
                b=b+qq.get(i).getLongitude();
            }
            a = a/qq.size();
            b = b/qq.size();

            map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(a, b), 14));
        }else if(number==997){
            double a=0,b=0;
            int num=0;
            for(int i=0; i < qq.size() ; i++){
                if(qq.get(i).getLatitude()<35.852427&&qq.get(i).getLongitude()>128.47988){
                    a=a+qq.get(i).getLatitude();
                    b=b+qq.get(i).getLongitude();
                    num++;
                }
            }

            a = a/num;
            b = b/num;

            map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(a, b), 16));

        }
        else if(number==996){
            double a=0,b=0;
            int num=0;
            for(int i=0; i < qq.size() ; i++){
                if(qq.get(i).getLongitude()<128.47988){
                    a=a+qq.get(i).getLatitude();
                    b=b+qq.get(i).getLongitude();
                    num++;
                }
            }

            a = a/num;
            b = b/num;

            map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(a, b), 16));

        }
        else map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng (latitude_my,longitude_my),15 ));   //식당 하나일떄 카메라 시작 위치


        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {                                 //  마커 클릭시 나오는 설명
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                final View v = getLayoutInflater().inflate(R.layout.map_item, null);

                final Marker test = marker;
                String temp;
                String intentString[];

                ImageView imView = (ImageView) v.findViewById(R.id.vi_image);

                TextView title = (TextView) v.findViewById(R.id.vi_title);

                TextView text_02 = (TextView) v.findViewById(R.id.vi_text);

                temp = marker.getTitle();
                intentString = temp.split("<");

                String[] imagePath2 = intentString[4].split("\n");

                imView.setImageBitmap(DatabaseUpload.GetImageFromURL(imagePath2[0]));
                title.setText(intentString[0]);
                text_02.setText(intentString[1]);

                return v;
            }


        });
        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                String[] info_Restaurant;
                info_Restaurant = new String[9];

                for(int i = 0 ; i < info_Restaurant.length; i++)
                    info_Restaurant[i] = "";



                Intent intent2 = new Intent(Map.this, Information_Activity.class);
                String temp;
                String intentString[];
                temp = marker.getTitle();
                intentString = temp.split("<");

                for(int i = 0 ; i< intentString.length; i++)
                    info_Restaurant[i] = intentString[i] ;

                intent2.putExtra("title", info_Restaurant[0]);
                intent2.putExtra("time", info_Restaurant[1]);
                intent2.putExtra("phone", info_Restaurant[2]);
                intent2.putExtra("address", info_Restaurant[3]);
                intent2.putExtra("imagePath", info_Restaurant[4]);
                intent2.putExtra("people", info_Restaurant[5]);
                intent2.putExtra("time2", info_Restaurant[6]);
                intent2.putExtra("menu", info_Restaurant[7]);
                intent2.putExtra("payment", info_Restaurant[8]);
                Map.this.startActivity(intent2);
            }
        });

    }

    @Override
    protected void onPause() {

        super.onPause();//화면이 보여지지 않는 시점?
/*
        map.setMyLocationEnabled(false);//깜빡깜빡

        if(manager != null){
            manager.removeUpdates(listener);
        }
        sensorManager.unregisterListener(sensorListener);
*/
    }

    @Override
    protected void onResume() {
        super.onResume();   //onResume()=>화면이 보일 시점에 호출이 됨??

        // map.setMyLocationEnabled(true);//내 위치 깜빡깜빡//알아서 확인하는게 셋마이 로케이션//줄 그어져 있는건 '권장하지 않는다.'

        // sensorManager.registerListener(sensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_UI);
    }


    private  void showCurrnetMap(Double latitude, Double longitude, int number2){
        final LatLng curPoint = new LatLng(latitude, longitude);
        final int number3 = number2;

        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);


        if(latitude_my !=0.1) { //식당 하나
            MarkerOptions marker1 = new MarkerOptions();
            marker1.position(curPoint);
            marker1.title(title+"<"+time+"<"+phone+"<"+address+"<"+imagePath+"<"+people+"<"+time2+"<"+menu2+"<"+payment);
            marker1.draggable(false);

            //marker1.snippet(phone);
            //marker1.draggable(true);

            //marker1.icon(BitmapDescriptorFactory.fromResource(R.drawable.bab));
            map.addMarker(marker1);

        }
        else if(number==999||number==998||number==997||number==996) {// 여러개일떄
            MarkerOptions marker1 = new MarkerOptions();
            marker1.position(curPoint);

            marker1.title(qq.get(number2).getTitle() + "<" + qq.get(number2).getTime() + "<" + qq.get(number2).getPhone() + "<" + qq.get(number2).getAddress() +"<"+ qq.get(number2).getImagePath()+"<"+qq.get(number2).getPeopel()+"<"+qq.get(number2).getTime2()+"<"+qq.get(number2).getMenu()+"<"+qq.get(number2).getPayment());
            //     marker1.snippet(qq.get(number2).getPhone());
            marker1.draggable(true);
            setIcon(marker1, qq.get(number2).getCategory());
            map.addMarker(marker1);

        }else{      //리스트 맵
            MarkerOptions marker1 = new MarkerOptions();
            marker1.position(curPoint);
            String aa = qq.get(number2).getPhone();
            marker1.title(qq2.get(number2).getTitle() + "<" + qq2.get(number2).getTime() + "<" + qq2.get(number2).getPhone() + "<" + qq2.get(number2).getAddress() +"<"+ qq2.get(number2).getImagePath()+"<"+qq2.get(number2).getPeopel()+"<"+qq2.get(number2).getTime2()+"<"+qq2.get(number2).getMenu()+"<"+qq2.get(number2).getPayment());
            //   marker1.snippet(qq2.get(number2).getPhone());
            marker1.draggable(true);

            setIcon(marker1, qq2.get(number2).getCategory());
            map.addMarker(marker1);}


        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Intent intent = new Intent(Map.this, Information_Activity.class);
                //putExtra 로 선택한 아이템의 정보를 인텐트로 넘겨 줄 수 있다.


            }
        });

        //아이콘 넣기 끝
    }

    public void setIcon(MarkerOptions marker,int category){
        switch (category/10){
            case 1: marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.bab333333));
                break;
            case 2: marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.meat3333333));
                break;
            case 3:marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.noodle3333333));
                break;
            case 4: marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.comma333333));
        }

    }

    class MySensorListener implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent event) {//센서의 값이 바뀔때마다 확인할수 있는 값
            Log.d("MySensorListener", "sensor \0 :" + event.values[0]);

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }

    class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {

            Double latitude = location.getLatitude(); //위도
            Double longitude = location.getLongitude();

            Log.d("MainActivity", "내 위치 :" + latitude + "," + longitude);

            showCurrnetMap(latitude, longitude,number);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
}