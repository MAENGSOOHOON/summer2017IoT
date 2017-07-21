package appddi.ma_project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Cho on 2016-03-01.
 */
public class List_Adapter03 extends BaseAdapter {

    private LayoutInflater bInflater;
    private Activity b_activity;
    private ArrayList<Item02> arr_03;


    public List_Adapter03(Activity act03, ArrayList<Item02> arr_item_03) {
        this.b_activity = act03;
        arr_03 = arr_item_03;

        bInflater =(LayoutInflater) b_activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return arr_03.size();
    }

    @Override
    public Object getItem(int position) {
        return arr_03.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            int res = 0;
            res = R.layout.list_item03;
            convertView = bInflater.inflate(res, parent, false);
        }


        TextView text = (TextView) convertView.findViewById(R.id.notice_text);
        TextView day = (TextView) convertView.findViewById(R.id.notice_day);

        LinearLayout layout_view03 = (LinearLayout) convertView.findViewById(R.id.notice_view2);



        text.setText(arr_03.get(position).getNoticeTitle());
        day.setText(arr_03.get(position).getNoticeDay());



  /*  버튼에 이벤트처리를 하기위해선 setTag를 이용해서 사용할 수 있습니다.
       *   Button btn 가 있다면, btn.setTag(position)을 활용해서 각 버튼들
       *   이벤트처리를 할 수 있습니다.
   */
        return convertView;

    }


    public AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long l_position) {


            DatabaseUpload_notice temp = new DatabaseUpload_notice();
            final ArrayList<Item02>  semple = temp.getM_arr();


            Intent intent = new Intent(b_activity, GongJi2.class);


            intent.putExtra("text", semple.get(position).getNoticeText());


            b_activity.startActivity(intent);
        }
    };
}
