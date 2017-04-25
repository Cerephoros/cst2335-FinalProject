package com.algonquincollege.njol0004.finalproject;

import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.widget.TextView;

public class LightsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lights);
        //buttons to control lights
        TextView normal = (TextView) findViewById(R.id.button2);
        TextView high = (TextView) findViewById(R.id.button4);
        TextView off = (TextView) findViewById(R.id.button3);
    }
}