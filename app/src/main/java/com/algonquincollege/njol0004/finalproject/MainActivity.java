package com.algonquincollege.njol0004.finalproject ;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/**
 * This class is where the main point of execution gets launched inside the application where
 * it features a list of activities available for the user.
 */
public class MainActivity extends AppCompatActivity {

    private final String ACTIVITY_NAME = "MainActivity";
    private String msg = "";

    /**
     * onCreate function that launches the MainActivity class with Toolbar functionality.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(ACTIVITY_NAME, "Currently in onCreate()");
        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
    }

    /**
     * Creates and inflates the menu from the Menu XML file.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Shows the items available from the Menu fragment in the form of a toolbar. Each items
     * are clickable enabling the user to launch from one activity to another.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_auto:
                Snackbar snackBarA = Snackbar.make(findViewById(android.R.id.content),
                        (msg.equals("") ? "Auto selected" : msg),
                        Snackbar.LENGTH_LONG);
                snackBarA.show();
                startActivityForResult(new Intent(MainActivity.this, AutoActivity.class), 1);
                return true;
            case R.id.item_house:
                Snackbar snackBarH = Snackbar.make(findViewById(android.R.id.content),
                        (msg.equals("") ? "House Selected" : msg),
                        Snackbar.LENGTH_LONG);
                snackBarH.show();
                startActivityForResult(new Intent(MainActivity.this, HouseActivity.class), 2);
                return true;
            case R.id.item_living_room:
                Snackbar snackBarLR = Snackbar.make(findViewById(android.R.id.content),
                        (msg.equals("") ? "Living Room Selected" : msg),
                        Snackbar.LENGTH_LONG);
                snackBarLR.show();
                startActivityForResult(new Intent(MainActivity.this, LivingRoomActivity.class), 3);
                return true;
            case R.id.about:
                Toast toast = Toast.makeText(this, "Final Project v1.0 by Erik Njolstad, Sam Decker and Ali Akbari", Toast.LENGTH_LONG);
                toast.show();
                return true;
        }
        return false;
    }

    /**
     * onStart()
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "Currently in onStart()");
    }

    /**
     * onResume()
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "Currently in onResume()");
    }

    /**
     * onPause()
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "Currently in onPause()");
    }

    /**
     * onRestart()
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(ACTIVITY_NAME, "Currently in onRestart()");
    }

    /**
     * onStop()
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "Currently in onStop()");
    }

    /**
     * onDestroy()
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "Terminating the app...");
    }
}
