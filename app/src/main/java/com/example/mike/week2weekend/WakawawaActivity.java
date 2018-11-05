package com.example.mike.week2weekend;

import android.annotation.SuppressLint;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.Random;

public class WakawawaActivity extends MenuActivity {

    private Button btnWaka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setInclude(R.layout.activity_wakawaka);

        btnWaka = findViewById( R.id.btnWaka );
    }

    private void fart(){
        try {
            AssetFileDescriptor afd = getAssets().openFd("fart-01.wav");
            MediaPlayer player = new MediaPlayer();
            player.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            player.prepare();
            player.start();
        } catch (IOException e) {
            Log.d("__TAG__", "ERROR+"+e.getMessage());
        }
    }

    public void btnWaka(View view) {
        btnWaka.setBackgroundColor(R.color.black);
    }
}
