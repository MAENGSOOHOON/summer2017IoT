package appddi.ma_project;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.StrictMode;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Cho on 2016-03-01.
 */
public class CustomSwipeAdapter extends PagerAdapter {


    private String[] imagePath;
    private Context ctx;
    private LayoutInflater layoutInflater;
    private DatabaseUpload temp;
    private ImageView imageView;
    private  int position2;
    public CustomSwipeAdapter(Context ctx, String[] imagePath)
    {
        this.ctx = ctx;
        this.imagePath = imagePath;}

    @Override
    public int getCount() {
        return imagePath.length-1;}

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view==((LinearLayout)object);}

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        this.position2 = position;
        String[] image_path2 = new String[imagePath.length-1];

        for(int i=1; i < imagePath.length; i++)
            image_path2[i-1]   = imagePath[i];

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        Bitmap image2=null;
        temp = new DatabaseUpload();
        if(position2 > image_path2.length) position2 = 0;
        try {

            image2 = temp.GetImageFromURL2(image_path2[position2]);
        }catch (Exception e){
            e.printStackTrace();}
        layoutInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View item_view = layoutInflater.inflate(R.layout.swipe_layout,container,false);

        imageView = (ImageView) item_view.findViewById(R.id.image_view);


        imageView.setImageBitmap(image2);

        container.addView(item_view);

        return item_view;
    }


    public int getPosition(){return position2;}

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
