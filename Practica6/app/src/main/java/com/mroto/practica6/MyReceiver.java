package com.mroto.practica6;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    private static final String TAG="MyReceiver";

    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e(TAG,"onReceive");
        Intent myIntent = new Intent(context, IntentService.class);
        context.startService(myIntent);
    }
}
