package com.ahmetmatematikci.broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SMS extends Activity {

    protected static final String SMS_YAKALANDI= "android.provider.Telephony.SMS_RECEIVED";

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(SMS.this, "Yeni SMS geldi", Toast.LENGTH_SHORT).show();
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);



    }

    @Override
    protected void onPause() {
        getApplicationContext().unregisterReceiver(mReceiver);
        super.onPause();
    }


    @Override
    protected void onResume() {
        getApplicationContext().registerReceiver(mReceiver, new IntentFilter(SMS_YAKALANDI));
        super.onResume();
    }
}
