package com.algonquincollege.njol0004.finalproject;

import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Radio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
    }
}

