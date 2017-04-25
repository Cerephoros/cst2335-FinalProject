package com.algonquincollege.njol0004.finalproject;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.ToggleButton;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class LivingRoomLamp2 extends AppCompatActivity {

    ToggleButton tgg_lamp2;
    ImageView img_lamp2;
    Drawable img_lamp2_on;
    Drawable img_lamp2_off;
    SeekBar slider_lamp2;
    public float dimmer = 1f;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_room_lamp2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.mymenu);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartHome(view);
            }
        });

        tgg_lamp2 = (ToggleButton) findViewById(R.id.id_tgg_lamp2);
        img_lamp2 = (ImageView) findViewById(R.id.id_img_lamp2);
        img_lamp2_on = getResources().getDrawable(R.drawable.livingroom_lamp1_on, null);
        img_lamp2_off = getResources().getDrawable(R.drawable.livingroom_lamp1_off, null);
        slider_lamp2 = (SeekBar) findViewById(R.id.id_slider_lamp2);

        tgg_lamp2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                slider_lamp2.setProgress(isChecked ? 100 : 100);

                SwitchLamp(buttonView, isChecked);
            }
        });

        slider_lamp2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (!tgg_lamp2.isChecked()) {
                    dimmer = i * 0.01f;
                    if (dimmer > 0.2f)
                        img_lamp2.setAlpha(dimmer);
                } else {
                    img_lamp2.setAlpha(1f);
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void StartHome(View view) {
        startActivity(new Intent(getApplicationContext(), LivingRoomActivity.class));
    }

    public void SwitchLamp(CompoundButton buttonView, boolean isChecked) {

        img_lamp2.setBackground(isChecked ? img_lamp2_off : img_lamp2_on);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("LivingRoomLamp2 Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
