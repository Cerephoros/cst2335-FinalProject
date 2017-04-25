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
import android.widget.SeekBar;
import android.widget.ToggleButton;

public class LivingRoomLamp3 extends AppCompatActivity {

    ToggleButton tgg_lamp3;
    ImageView img_lamp3, img_lamp_a;
    Drawable img_lamp3_on, img_lamp3_off, img_lamp_a_yellow, img_lamp_a_red, img_lamp_a_green;
    SeekBar slider_lamp3;
    Button btn_lamp3_yellow, btn_lamp3_red, btn_lamp3_green;

    public float dimmer = 1f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_room_lamp3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.mymenu);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartHome(view);
            }
        });
        tgg_lamp3 = (ToggleButton)findViewById(R.id.id_tgg_lamp3);
        img_lamp3 = (ImageView)findViewById(R.id.id_img_lamp3);
        img_lamp_a = (ImageView)findViewById(R.id.id_img_lamp_a);
        img_lamp_a_yellow = getResources().getDrawable(R.drawable.livingroom_lamp_a_yellow,null);
        img_lamp_a_red = getResources().getDrawable(R.drawable.livingroom_lamp_a_red,null);
        img_lamp_a_green = getResources().getDrawable(R.drawable.livingroom_lamp_a_green,null);
        img_lamp3_on = getResources().getDrawable(R.drawable.livingroom_lamp1_on,null);
        img_lamp3_off = getResources().getDrawable(R.drawable.livingroom_lamp1_off,null);
        slider_lamp3 = (SeekBar)findViewById( R.id.id_slider_lamp3 );
        btn_lamp3_yellow = (Button)findViewById(R.id.id_btn_lamp3_yellow);
        btn_lamp3_red= (Button)findViewById(R.id.id_btn_lamp3_red);
        btn_lamp3_green = (Button)findViewById(R.id.id_btn_lamp3_green);

        tgg_lamp3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                slider_lamp3.setProgress( isChecked ? 100 : 100 );

                SwitchLamp(buttonView, isChecked);
            }
        });

        slider_lamp3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(!tgg_lamp3.isChecked()) {
                    dimmer = i*0.01f;
                    if(dimmer > 0.2f)
                        img_lamp3.setAlpha(dimmer);

                    if(dimmer < 0.7f)
                        img_lamp_a.setAlpha(dimmer);
                }else {
                    img_lamp3.setAlpha(1f);
                }
            }
            public void onStartTrackingTouch( SeekBar seekBar ){

            }
            public void  onStopTrackingTouch( SeekBar seekBar ){

            }
        });

        btn_lamp3_yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!tgg_lamp3.isChecked()){
                    img_lamp_a.setBackground(img_lamp_a_yellow);
                }
            }
        });

        btn_lamp3_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!tgg_lamp3.isChecked()){
                    img_lamp_a.setBackground(img_lamp_a_red);
                }
            }
        });

        btn_lamp3_green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!tgg_lamp3.isChecked()){
                    img_lamp_a.setBackground(img_lamp_a_green);
                }
            }
        });
    }

    public void StartHome(View view){
        startActivity(new Intent(getApplicationContext(), LivingRoomActivity.class));
    }

    public void SwitchLamp(CompoundButton buttonView, boolean isChecked){

        img_lamp3.setBackground(isChecked ? img_lamp3_off: img_lamp3_on);
    }
}
