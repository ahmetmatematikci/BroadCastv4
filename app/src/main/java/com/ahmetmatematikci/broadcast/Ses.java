package com.ahmetmatematikci.broadcast;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

/**
 * Created by a on 2/3/17.
 */

public class Ses extends Activity {
    /*

    private final int[] mSesId = new int[] {
            R.raw.ses, R.raw.kus
    };

    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ses);
    }

    public void onSesClick(View view) {
        int selectedId;
        switch (view.getId()) {
            case R.id.sesmp:
                final Spinner spinner = (Spinner)findViewById(R.id.sp_sesmp);
                selectedId = (int)spinner.getSelectedItemId();

                if (selectedId >=0) {
                    try {
                        mediaPlayer = MediaPlayer.create(this, mSesId[selectedId]);
                        mediaPlayer.start();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                break;
        }
    }

    */

    private final int[] mSesId = new int[] {
            R.raw.ses, R.raw.kus
    };

    private AudioManager maudioManager;
    private int mStreamVolume;
    private SoundPool mSounPool;
    private int[] mSoundPoolIds = new int[mSesId.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ses);
        maudioManager= (AudioManager)getSystemService(AUDIO_SERVICE);
        mStreamVolume = maudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        mSounPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        for (int i = mSesId.length; --i>=0;) {
            mSoundPoolIds[i] = mSounPool.load(this, mSesId[i], 1);
        }
    }

    public void onSesClick(View view) {
        int selectedId;
        switch (view.getId()) {
            case R.id.sesmp:
                final Spinner spinner = (Spinner) findViewById(R.id.sp_sesmp);
                selectedId = (int) spinner.getSelectedItemId();

                if (selectedId >= 0) {

                    mSounPool.play(
                            mSoundPoolIds[selectedId],mStreamVolume,mStreamVolume,1,0,1);

                    break;
                }


        }
    }

}
