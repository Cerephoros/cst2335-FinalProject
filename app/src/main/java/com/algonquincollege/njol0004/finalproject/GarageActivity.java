package com.algonquincollege.njol0004.finalproject;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * This class has two buttons included where the user can either open or close the garage door,
 * or turn on or off the garage lights.
 */
public class GarageActivity extends AppCompatActivity {

    protected static ProgressBar progressBar;
    protected int progress;
    protected Toolbar toolBar;
    protected ToggleButton toggleDoor;
    protected ToggleButton toggleLight;
    protected TextView text;
    protected Context context;
    protected Handler handler;
    private String msg = "";
    private boolean isDoorOpen = false;
    private boolean isLightOn = false;

    /**
     * onCreate() function that launches the GarageActivity class with Toolbar functionality.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage);
        context = this;

        handler = new Handler();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabGarage);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GarageActivity.this, HouseActivity.class));
            }
        });

        progressBar.setProgress(progress);
        toolBar = (Toolbar) findViewById(R.id.toolbar);
        this.setSupportActionBar(toolBar);

        progressBar = (ProgressBar) findViewById(R.id.pBarGarage);
        progressBar.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progress < 100) {
                    progress += 1;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progress);
                        }
                    });
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        toggleDoor = (ToggleButton) findViewById(R.id.toggleDoor);
        if (!isDoorOpen) {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                    msg.equals("") ? "Garage Door Open" : msg, Snackbar.LENGTH_SHORT);
            snackbar.show();
            toggleDoor.setTextOn("ON");
        } else {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                    msg.equals("") ? "Garage Door Closed" : msg, Snackbar.LENGTH_SHORT);
            snackbar.show();
            toggleDoor.setTextOff("OFF");
        }

        toggleLight = (ToggleButton) findViewById(R.id.toggleLight);
        if (!isLightOn) {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                    msg.equals("") ? "Garage Light On" : msg, Snackbar.LENGTH_SHORT);
            snackbar.show();
            toggleLight.setTextOn("ON");
        } else {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                    msg.equals("") ? "Garage Light Off" : msg, Snackbar.LENGTH_SHORT);
            snackbar.show();
            toggleLight.setTextOff("OFF");
        }
    }
}
