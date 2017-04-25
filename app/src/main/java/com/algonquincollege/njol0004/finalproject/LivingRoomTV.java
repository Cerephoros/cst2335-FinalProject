package com.algonquincollege.njol0004.finalproject;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class LivingRoomTV extends AppCompatActivity {

    ToggleButton tgg_tv_pad_on;
    ImageView img_tv_screen;
    Drawable img_tv_on, img_tv_off, ref_img_channel_actual, img_tv_channel1, img_tv_channel2, img_tv_channel3, img_tv_channel4;
    Button btn_pad_up, btn_pad_down;
    int channel = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_room_tv);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.mymenu);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartHome(view);
            }
        });

        tgg_tv_pad_on = (ToggleButton)findViewById(R.id.id_img_pad_on);
        img_tv_screen = (ImageView)findViewById(R.id.id_img_tv_screen);
        img_tv_on = getResources().getDrawable(R.drawable.livingroom_tv_on,null);
        img_tv_off = getResources().getDrawable(R.drawable.livingroom_tv_off,null);
        img_tv_channel1 = getResources().getDrawable(R.drawable.livingroom_tv_channel1,null);
        img_tv_channel2 = getResources().getDrawable(R.drawable.livingroom_tv_channel2,null);
        img_tv_channel3 = getResources().getDrawable(R.drawable.livingroom_tv_channel3,null);
        img_tv_channel4 = getResources().getDrawable(R.drawable.livingroom_tv_channel4,null);
        btn_pad_up = (Button)findViewById(R.id.id_btn_pad_up);
        btn_pad_down = (Button)findViewById(R.id.id_btn_pad_down);

        tgg_tv_pad_on.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                SwitchTv(buttonView, isChecked);
            }
        });

        btn_pad_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!tgg_tv_pad_on.isChecked()) {
                    if(channel <= 4){
                        channel++;
                        Channels(v, channel);
                    }else {
                        channel = 4;
                    }
                }
            }
        });

        btn_pad_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!tgg_tv_pad_on.isChecked()) {
                    if(channel >= 1){
                        channel--;
                        Channels(v, channel);
                    }else {
                        channel = 1;
                    }
                }
            }
        });
    }

    public void StartHome(View view){
        startActivity(new Intent(getApplicationContext(), LivingRoomActivity.class));
    }

    public void SwitchTv(CompoundButton buttonView, boolean isChecked){

        img_tv_screen.setBackground(isChecked ? img_tv_off: img_tv_on);
    }

    public  void Channels(View view, int ch){
        switch (ch) {
            case 1:
                img_tv_screen.setBackground(img_tv_channel1);
                img_tv_on = img_tv_channel1;
                break;
            case 2:
                img_tv_screen.setBackground(img_tv_channel2);
                img_tv_on = img_tv_channel2;
                break;
            case 3:
                img_tv_screen.setBackground(img_tv_channel3);
                img_tv_on = img_tv_channel3;
                break;
            case 4:
                img_tv_screen.setBackground(img_tv_channel4);
                img_tv_on = img_tv_channel4;
                break;
        }
    }
}
