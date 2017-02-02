package com.ahmetmatematikci.broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Services extends Activity {
    Button bt1, bt2;

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            Toast.makeText(Services.this, bundle.getString("mesaj"), Toast.LENGTH_SHORT).show();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arkaplan_services);
    }

    public void onButtonClick(View view) {

        bt1 =(Button)findViewById(R.id.service_baslat);
        bt2 =(Button)findViewById(R.id.service_durdur);

        if (view == bt1) {
            startService(new Intent(this, MiniService.class));
            bt1.setEnabled(false);
            bt2.setEnabled(true);
        } else {
            stopService(new Intent(this, MiniService.class));
            bt1.setEnabled(true);
            bt2.setEnabled(false);

        }
    }

    @Override
    protected void onPause() {
        getApplicationContext().unregisterReceiver(mReceiver);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getApplicationContext().registerReceiver(mReceiver, new IntentFilter(MiniService.ACTION_BROADCAST));
    }
}
