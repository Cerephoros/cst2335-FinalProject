package com.algonquincollege.njol0004.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class LivingRoomActivity extends AppCompatActivity {

    Button btn_lamp1, btn_lamp2, btn_lamp3, btn_tv, btn_window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_room);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_lamp1 = (Button)findViewById(R.id.id_btn_lamp1);
        btn_lamp2 = (Button)findViewById(R.id.id_btn_lamp2);
        btn_lamp3 = (Button)findViewById(R.id.id_btn_lamp3);
        btn_tv = (Button)findViewById(R.id.id_btn_tv);
        btn_window = (Button)findViewById(R.id.id_btn_window);

        btn_lamp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lamp1(v);
            }
        });

        btn_lamp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lamp2(v);
            }
        });

        btn_lamp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lamp3(v);
            }
        });

        btn_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tv(v);
            }
        });

        btn_window.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Blind(v);
            }
        });
    }

    public void Lamp1(View view){
        startActivity(new Intent(getApplicationContext(), LivingRoomLamp1.class));
    }

    public void Lamp2(View view){
        startActivity(new Intent(getApplicationContext(), LivingRoomLamp2.class));
    }

    public void Lamp3(View view){
        startActivity(new Intent(getApplicationContext(), LivingRoomLamp3.class));
    }

    public void Tv(View view){
        startActivity(new Intent(getApplicationContext(), LivingRoomTV.class));
    }

    public void Blind(View view){
        startActivity(new Intent(getApplicationContext(), LivingRoomBlind.class));
    }
}

