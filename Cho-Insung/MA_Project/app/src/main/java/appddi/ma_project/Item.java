package appddi.ma_project;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Cho on 2016-02-03.
 */
public class Item implements Serializable {    // 식당 아이템
    private String title,content,time,time2,address,phone,imagePath,peopel,menu,payment;
    private Bitmap firstImage;
    private  int category;
    private double latitude, longitude;

    public Item(){}

    public Item(String data,String content,int category,String time,String phone,String address,double latitude, double longitude,String imagePath,String people,String menu,String payment,String time2 ) {

        title = data;
        this.content = content;
        this.category = category;
        this.phone = phone;
        this.time = time;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imagePath =  imagePath;
        this.peopel = people;
        this.menu = menu;
        this.payment = payment;
        this.time2 = time2;
        this.firstImage =null;
    }
    public void setFirstImage(Bitmap image){firstImage = image;}
    public String getTitle(){
        return title;
    }
    public String getContent(){
        return content;
    }
    public Bitmap getFirstImage(){return firstImage;}
    public int getCategory(){return this.category;}
    public String getTime(){return this.time;}
    public String getAddress(){ return  address;}
    public String getPhone(){return phone; }
    public double getLatitude(){return latitude;}
    public double getLongitude(){return longitude;}
    public String getImagePath(){return imagePath;}
    public String getPeopel(){return peopel;}
    public  String getMenu(){return menu;}
    public  String getPayment(){return payment;}
    public String getTime2(){return time2;}






}
