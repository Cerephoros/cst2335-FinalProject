package com.algonquincollege.njol0004.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AutoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto);

        MainMenuFragment mainMenu = new MainMenuFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.activity_auto, mainMenu).commit();
    }
}
