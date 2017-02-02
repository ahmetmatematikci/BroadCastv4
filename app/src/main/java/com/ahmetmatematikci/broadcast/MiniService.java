package com.ahmetmatematikci.broadcast;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by a on 2/1/17.
 */
public class MiniService  extends  Services{
    private Timer  mTimer;
    private long mStartTime;

    private String TAG= MiniService.class.getSimpleName();


    public static String ACTION_BROADCAST = "Bu bir Broastcasttır";
    private MiniServiceBinder mBinder = new MiniServiceBinder();

private TimerTask mtimerTask =  new TimerTask() {
    @Override
    public void run() {
        long currentTime = System.currentTimeMillis();
        final  Intent broadcastIntent = new Intent(ACTION_BROADCAST);
        broadcastIntent.putExtra("mesaj" , String.valueOf((int)((currentTime-mStartTime)/1000)));
        getApplicationContext().sendBroadcast(broadcastIntent);
        }

    };

    public class MiniServiceBinder extends Binder {
    }


    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "on Create çağırıldı");
        mTimer = new Timer();
        mStartTime = System.currentTimeMillis();
    }



    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy çağırıldı");
        mTimer.cancel();

        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart çağırıldı");
        mTimer.scheduleAtFixedRate(mtimerTask,0,1000);
    }
}
