package com.algonquincollege.njol0004.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

/**
 * This class has all information pertained to the HouseActivity where it contains a ListView of
 * sub-activities such as Garage, House Temperature and Outside Weather within the House Activity.
 */
public class HouseActivity extends AppCompatActivity {

    protected Context context;
    protected static ProgressBar progressBar;
    protected int progress;
    protected Toolbar toolBar;
    protected Handler handler;

    /**
     * Gets a String of activities in an ArrayList.
     * @return
     */
    private ArrayList<String> retrieveData() {
        ArrayList<String> data = new ArrayList<>();
        data.add("Garage");
        data.add("House Temperature");
        data.add("Outside Weather");
        data.add("About");
        return data;
    }

    /**
     * onCreate() function that launches the HouseActivity class and displays each sub-activity with
     * Toolbar functionality.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);
        context = this;

        handler = new Handler();
        progress=0;


        toolBar = (Toolbar) findViewById(R.id.toolbar);
        this.setSupportActionBar(toolBar);

        progressBar = (ProgressBar) findViewById(R.id.housePBar);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(progress);
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

        ListView houseLV = (ListView) findViewById(R.id.HouseListView);
        houseLV.setAdapter(new ArrayAdapter<>(HouseActivity.this, android.R.layout.simple_list_item_1, retrieveData()));
        houseLV.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: Intent intentGarage = new Intent(HouseActivity.this, GarageActivity.class);
                        startActivityForResult(intentGarage, 0);
                        break;
                    case 1: Intent intentTemp = new Intent(HouseActivity.this, TempControlActivity.class);
                        startActivityForResult(intentTemp, 1);
                        break;
                    case 2: Intent intentWeather = new Intent(HouseActivity.this, WeatherForecast.class);
                        startActivityForResult(intentWeather, 2);
                        break;
                    case 3: AlertDialog.Builder categoryBuilder = new AlertDialog.Builder(context);
                        categoryBuilder.setTitle("About");
                        LayoutInflater inflater = getLayoutInflater();
                        View viewDialog = inflater.inflate(R.layout.house_help_dialog, null);
                        categoryBuilder.setView(viewDialog);
                        categoryBuilder.setPositiveButton(R.string.OKButton, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }); categoryBuilder.show();
                }
            }
        });
    }
}
//      }