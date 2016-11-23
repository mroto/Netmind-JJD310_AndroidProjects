package com.mroto.practica6;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyBoundService extends Service {

    private static final String TAG="MyBoundService";
    private static final long WAIT_MILLIS=7000;

    private IBinder myBinder=new MyBinder();


    public MyBoundService(){}

    public class MyBinder extends Binder {
        MyBoundService getService(){
            return MyBoundService.this;
        }

        private int number=4543;
        public int getNumber(){
            return this.number;
        }
    }

    @Override
    public void onCreate(){
        Log.e(MyBoundService.TAG,"onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(MyBoundService.TAG,"onBind");
        ProcessThings.waitMillis(MyBoundService.WAIT_MILLIS);

        return this.myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent){
        Log.e(MyBoundService.TAG,"onUnbind");
        return false;
    }
}
