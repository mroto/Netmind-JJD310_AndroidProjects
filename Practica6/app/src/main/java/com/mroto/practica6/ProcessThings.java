package com.mroto.practica6;

import android.util.Log;

/**
 * Created by A5Alumno on 23/11/2016.
 */

public class ProcessThings {

    private static final String TAG="ProcessThings";

    public ProcessThings(){}

    public static void waitMillis(long wait){
        Log.e(ProcessThings.TAG,"hardProcess:enter");
        long time=System.currentTimeMillis()+wait;
        while(time>System.currentTimeMillis()){}
        Log.e(ProcessThings.TAG,"hardProcess:exit");
    }
}