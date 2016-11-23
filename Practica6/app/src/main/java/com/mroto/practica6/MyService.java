package com.mroto.practica6;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private static final String TAG="MyService";
    private static final long WAIT_MILLIS=7000;

    public MyService(){}

    @Override
    public IBinder onBind(Intent intent) {
        //OBLIGATORI
        Log.e(MyService.TAG,"onBind");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.e(MyService.TAG,"onStartCommand");
        ProcessThings.waitMillis(MyService.WAIT_MILLIS);
        //stop service:
        stopSelf();
        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy(){
        Log.e(MyService.TAG,"onDestroy");
        super.onDestroy();
    }

    @Override
    public void onCreate(){
        Log.e(MyService.TAG,"onCreate");
    }

}
