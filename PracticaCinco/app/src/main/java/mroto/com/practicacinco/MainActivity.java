package mroto.com.practicacinco;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_CODE=1;
    private static final int NOTIFICATION_ID=2;
    private int notificationId=0;
    private static final String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(MainActivity.TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Add Listeners:
        final Button btn_notify = (Button) this.findViewById(R.id.btn_notify);
        btn_notify.setOnClickListener(this);
        final Button btn_notifyCustom = (Button) this.findViewById(R.id.btn_notify_custom);
        btn_notifyCustom.setOnClickListener(this);

        //--------------
        // Using the 'AlarmManager' service to start an 'Activity' in 3 seconds
        // 'Activity' intent which is wrapped by a 'PendingIntent' object
        Intent activityIntent = new Intent(this, SecondActivity.class);
        PendingIntent mActPendIntent = PendingIntent.getActivity(this, 0, activityIntent, PendingIntent.FLAG_ONE_SHOT);
        // 'AlarmManager' service call and configuration to 3 seconds from right now
        AlarmManager mAlarmManager = (AlarmManager) this.getSystemService(Service.ALARM_SERVICE);
        mAlarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 3000, mActPendIntent);

        Toast.makeText(this, "Alarm set in 3 seconds time", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View view) {
        Log.e(MainActivity.TAG,"onClick");
        RemoteViews mRemoteViews=null;
        if (view.getId() == R.id.btn_notify)
        {
            mRemoteViews=null;
        }else if(view.getId() == R.id.btn_notify_custom){
            mRemoteViews = new RemoteViews(this.getPackageName(),R.layout.notification_custom);
        }
        this.addNotification(mRemoteViews);
    }

    private void addNotification(RemoteViews mRemoteViews){
        Log.e(MainActivity.TAG,"Adding Notification...");
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle("My notification");
        mBuilder.setContentText("This is a simple text for my notification");

        Intent notificationIntent = new Intent(this, MainActivity.class);

        PendingIntent mPendingIntent =  PendingIntent.getActivity(this,
                MainActivity.REQUEST_CODE,
                notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        mBuilder.setContentIntent(mPendingIntent);

        //Apply customs if any:
        if(mRemoteViews!=null){
            mBuilder.setContent(mRemoteViews);
        }

        NotificationManager mNotifManager=(NotificationManager) this.getSystemService(Service.NOTIFICATION_SERVICE);
        mNotifManager.notify(MainActivity.NOTIFICATION_ID, mBuilder.build());
        //New notification with different ID each time:
        //mNotifManager.notify(this.notificationId, mBuilder.build());
        //notificationId++;
    }
}
