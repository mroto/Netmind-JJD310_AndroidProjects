package com.mroto.practica6;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/*La aplicación a implementar consta de un layout con varios botones, un TextView y un EditText.

Cuando el botón superior es pulsado, se arranca un Service en el que se simula una tarea “pesada”
(en realidad se manda al hilo de procesamiento en cuestión a “dormir”). Sin embargo, durante este
lapso de tiempo, la aplicación no responde a la interacción con el usuario, de forma que el EditText,
 por ejemplo, no puede utilizarse para actualizar el  TextView.

Los dos botones siguientes lanzan también un Service pero en otro hilo de ejecución (worker thread).
 Un Intent Service se ejecuta en otro hilo por defecto, mientras que un Bound Service es un Service
  estándar al que la Activity en ejecución se puede “enganchar” (bound).

 Será labor del desarrollador que este Service se ejecute en otro hilo (usando un Thread, por ejemplo).*/


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create listeners:
        final Button btn_DispInfo = (Button) this.findViewById(R.id.btnDispInfo);
        btn_DispInfo.setOnClickListener(this);
        final Button btn_StartBoundService = (Button) this.findViewById(R.id.btnStartBoundService);
        btn_StartBoundService.setOnClickListener(this);
        final Button btn_StartService = (Button) this.findViewById(R.id.btnStartService);
        btn_StartService.setOnClickListener(this);
        final Button btn_StartIntentService = (Button) this.findViewById(R.id.btnStartIntentService);
        btn_StartIntentService.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnDispInfo){
            this.displayInfo();
        }else if (view.getId() == R.id.btnStartBoundService){
            this.startMyBoundService();
        }else if (view.getId() == R.id.btnStartIntentService){
            this.startMyIntentService();
        }else if (view.getId() == R.id.btnStartService){
            this.startMyService();
        }
    }

    private void startMyService(){
        Intent intent=new Intent(this, MyService.class);
        startService(intent);
    }

    private void startMyIntentService(){
        Intent intent=new Intent(this, MyIntentService.class);
        startService(intent);

    }

    private ServiceConnection msrvConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e(MainActivity.TAG,"onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e(MainActivity.TAG,"onServiceDisconnected");
        }
    };

    private void startMyBoundService() {
        Log.e(MainActivity.TAG,"startMyBoundService");
        Intent intent=new Intent(this,MyBoundService.class);
        bindService(intent, msrvConnection, Context.BIND_AUTO_CREATE);
    }

    private void displayInfo(){
        Log.e(MainActivity.TAG,"displayInfo");
    }
}
