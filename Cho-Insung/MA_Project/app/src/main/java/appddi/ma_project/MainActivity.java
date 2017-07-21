package appddi.ma_project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ViewFlipper;

import com.google.android.gcm.GCMRegistrar;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout dlDrawer;
    ActionBarDrawerToggle dtToggle;
    Button allJido,dong,jung,north;
    final Activity activity = this;
    ViewFlipper flipper;

    private String PROJECT_ID = "350603521181";
    String TAG = "GCM Tutorial::Activity";
    String id = GCMIntentService.SENDER_ID;

    DatabaseUpload_event temp;
    DatabaseUpload_notice temp2;
    private BackPressCloseHandler backPressCloseHandler; //종료버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(Html.fromHtml("<big>뭐물래?</big>"));
        dlDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        dlDrawer.closeDrawers();

        allJido = (Button)findViewById(R.id.allJido);
        dong    = (Button)findViewById(R.id.dong);
        jung    = (Button)findViewById(R.id.jung);
        north   = (Button)findViewById(R.id.north);

        setSupportActionBar(toolbar);
        dtToggle = new ActionBarDrawerToggle(this, dlDrawer, R.string.app_name, R.string.app_name);
        dlDrawer.setDrawerListener(dtToggle);

        flipper = (android.widget.ViewFlipper)findViewById(R.id.view_flipper);
        flipper.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        flipper.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
        //자동 Flipping 시작(간격 3초)
        flipper.setFlipInterval(3000);
        flipper.startFlipping();

        dong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Map.class);
                intent.putExtra("number", 998);
                MainActivity.this.startActivity(intent);
            }
        });

        north.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Map.class);
                intent.putExtra("number", 996);
                MainActivity.this.startActivity(intent);
            }
        });


        jung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Map.class);
                intent.putExtra("number", 997);
                MainActivity.this.startActivity(intent);
            }
        });

        allJido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Map.class);
                intent.putExtra("number",999);
                MainActivity.this.startActivity(intent);
            }
        });

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        temp = new DatabaseUpload_event();
        temp.execute("http://appddi.com/database/upload_event_and.php");

        temp2 = new DatabaseUpload_notice();
        temp2.execute("http://appddi.com/database/upload_notice_and.php");

        Count.count_main();

        backPressCloseHandler = new BackPressCloseHandler(this);//뒤로가기 버튼이벤트

        flipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (flipper.getDisplayedChild()) {
                    case 0:
                        Intent intent_01 = (new Intent(Intent.ACTION_VIEW, Uri.parse("http://appddi.com")));
                        startActivity(intent_01);
                        break;
                    case 1:
                        Intent it = new Intent(Intent.ACTION_SEND);
                        it.setType("plain/text");
                        String[] tos = {"app_ddi@naver.com"};
                        it.putExtra(Intent.EXTRA_EMAIL, tos);
                        it.putExtra(Intent.EXTRA_SUBJECT, "제목을 입력해 주세요");
                        it.putExtra(Intent.EXTRA_TEXT, "내용을 입력해 주세요");
                        startActivity(it);
                        break;

                }
            }
        });
        GCMRegistrar.checkDevice(this);
        GCMRegistrar.checkManifest(this);
        GCMRegistrar.register(MainActivity.this, GCMIntentService.SENDER_ID);
        regist task1 = new regist();
        task1.execute(this);
    }



    public class regist extends AsyncTask<Context, Integer, String> {

        @Override
        protected String doInBackground(Context... params) {
            // TODO Auto-generated method stub
            String regId;
            do {
                GCMRegistrar.checkDevice(params[0]);
                GCMRegistrar.checkManifest(params[0]);

                regId = GCMRegistrar.getRegistrationId(params[0]);

                if (regId.equals("")) {
                    GCMRegistrar.register(params[0], PROJECT_ID);
                } else {
                    Log.e("id", regId);
                }
            } while (regId == "");
            return regId;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            registDB task = new registDB();
            task.execute(result);
        }
    }

    public class registDB extends AsyncTask<String, Integer, Void> {

        @Override
        protected Void doInBackground(String... params) {
            // TODO Auto-generated method stub
            try {
                String u_id = java.net.URLEncoder.encode(new String(params[0].getBytes("UTF-8")));
                URL url = new URL("http://appddi.com/gcm_upload.php?u_id=" + u_id + "");
                url.openStream();
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
    }


    public void btn_meat(View v){
        Intent intent_01 = new Intent(getApplicationContext(), Menu_Choice_1st.class);
        int tem = 20;
        intent_01.putExtra("number",tem);
        startActivity(intent_01);
    }

    public void btn_gt(View v){
        Intent intent_01 = new Intent(getApplicationContext(), Menu_Choice_2nd.class);
        int tem = 40;
        intent_01.putExtra("number",tem);
        startActivity(intent_01);
    }
    public void btn_noodle(View v){
        Intent intent_01 = new Intent(getApplicationContext(), Menu_Choice_1st.class);
        int tem = 30;
        intent_01.putExtra("number",tem);
        startActivity(intent_01);
    }
    public void btn_bab(View v){
        Intent intent_01 = new Intent(getApplicationContext(), Menu_Choice_1st.class);
        int tem = 10;
        intent_01.putExtra("number",tem);
        startActivity(intent_01);
    }
    public void event(View v){
        Intent intent_01 = new Intent(getApplicationContext(), Event.class);
        startActivity(intent_01);
        dlDrawer.closeDrawers();
    }
    public void event1(View v){
        Intent intent_01 = new Intent(getApplicationContext(), Event.class);
        startActivity(intent_01);
        dlDrawer.closeDrawers();
    }
    public void btn_su(View v){
        Intent it1 = new Intent(Intent.ACTION_SEND);
        it1.setType("plain/text");
        String[] tos = {"app_ddi@naver.com"};
        it1.putExtra(Intent.EXTRA_EMAIL, tos);

        it1.putExtra(Intent.EXTRA_SUBJECT, "제목을 입력해 주세요");
        it1.putExtra(Intent.EXTRA_TEXT, "내용을 입력해 주세요");
        startActivity(it1);
        dlDrawer.closeDrawers();
    }
    public void btn_hu(View v){
        Intent intent_01 = new Intent(getApplicationContext(), Hu.class);
        startActivity(intent_01);
        dlDrawer.closeDrawers();
    }

    public void notice(View v){
        Intent intent_01 = new Intent(getApplicationContext(), Notice_Page.class);
        startActivity(intent_01);
        dlDrawer.closeDrawers();

    }

    public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        dtToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        dtToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (dtToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {

            case R.id.action_settings:
                Intent intent_01 = new Intent(getApplicationContext(), Making_Activity.class);
                startActivity(intent_01);
        }
        return super.onOptionsItemSelected(item);

    }

}