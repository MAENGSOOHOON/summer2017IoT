package appddi.ma_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;

/**
 * Created by Cho on 2016-03-01.
 */
public class Loading extends Activity {

    DatabaseUpload task;
    String imgUrl = "http://appddi.com/database/upload_and.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        task = new DatabaseUpload();
        task.execute(imgUrl);

        try {
            Thread.sleep(400);
        }

        catch (InterruptedException e){
            e.printStackTrace();
        }

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
