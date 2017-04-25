package com.algonquincollege.njol0004.finalproject;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class LivingRoomLamp1 extends AppCompatActivity {

    ToggleButton tgg_lamp1;
    ImageView img_lamp1;
    Drawable img_lamp1_on;
    Drawable img_lamp1_off;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_room_lamp1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.mymenu);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartHome(view);
            }
        });

        tgg_lamp1 = (ToggleButton)findViewById(R.id.id_tgg_lamp1);
        img_lamp1 = (ImageView)findViewById(R.id.id_img_lamp1);
        img_lamp1_on = getResources().getDrawable(R.drawable.livingroom_lamp1_on,null);
        img_lamp1_off = getResources().getDrawable(R.drawable.livingroom_lamp1_off,null);

        tgg_lamp1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SwitchLamp(buttonView, isChecked);
            }
        });
    }

    public void StartHome(View view){
        startActivity(new Intent(getApplicationContext(), LivingRoomActivity.class));
    }

    public void SwitchLamp(CompoundButton buttonView, boolean isChecked){

        img_lamp1.setBackground(isChecked ? img_lamp1_off: img_lamp1_on);
    }
}
