package com.mroto.practica6;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {

    private static final String TAG="MyIntentService";
    private static final long WAIT_MILLIS=7000;

    public MyIntentService() {
        super("MyIntentService");
        setIntentRedelivery(true);
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.e(MyIntentService.TAG,"onCreate");
    }

    @Override
    protected void onHandleIntent(Intent intent){
        ProcessThings.waitMillis(MyIntentService.WAIT_MILLIS);
    }
}
