package appddi.ma_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class Event extends AppCompatActivity {

    Toolbar toolbar;
    private ArrayList<Item03> n_arr;
    private List_Adapter02 adapter02;
    DatabaseUpload_event temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        temp = new DatabaseUpload_event();
        n_arr = temp.getM_arr();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(Html.fromHtml("<big>뭐물래?</big>"));

        setSupportActionBar(toolbar);


        setList2();
    }


    private void setList2(){


        ListView lv = (ListView)findViewById(R.id.iv_view1);


        adapter02 = new List_Adapter02(Event.this, n_arr);
        lv.setAdapter(adapter02);
        //lv.setDivider(null); 구분선을 없에고 싶으면 null 값을 set합니다.
        lv.setDividerHeight(5); //구분선의 굵기를 좀 더 크게 하고싶으면 숫자로 높이 지정
        lv.setOnItemClickListener(adapter02.mItemClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                //NavUtils.navigateUpFromSameTask(this);
                finish();
                return true;

            case R.id.action_settings:
                Intent intent_01 = new Intent(getApplicationContext(), Making_Activity.class);
                startActivity(intent_01);
        }
        return super.onOptionsItemSelected(item);

    }

}