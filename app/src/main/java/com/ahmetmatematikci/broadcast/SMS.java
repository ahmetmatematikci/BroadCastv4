package com.ahmetmatematikci.broadcast;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Toast;

public class SMS extends Activity {

    protected static final String SMS_YAKALANDI= "android.provider.Telephony.SMS_RECEIVED";

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(SMS_YAKALANDI)) {
                Bundle extras = intent.getExtras();

                if (extras != null) {
                    Object[] pdus =(Object[]) extras.get("pdus");
                    SmsMessage[] messages= new SmsMessage[pdus.length];

                    for (int i =pdus.length; --i >=0;) {
                        messages[i] = SmsMessage.createFromPdu(
                                (byte[])pdus[i]);
                         }
                    for (SmsMessage message : messages) {
                        Toast.makeText(SMS.this, "Yeni SMS geldi" + "(" + message.getOriginatingAddress() + ")" +
                                ")\n" + message.getMessageBody(), Toast.LENGTH_LONG).show();

                    }
                }
            }
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

    public void onBClick(View view) {
        switch (view.getId()){
            case R.id.sms:


                //SmsManager smsManager = SmsManager.getDefault();

                //Intent smsAt = new Intent(Intent.ACTION_VIEW);
               // PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 123, smsAt, 0);
                //smsManager.sendTextMessage("+901234567", null, "Kendi kendien sms atıldı", pendingIntent, pendingIntent);

                String telNo = "5321111111";
                String mesaj = "Bur bir sms dir";
                Intent msjGonder = new Intent(Intent.ACTION_VIEW);
                msjGonder.setData(Uri.parse("sms:" + telNo));
                msjGonder.putExtra("sms_body" , mesaj);
                startActivity(msjGonder);

                break;
        }
    }
}
