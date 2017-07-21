package appddi.ma_project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Cho on 2016-03-01.
 */
public class List_Adapter02 extends BaseAdapter {   //이벤트 어댑터

    private LayoutInflater nInflater;
    private Activity n_activity;
    private ArrayList<Item03> arr_02;
    private ArrayList<Map> map;
    private DatabaseUpload temp;
    public List_Adapter02(Activity act02, ArrayList<Item03> arr_item_02) {
        this.n_activity = act02;
        arr_02 = arr_item_02;

        nInflater =(LayoutInflater) n_activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return arr_02.size();
    }

    @Override
    public Object getItem(int position) {
        return arr_02.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            int res = 0;
            res = R.layout.list_item02;
            convertView = nInflater.inflate(res, parent, false);
        }


        TextView title = (TextView) convertView.findViewById(R.id.day);
        TextView text_01 = (TextView) convertView.findViewById(R.id.title02);
        //Button btn_01 = (Button)convertView.findViewById(R.id.map_btn1);
        LinearLayout layout_view02 = (LinearLayout) convertView.findViewById(R.id.iv_view2);

        temp = new DatabaseUpload();
        final ArrayList<Item>  semple = temp.getM_arr();



        title.setText( arr_02.get(position).getContent());
        text_01.setText(arr_02.get(position).getTitle()+"   "+arr_02.get(position).getDay());


        /*btn_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int select = getCoordinate(arr_02.get(position).getTitle());
                if (select == -1) System.exit(0);


                Intent intent = new Intent(n_activity, Map.class); //원하는 액티비티로 이동 map을 구현한 액티비티로 설정하면 되겠죠잉?


                intent.putExtra("latitude", semple.get(select).getLatitude());
                intent.putExtra("longitude", semple.get(select).getLongitude());
                intent.putExtra("title", semple.get(select).getTitle());
                intent.putExtra("phone", semple.get(select).getPhone());
                intent.putExtra("people", semple.get(select).getPeopel());

                n_activity.startActivity(intent);           //http://www.androidside.com/bbs/board.php?bo_table=B49&wr_id=131743  <-- 자세한 설명은 링크 댓글에..
            }
        });*/
        /*
        layout_view02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(n_activity, Information_Activity.class); //원하는 액티비티로 이동 map을 구현한 액티비티로 설정하면 되겠죠잉?
                n_activity.startActivity(intent);
            }
        });
        */
  /*  버튼에 이벤트처리를 하기위해선 setTag를 이용해서 사용할 수 있습니다.
       *   Button btn 가 있다면, btn.setTag(position)을 활용해서 각 버튼들
       *   이벤트처리를 할 수 있습니다.
   */
        return convertView;

    }

    public int getCoordinate(String title){
        temp = new DatabaseUpload();
        ArrayList<Item> semple = temp.getM_arr();

        for(int i =0 ; i <semple.size();i++ ){
            if( semple.get(i).getTitle().equals(title) )
                return i;

        }
        return -1;
    }

    public AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long l_position) {
            // parent는 AdapterView의 속성의 모두 사용 할 수 있다.
            int select = getCoordinate(arr_02.get(position).getTitle());
            if(select == -1 ) System.exit(0);

            temp = new DatabaseUpload();
            final ArrayList<Item>  semple = temp.getM_arr();


            Intent intent = new Intent(n_activity, Information_Activity.class);
            //putExtra 로 선택한 아이템의 정보를 인텐트로 넘겨 줄 수 있다.

            intent.putExtra("title", semple.get(select).getTitle());
            //   intent.putExtra("content", arr.get(a).getContent());

            intent.putExtra("time", semple.get(select).getTime());
            intent.putExtra("phone", semple.get(select).getPhone());
            intent.putExtra("address", semple.get(select).getAddress());
            intent.putExtra("imagePath",semple.get(select).getImagePath());
            intent.putExtra("people", semple.get(select).getPeopel());
            intent.putExtra("time2",semple.get(select).getTime2());
            intent.putExtra("menu",semple.get(select).getMenu());
            intent.putExtra("payment",semple.get(select).getPayment());
            n_activity.startActivity(intent);
        }
    };
}

