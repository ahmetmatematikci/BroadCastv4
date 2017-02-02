package com.ahmetmatematikci.broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by a on 2/1/17.
 */

public class BroadCast extends Activity {
    private static final String ACTION_BROADCAST = "Bu bir BROADCAST ";


    private BroadcastReceiver mbroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(BroadCast.this, "Broadcast yakalandı", Toast.LENGTH_SHORT).show();

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast);
    }

    public void onButtonClick(View view) {
        Intent i = new Intent(ACTION_BROADCAST);
        getApplicationContext().sendBroadcast(i);

    }

    @Override
    protected void onResume() {
        super.onResume();

        final IntentFilter intentFilter = new IntentFilter((ACTION_BROADCAST));
        getApplicationContext().registerReceiver(
                mbroadcastReceiver, intentFilter);
    }
}