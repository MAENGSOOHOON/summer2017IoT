package appddi.ma_project;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
/**
 * Created by a on 2016-02-04.
 */
public class DatabaseUpload extends AsyncTask<String, Integer,String> {
    static private ArrayList<Item> m_arr = new ArrayList<Item>();

    public ArrayList<Item> getM_arr(){ System.out.print(m_arr.size()); return m_arr;}
    @Override
    protected  synchronized String doInBackground(String... urls) {
        StringBuilder jsonHtml = new StringBuilder();
        try{
            // 연결 url 설정
            URL url = new URL(urls[0]);
            // 커넥션 객체 생성
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            // 연결되었으면.
            if(conn != null){
                conn.setConnectTimeout(20000);
                conn.setUseCaches(false);
                // 연결되었음 코드가 리턴되면.
                if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                    for(;;){
                        // 웹상에 보여지는 텍스트를 라인단위로 읽어 저장.
                        String line = br.readLine();
                        if(line == null) break;
                        // 저장된 텍스트 라인을 jsonHtml에 붙여넣음
                        jsonHtml.append(line + "\n");
                    }
                    br.close();
                }
                conn.disconnect();
            }
        } catch(Exception ex){
            System.out.print(ex.getLocalizedMessage());
        }
        return jsonHtml.toString();

    }

    protected  void onPostExecute(String str){
        String imgurl="";
        String txt1,txt2,time,phone,address, latitude, longitude,people,time2,menu_temp,payment_temp;
        String imagePath,menu,payment;

        int category;


        try{

            JSONObject root = new JSONObject(str);
            JSONArray ja = root.getJSONArray("results");

            if(m_arr.size() == ja.length()) return;

            for(int i=0; i<ja.length(); i++){
                JSONObject jo = ja.getJSONObject(i);
                imgurl = jo.getString("이미지 경로");
                txt1 = jo.getString("이름");
                txt2 = jo.getString("내용");
                category = Integer.parseInt(jo.getString("분류"));
                time = jo.getString("시간");
                phone = jo.getString("전화");
                address = jo.getString("주소");
                latitude = jo.getString("위도");
                longitude = jo.getString("경도");
                people = jo.getString("인원수");
                imagePath = imgurl;
                menu_temp = jo.getString("메뉴");
                menu = menu_temp;
                payment_temp = jo.getString("가격");
                payment = payment_temp;
                time2 = jo.getString("주말시간");
                m_arr.add(new Item(txt1,txt2,category,time,phone,address, Double.parseDouble(latitude), Double.parseDouble(longitude),imagePath,people,menu,payment,time2));

            }
            System.out.print(m_arr.size());
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public static synchronized Bitmap  GetImageFromURL(String strImageURL) {
        Bitmap bmImg=null;

        try {
            URL myFileUrl = new URL(strImageURL);
            HttpURLConnection conn = (HttpURLConnection)myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            //String json = DownloadHtml("http://서버주소/appdata.php");
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            bmImg = BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            e.printStackTrace();
        }



        //  bmImg = resizeBitmapImage(bmImg,100);

        return bmImg;
    }
    public synchronized Bitmap GetImageFromURL2(String strImageURL) {
        Bitmap bmImg=null;

        try {
            URL myFileUrl = new URL(strImageURL);
            HttpURLConnection conn = (HttpURLConnection)myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            //String json = DownloadHtml("http://서버주소/appdata.php");
            InputStream is = conn.getInputStream();

            bmImg = BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bmImg;
    }

    public synchronized  void count_(String name2 ){
        try{
            String name = name2;


            String link="http://appddi.com/database/upload_count.php";
            String data  = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");


            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write( data );
            wr.flush();



        }
        catch(Exception e){
            new String("Exception: " + e.getMessage());
        }
    }
}

class DatabaseUpload_event extends AsyncTask<String, Integer,String> {
    static private ArrayList<Item03> m_arr = new ArrayList<Item03>();

    public ArrayList<Item03> getM_arr(){  return m_arr;}
    @Override
    protected  synchronized String doInBackground(String... urls) {
        StringBuilder jsonHtml = new StringBuilder();
        try{
            // 연결 url 설정
            URL url = new URL(urls[0]);
            // 커넥션 객체 생성
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            // 연결되었으면.
            if(conn != null){
                conn.setConnectTimeout(20000);
                conn.setUseCaches(false);
                // 연결되었음 코드가 리턴되면.
                if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                    for(;;){
                        // 웹상에 보여지는 텍스트를 라인단위로 읽어 저장.
                        String line = br.readLine();
                        if(line == null) break;
                        // 저장된 텍스트 라인을 jsonHtml에 붙여넣음
                        jsonHtml.append(line + "\n");
                    }
                    br.close();
                }
                conn.disconnect();
            }
        } catch(Exception ex){
            System.out.print(ex.getLocalizedMessage());
        }
        return jsonHtml.toString();

    }

    protected  void onPostExecute(String str){

        String txt1,txt2,time;

        try{

            JSONObject root = new JSONObject(str);
            JSONArray ja = root.getJSONArray("results");
            if(m_arr.size() == ja.length()) return;

            for(int i=0; i<ja.length(); i++){
                JSONObject jo = ja.getJSONObject(i);

                txt1 = jo.getString("name");
                txt2 = jo.getString("content");
                time = jo.getString("day");


                m_arr.add(new Item03(txt1,time,txt2));

            }
            System.out.print(m_arr.size());
        }catch(Exception e){
            e.printStackTrace();
        }

    }



}

class DatabaseUpload_notice extends AsyncTask<String, Integer,String> {
    static private ArrayList<Item02> m_arr = new ArrayList<Item02>();

    public ArrayList<Item02> getM_arr(){  return m_arr;}
    @Override
    protected  synchronized String doInBackground(String... urls) {
        StringBuilder jsonHtml = new StringBuilder();
        try{
            // 연결 url 설정
            URL url = new URL(urls[0]);
            // 커넥션 객체 생성
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            // 연결되었으면.
            if(conn != null){
                conn.setConnectTimeout(20000);
                conn.setUseCaches(false);
                // 연결되었음 코드가 리턴되면.
                if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                    for(;;){
                        // 웹상에 보여지는 텍스트를 라인단위로 읽어 저장.
                        String line = br.readLine();
                        if(line == null) break;
                        // 저장된 텍스트 라인을 jsonHtml에 붙여넣음
                        jsonHtml.append(line + "\n");
                    }
                    br.close();
                }
                conn.disconnect();
            }
        } catch(Exception ex){
            System.out.print(ex.getLocalizedMessage());
        }
        return jsonHtml.toString();

    }

    protected  void onPostExecute(String str){

        String txt1,txt2,txt3;

        try{

            JSONObject root = new JSONObject(str);
            JSONArray ja = root.getJSONArray("results");
            if(m_arr.size() == ja.length()) return;

            for(int i=0; i<ja.length(); i++){
                JSONObject jo = ja.getJSONObject(i);
                txt3 = jo.getString("notice_title");
                txt1 = jo.getString("notice_text");
                txt2 = jo.getString("notice_day");



                m_arr.add(new Item02(txt3,txt1,txt2));

            }
            System.out.print(m_arr.size());
        }catch(Exception e){
            e.printStackTrace();
        }

    }



}

class Count{
    public static synchronized void count_main(){
        try {
            URL myFileUrl = new URL("http://appddi.com/database/count_main.php");
            HttpURLConnection conn = (HttpURLConnection)myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();

            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


class InsertData extends AsyncTask<String, Void, String> {




    @Override
    protected String doInBackground(String... params) {

        try{
            String name = (String)params[0];


            String link="http://appddi.com/database/upload_count.php";
            String data  = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");
            //    data += "&" + URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write( data );
            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                sb.append(line);
                break;
            }
            return sb.toString();
        }
        catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }

    }
}