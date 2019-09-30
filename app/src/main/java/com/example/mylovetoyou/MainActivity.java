package com.example.mylovetoyou;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.KeyEvent;


public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaplayer = new MediaPlayer();
    private AudioManager mgr;
    private static final int FLAG_HOMEKEY_DISPATCHED = 0x80000000;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == event.KEYCODE_HOME){
            return false;
        }
        else return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaplayer.release();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().setFlags(FLAG_HOMEKEY_DISPATCHED,FLAG_HOMEKEY_DISPATCHED);

        mgr = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    mgr.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_RAISE,AudioManager.FLAG_SHOW_UI);
                }
            }
        }).start();

        mgr.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_RAISE,AudioManager.FLAG_SHOW_UI);

        mediaplayer = MediaPlayer.create(this,R.raw.test);
        mediaplayer.start();

        mediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaplayer.start();
                mediaplayer.setLooping(true);
            }
        });
    }
}
