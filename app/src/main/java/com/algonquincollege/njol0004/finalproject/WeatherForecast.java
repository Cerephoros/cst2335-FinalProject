package com.algonquincollege.njol0004.finalproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class displays a weather app with minimum temperature, maximum temperature and the current
 * temperature. It also holds an ImageView to display what the weather looks like.
 */
public class WeatherForecast extends AppCompatActivity {
    ProgressBar progressBar;
    TextView maxView;
    TextView minView;
    TextView currentView;
    ImageView image;
    protected Toolbar toolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_app);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabWeather);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WeatherForecast.this, HouseActivity.class));
            }
        });

        toolBar = (Toolbar) findViewById(R.id.toolbar);
        this.setSupportActionBar(toolBar);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setMax(100);
        image = (ImageView) findViewById(R.id.imageView3);
        currentView = (TextView) findViewById(R.id.textView4);
        maxView = (TextView) findViewById(R.id.textView6);
        minView = (TextView) findViewById(R.id.textView5);

        (new ForecastQuery()).execute("");
    }

    public class ForecastQuery extends AsyncTask<String, Integer, String> {
        URL url = null;
        XmlPullParser parser;
        String currentTemp, minTemp, maxTemp, iconName;
        Bitmap bmp;
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=d99666875e0e51521f0040a3d97d0f6a&mode=xml&units=metric";

        @Override
        protected String doInBackground(String... params) {
            String link = "http://openweathermap.org/img/w/";
            HttpURLConnection connection = null;
            try {
                URL dataUrl = new URL(urlString);
                connection = (HttpURLConnection) dataUrl.openConnection();

                connection.setReadTimeout(10000);
                connection.setConnectTimeout(15000);
                connection.setRequestMethod("GET");
                connection.setDoInput(true);

                connection.connect();

                parser = Xml.newPullParser();
                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(connection.getInputStream(), null);

                int type = parser.getEventType();
                while (type != parser.END_DOCUMENT) {
                    if(parser.getEventType() == parser.START_TAG) {
                        String attribute = parser.getName();

                        if (attribute.equals("temperature")) {
                            minTemp = parser.getAttributeValue(null, "min");
                            publishProgress(25);
                            maxTemp = parser.getAttributeValue(null, "max");
                            publishProgress(50);
                            currentTemp = parser.getAttributeValue(null, "value");
                            publishProgress(75);
                        }

                        if (attribute.equals("weather")) {
                            iconName = parser.getAttributeValue(null, "icon");
                            Log.i("Looking for ", iconName + ".png");
                            if (fileExistance(iconName + ".png")){

                                FileInputStream fis = null;
                                try {
                                    fis = new FileInputStream(getBaseContext().getFileStreamPath(iconName + ".png"));
                                }
                                catch (FileNotFoundException e)
                                {
                                    e.printStackTrace();
                                }
                                bmp = BitmapFactory.decodeStream(fis);
                            } else {
                                Log.i("Downloading ", iconName + ".png");
                                url = new URL(link+iconName + ".png");
                                bmp = HttpUtils.getImage(url);
                                FileOutputStream outputStream = null;
                                outputStream = openFileOutput(iconName + ".png", Context.MODE_PRIVATE);
                                bmp.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
                                outputStream.flush();
                                outputStream.close();
                            }
                        }
                    }
                    parser.next();
                    type = parser.getEventType();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }

            return "";
        }
        protected void onPostExecute(String s){
            currentView.setText("Current Temperature: " + currentTemp + "°C");
            maxView.setText("Maximum Temperature: " + maxTemp + "°C");
            minView.setText("Minimum Temperature: " + minTemp + "°C");
            image.setImageBitmap(bmp);
            progressBar.setProgress(View.INVISIBLE);
        }
        protected void onProgressUpdate(Integer... value) {
            progressBar.setProgress(value[0]);
        }

        public boolean fileExistance(String fName){
            File f = getBaseContext().getFileStreamPath(fName);
            return f.exists();
        }


    }
}

class HttpUtils {
    public  static Bitmap getImage(URL url) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                return BitmapFactory.decodeStream(connection.getInputStream());
            } else
                return null;
        } catch (Exception e) {
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
