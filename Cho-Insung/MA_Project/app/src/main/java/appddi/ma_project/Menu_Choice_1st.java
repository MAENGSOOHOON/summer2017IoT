package appddi.ma_project;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Menu_Choice_1st extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout dlDrawer;
    ActionBarDrawerToggle dtToggle;

    final Activity activity = this;
    int number;
    Button notice; // 공지사항 버튼

    private BackPressCloseHandler backPressCloseHandler; //종료버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__choice_1st);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(Html.fromHtml("<big>뭐물래?</big>"));
        dlDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);
        dtToggle = new ActionBarDrawerToggle(this, dlDrawer, R.string.app_name, R.string.app_name);
        dlDrawer.setDrawerListener(dtToggle);

        backPressCloseHandler = new BackPressCloseHandler(this);//뒤로가기 버튼이벤트

        Intent intent = getIntent();
        number = intent.getIntExtra("number",99);
        selectPage(number);
    }

    public void selectPage(int number){
        TextView category = (TextView)findViewById(R.id.category);
        Button one = (Button)findViewById(R.id.one);
        Button two = (Button)findViewById(R.id.two);
        Button three = (Button)findViewById(R.id.three);
        Button four = (Button)findViewById(R.id.four);
        Button five = (Button)findViewById(R.id.five);

        switch (number){
            case 10:    category.setText("메뉴 > 밥");
                one.setText("일반음식점");
                two.setText("덮밥");
                three.setText("국 & 탕");
                four.setText("도시락");
                five.setText("김밥 & 분식");
                break;

            case 20:    category.setText("메뉴 > 고기");
                one.setText("돼지고기");
                two.setText("소고기");
                three.setText("닭");
                four.setText("곱창 & 막창");
                five.setText("돈까스");
                break;
            case 30:    category.setText("메뉴 > 면");
                one.setText("중화요리");
                two.setText("라면");
                three.setText("국수");
                four.setText("파스타");
                break;
            case 40:    category.setText("메뉴 > 기타");

        }
    }

    public void btn_meat_tripe(View v){
        Intent intent_01 = new Intent(getApplicationContext(), Menu_Choice_2nd.class);
        int num = number+3;
        intent_01.putExtra("num", num);
        startActivity(intent_01);
    }

    public void btn_meat_pig(View v){
        Intent intent_01 = new Intent(getApplicationContext(), Menu_Choice_2nd.class);
        int num = number+1;
        intent_01.putExtra("num", num);
        startActivity(intent_01);
    }

    public void btn_meat_cow(View v){
        Intent intent_01 = new Intent(getApplicationContext(), Menu_Choice_2nd.class);
        int num = number+2;
        intent_01.putExtra("num", num);
        startActivity(intent_01);
    }



    public void btn_meat_etc(View v){
        Intent intent_01 = new Intent(getApplicationContext(), Menu_Choice_2nd.class);
        int num = number+5;
        intent_01.putExtra("num", num);
        startActivity(intent_01);
    }

    public void btn_meat_chicken(View v){
        Intent intent_01 = new Intent(getApplicationContext(), Menu_Choice_2nd.class);
        int num =number+ 4;
        intent_01.putExtra("num", num);
        startActivity(intent_01);
    }

    public void event1(View v){
        Intent intent_01 = new Intent(getApplicationContext(), Event.class);
        startActivity(intent_01);
        dlDrawer.closeDrawers();
    }
    public void btn_su(View v){
        Intent intent_01 = new Intent(getApplicationContext(), Su.class);
        startActivity(intent_01);
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