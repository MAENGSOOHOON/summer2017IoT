package appddi.ma_project;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class Information_Activity extends AppCompatActivity{

    Toolbar toolbar;
    DrawerLayout dlDrawer;
    ActionBarDrawerToggle dtToggle;
    ViewPager viewpager;
    CustomSwipeAdapter adapter;
    TextView textView, textView2,textView3,textView4,textView5,textView6,textView_menu[], textView_payment[];
    String[] menu,payment;
    ImageView imageView;
    String imagePath[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_);

        Intent intent = getIntent();


        imagePath = intent.getStringExtra("imagePath").split("\n");



        viewpager = (ViewPager) findViewById(R.id.view_pager);
        adapter = new CustomSwipeAdapter(this,imagePath);
        viewpager.setAdapter(adapter);

        Button button = (Button) findViewById(R.id.imback);
        button.setOnClickListener(btnListener);
        button = (Button) findViewById(R.id.imnext);
        button.setOnClickListener(btnListener);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        dlDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);
        dtToggle = new ActionBarDrawerToggle(this, dlDrawer, R.string.app_name, R.string.app_name);
        dlDrawer.setDrawerListener(dtToggle);

        textView = (TextView)findViewById((R.id.infoTitle));
        textView2 = (TextView)findViewById((R.id.infoTime));
        textView3 = (TextView)findViewById((R.id.infoPhone));
        textView4 = (TextView)findViewById((R.id.infoAddress));
        textView5 = (TextView)findViewById((R.id.people));
        textView6 = (TextView)findViewById((R.id.infoTime1));


        textView_menu = new TextView[8];
        textView_payment = new TextView[8];


        textView_menu[0] = (TextView)findViewById((R.id.menu1));
        textView_menu[1] = (TextView)findViewById((R.id.menu2));
        textView_menu[2] = (TextView)findViewById((R.id.menu3));
        textView_menu[3] = (TextView)findViewById((R.id.menu4));
        textView_menu[4] = (TextView)findViewById((R.id.menu5));
        textView_menu[5] = (TextView)findViewById((R.id.menu6));
        textView_menu[6] = (TextView)findViewById((R.id.menu7));
        textView_menu[7] = (TextView)findViewById((R.id.menu8));

        textView_payment[0] = (TextView)findViewById((R.id.payment1));
        textView_payment[1] = (TextView)findViewById((R.id.payment2));
        textView_payment[2] = (TextView)findViewById((R.id.payment3));
        textView_payment[3] = (TextView)findViewById((R.id.payment4));
        textView_payment[4] = (TextView)findViewById((R.id.payment5));
        textView_payment[5] = (TextView)findViewById((R.id.payment6));
        textView_payment[6] = (TextView)findViewById((R.id.payment7));
        textView_payment[7] = (TextView)findViewById((R.id.payment8));
        //  imageView = (ImageView)findViewById(R.id.imfoImageView);



        String title = intent.getStringExtra("title");
        textView.setText(title);

        textView2.setText(intent.getStringExtra("time"));
        textView3.setText(intent.getStringExtra("phone"));
        textView4.setText(intent.getStringExtra("address"));
        textView5.setText(intent.getStringExtra("people"));
        textView6.setText(intent.getStringExtra("time2"));

        menu  = intent.getStringExtra("menu").split("!");
        payment = intent.getStringExtra("payment").split("!");

        for(int i = 0 ; i <menu.length; i++){
            textView_menu[i].setText(menu[i]);
            textView_payment[i].setText(payment[i]);

        }



        //   imageView.setImageBitmap(image);


    }

    private View.OnClickListener btnListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.imback:
                    viewpager.setCurrentItem(viewpager.getCurrentItem()-1);
                    break;
                case R.id.imnext:
                    viewpager.setCurrentItem(viewpager.getCurrentItem()+1);
                    break;
            }
        }
    };


    public void tel(View v){
        String toDial ="tel:"+textView3.getText().toString();
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(toDial)));

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
    public void btn_swipe(View v){
        Intent intent_01 = new Intent(getApplicationContext(), zoom.class);
        intent_01.putExtra("number", viewpager.getCurrentItem() );
        intent_01.putExtra("imagePath", imagePath);
        startActivity(intent_01);
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

