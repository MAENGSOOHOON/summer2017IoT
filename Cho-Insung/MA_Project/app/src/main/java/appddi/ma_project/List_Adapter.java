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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Cho on 2016-02-03.
 */
public class List_Adapter extends BaseAdapter implements View.OnClickListener {

    private LayoutInflater mInflater;
    private Activity m_activity;
    private ArrayList<Item> arr;
    private DatabaseUpload temp;


    public List_Adapter(Activity act, ArrayList<Item> arr_item) {
        this.m_activity = act;
        arr = arr_item;

        mInflater = (LayoutInflater) m_activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int position) {
        return arr.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            int res = 0;
            String location = new String();

            res = R.layout.list_item01;
            convertView = mInflater.inflate(res, parent, false);
        }

        ImageView imView = (ImageView) convertView.findViewById(R.id.vi_image);

        TextView title = (TextView) convertView.findViewById(R.id.vi_title);
        Button text_01 = (Button) convertView.findViewById(R.id.vi_button);
        TextView text_02 = (TextView) convertView.findViewById(R.id.vi_text);


        convertView.setFocusable(false);

        title.setFocusable(false);
        text_01.setFocusable(false);
        imView.setFocusable(false);

        temp = new DatabaseUpload();
        String location = new String();
        if (arr.get(position).getLongitude() > 128.492011) {
            location = "동문";
        } else if (arr.get(position).getLatitude() < 35.852427 && arr.get(position).getLongitude() > 128.47988) {
            location = "정문";
        } else if (arr.get(position).getLongitude() < 128.47988) {
            location = "남문";
        }

        imView.setImageBitmap(arr.get(position).getFirstImage());
        title.setText(arr.get(position).getTitle());
        text_02.setText(arr.get(position).getTime() + " ");
        text_01.setText(location);


        Button btn_01 = (Button) convertView.findViewById(R.id.map_btn);
        btn_01.setFocusable(false);
        btn_01.setTag(position);
        btn_01.setOnClickListener(this);

        //  text_02.setText(arr.get(position).Content_01);
  /*  버튼에 이벤트처리를 하기위해선 setTag를 이용해서 사용할 수 있습니다.
       *   Button btn 가 있다면, btn.setTag(position)을 활용해서 각 버튼들
       *   이벤트처리를 할 수 있습니다.
   */

        return convertView;

    }

    public void sortList(ArrayList<Item> arr) {

        Item temp;
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 1; j < arr.size(); j++) {
                if (arr.get(i).getTitle().compareTo(arr.get(j).getTitle()) == 1)
                    temp = arr.get(i);
            }
        }
    }


    public AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long l_position) {
            // parent는 AdapterView의 속성의 모두 사용 할 수 있다.
            InsertData temp = new InsertData();
            temp.execute(arr.get(position).getTitle());


            Intent intent = new Intent(m_activity, Information_Activity.class);
            //putExtra 로 선택한 아이템의 정보를 인텐트로 넘겨 줄 수 있다.

            intent.putExtra("title", arr.get(position).getTitle());
            intent.putExtra("time", arr.get(position).getTime());
            intent.putExtra("phone", arr.get(position).getPhone());
            intent.putExtra("address", arr.get(position).getAddress());
            intent.putExtra("imagePath", arr.get(position).getImagePath());
            intent.putExtra("people", arr.get(position).getPeopel());
            intent.putExtra("time2", arr.get(position).getTime2());
            intent.putExtra("menu", arr.get(position).getMenu());
            intent.putExtra("payment", arr.get(position).getPayment());
            m_activity.startActivity(intent);
        }
    };

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();


        Intent intent = new Intent(m_activity, Map.class); //원하는 액티비티로 이동 map을 구현한 액티비티로 설정하면 되겠죠잉?


        intent.putExtra("latitude", arr.get(position).getLatitude());//
        intent.putExtra("longitude", arr.get(position).getLongitude());//
        intent.putExtra("title", arr.get(position).getTitle());//
        intent.putExtra("time", arr.get(position).getTime());
        intent.putExtra("phone", arr.get(position).getPhone());//
        intent.putExtra("address", arr.get(position).getAddress());
        intent.putExtra("imagePath", arr.get(position).getImagePath());//
        intent.putExtra("people", arr.get(position).getPeopel());//
        intent.putExtra("time2", arr.get(position).getTime2());//
        intent.putExtra("menu", arr.get(position).getMenu());//
        intent.putExtra("payment", arr.get(position).getPayment());//

        m_activity.startActivity(intent);           //http://www.androidside.com/bbs/board.php?bo_table=B49&wr_id=131743  <-- 자세한 설명은 링크 댓글에..

    }
}
