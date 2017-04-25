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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * This class has the functionality of controlling indoor temperature. The user has the option of
 * adding a specific time with temperature set they want to add, remove or update in their preset.
 */
public class TempControlActivity extends AppCompatActivity {

    protected static ProgressBar progressBar;
    protected int progress;
    protected Toolbar toolBar;
    protected TextView text;
    protected Context context;
    protected Handler handler;
    protected Button addBtn;
    protected Button removeBtn;
    protected Button updateBtn;
    protected EditText editTime;
    protected EditText editTemp;
    private String msg = "";

    /**
     * onCreate() function that launches the TempControlActivity class with Toolbar functionality.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_control);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabTemp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TempControlActivity.this, HouseActivity.class));
            }
        });

        context = this;
        handler = new Handler();

        progress=0;
        toolBar = (Toolbar) findViewById(R.id.toolbar);
        this.setSupportActionBar(toolBar);

        editTime=(EditText)findViewById(R.id.editTextTime);
        editTemp=(EditText)findViewById(R.id.editTextTemp);

        progressBar = (ProgressBar) findViewById(R.id.pBarTemp);
        progressBar.setProgress(progress);
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

        addBtn = (Button) findViewById(R.id.addButton);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                        msg.equals("") ? "Temperature settings have been added" : msg,
                        Snackbar.LENGTH_SHORT);
                snackbar.show();
                editTime.setText("");
                editTemp.setText("");
            }
        });
        removeBtn = (Button) findViewById(R.id.removeButtton);
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                        msg.equals("") ? "Temperature settings have been removed" : msg,
                        Snackbar.LENGTH_SHORT);
                snackbar.show();
                editTime.setText("");
                editTemp.setText("");
            }
        });
        updateBtn = (Button) findViewById(R.id.updateButton);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                        msg.equals("") ? "Temperature settings have been updated" : msg,
                        Snackbar.LENGTH_SHORT);
                snackbar.show();
                editTime.setText("");
                editTemp.setText("");
            }
        });
    }
}
