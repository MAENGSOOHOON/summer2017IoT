package appddi.ma_project;

/**
 * Created by choD on 2016-09-08.
 */
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;

import java.net.URLDecoder;
import java.util.Timer;
import java.util.TimerTask;

public class GCMIntentService extends GCMBaseIntentService {

    public  String token;

    private static final String TAG = "GCM Tutorial::Service";

    // Use your PROJECT ID from Google API into SENDER_ID
    public static final String SENDER_ID = "350603521181";

    public GCMIntentService() {
        super(SENDER_ID);
    }

    @Override
    protected void onRegistered(Context context, String registrationId) {

        Log.i(TAG, "onRegistered: registrationId=" + registrationId);
    }

    @Override
    protected void onUnregistered(Context context, String registrationId) {

        Log.i(TAG, "onUnregistered: registrationId=" + registrationId);
        token = registrationId;
    }

    @Override
    protected void onMessage(Context context, Intent data) {
        String message;
        String test = "";
        // Message from PHP server
        try {

            message = data.getStringExtra("msg");

            message = URLDecoder.decode(message, "utf-8");
            Intent intent = new Intent(this, MainActivity.class);
            // Pass data to the new activity
            intent.putExtra("msg", message);
            // Starts the activity on notification click
            PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            // Create the notification with a notification builder


            Notification notification = new Notification.Builder(this)
                    .setSmallIcon(R.drawable.icon)
                    .setWhen(System.currentTimeMillis())
                    .setContentTitle("★뭐물래 이벤트★")
                    .setContentText(message).setContentIntent(pIntent)
                    .getNotification()
                    ;
            // Remove the notification on click
            notification.defaults |= Notification.DEFAULT_VIBRATE;  //push 알림오면 진동오게 설정
            notification.flags |= Notification.FLAG_AUTO_CANCEL;

            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.notify(R.string.app_name, notification);


            {
                // Wake Android Device when notification received
                PowerManager pm = (PowerManager) context
                        .getSystemService(Context.POWER_SERVICE);
                final PowerManager.WakeLock mWakelock = pm.newWakeLock(
                        PowerManager.FULL_WAKE_LOCK
                                | PowerManager.ACQUIRE_CAUSES_WAKEUP, "GCM_PUSH");
                mWakelock.acquire();

                // Timer before putting Android Device to sleep mode.
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    public void run() {
                        mWakelock.release();
                    }
                };
                timer.schedule(task, 5000);
            }

        }catch (Exception e){}
    }

    @Override
    protected void onError(Context arg0, String errorId) {

        Log.e(TAG, "onError: errorId=" + errorId);
    }

}