package appddi.ma_project;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Menu_Choice_2nd extends AppCompatActivity  {

    Toolbar toolbar;
    DrawerLayout dlDrawer;
    ActionBarDrawerToggle dtToggle;
    DatabaseUpload temp;
    Button qq;
    int number;
    private ArrayList<Item> m_arr, atemp;

    private List_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__choice_2nd);

        Intent intent = getIntent();
        number = intent.getIntExtra("num", 0);
        if(number ==0) number = intent.getIntExtra("number",99);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(Html.fromHtml("<big>뭐물래?</big>"));
        dlDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        qq = (Button)findViewById(R.id.jido);



        setSupportActionBar(toolbar);
        dtToggle = new ActionBarDrawerToggle(this, dlDrawer, R.string.app_name, R.string.app_name);
        dlDrawer.setDrawerListener(dtToggle);

        selectCategory(number);
        setList(number);

        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu_Choice_2nd.this, Map.class);

                intent.putExtra("number", number);

                Menu_Choice_2nd.this.startActivity(intent);
            }
        });
    }



    public void selectCategory(int number) {
        TextView category = (TextView) findViewById((R.id.category));


        switch (number / 10) {

            case 1:
                switch (number % 10) {

                    case 1:
                        category.setText("메뉴 > 밥 > 일반음식점");
                        break;

                    case 2:
                        category.setText("메뉴 > 밥 > 덮밥");
                        break;

                    case 3:
                        category.setText("메뉴 > 밥 > 국 & 탕");
                        break;

                    case 4:
                        category.setText("메뉴 > 밥 > 도시락");
                        break;

                    case 5:
                        category.setText("메뉴 > 밥 > 기타");
                        break;
                }
                break;

            case 2:
                switch (number % 10) {

                    case 1:
                        category.setText("메뉴 > 고기 > 돼지고기");
                        break;

                    case 2:
                        category.setText("메뉴 > 고기 > 쇠고기");
                        break;

                    case 3:
                        category.setText("메뉴 > 고기 > 닭");
                        break;

                    case 4:
                        category.setText("메뉴 > 고기 > 곱창");
                        break;

                    case 5:
                        category.setText("메뉴 > 고기 > 기타");
                        break;
                }
                break;

            case 3:
                switch (number % 10) {

                    case 1:
                        category.setText("메뉴 > 면 > 중화요리");
                        break;

                    case 2:
                        category.setText("메뉴 > 면 > 라면");
                        break;

                    case 3:
                        category.setText("메뉴 > 면 > 국수");
                        break;

                    case 4:
                        category.setText("메뉴 > 면 > 파스타");
                        break;

                    case 5:
                        category.setText("메뉴 > 면 > 기타");
                        break;
                }
                break;

            case 4:

                category.setText("메뉴 > 기타");

                break;

            case 5:
                category.setText("메뉴 > 고기 > 기타");
                break;
        }

    }

    private void setList(int number) {

        int category = number;

        temp = new DatabaseUpload();

        m_arr = new ArrayList<Item>();
        atemp = temp.getM_arr();
        System.out.print(atemp.size());

        if(m_arr.size()==0) {                    //m_arr에 카테고리에 맞는 아이템을 삽입
            for (int i = 0; i < atemp.size(); i++)
                if (atemp.get(i).getCategory() == category)
                    m_arr.add(atemp.get(i));

            System.out.print(m_arr.size());
        }
        Collections.sort(m_arr, new NameAscCompare());       //정렬


        for(int i=0; i < m_arr.size() ; i++){           // 리스트 이미지를 받아옴
            if(m_arr.get(i).getFirstImage()==null){
                String []temp2 = m_arr.get(i).getImagePath().split("\n");
                //           Bitmap qwe =  temp.GetImageFromURL2(temp2[0]);
                m_arr.get(i).setFirstImage(temp.GetImageFromURL2(temp2[0]));
            }



            ListView lv = (ListView) findViewById(R.id.listview_chicken);


            adapter = new List_Adapter(Menu_Choice_2nd.this, m_arr);


            lv.setAdapter(adapter);
            lv.setFocusable(false);


            lv.setOnItemClickListener(adapter.mItemClickListener);

            //lv.setDivider(null); 구분선을 없에고 싶으면 null 값을 set합니다.
            //lv.setDividerHeight(5); 구분선의 굵기를 좀 더 크게 하고싶으면 숫자로 높이 지정
        }
    }




    public void listUpdate() {
        adapter.notifyDataSetChanged();
        //​리스트뷰 값들의 변화가 있을때 아이템들을 다시 배치 할 때 사용되는 메소드입니다.
    }

    public void event1(View v) {
        Intent intent_01 = new Intent(getApplicationContext(), Event.class);
        startActivity(intent_01);
        dlDrawer.closeDrawers();
    }

    public void btn_su(View v) {
        Intent intent_01 = new Intent(getApplicationContext(), Su.class);
        startActivity(intent_01);
        dlDrawer.closeDrawers();
    }

    public void btn_hu(View v) {
        Intent intent_01 = new Intent(getApplicationContext(), Hu.class);
        startActivity(intent_01);
        dlDrawer.closeDrawers();
    }

    public void notice(View v) {
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

class NameAscCompare implements Comparator<Item> {

    /**
     * 오름차순(ASC)
     */
    @Override
    public int compare(Item arg0, Item arg1) {
        // TODO Auto-generated method stub
        return arg0.getTitle().compareTo(arg1.getTitle());
    }

}
