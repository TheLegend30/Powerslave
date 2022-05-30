package com.example.powerslave;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.powerslave.government.Country;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //TODO: Add ChooseRulerActivity and finish it
    //TODO: Settings + Finish Music
    //TODO: More Pics
    //TODO: Military indexes
    //TODO: Better descriptions
    //TODO: Rework economic indexes

    //Main button on main activity
    private Button buttonStart;
    private Button buttonLoad;
    private Button buttonAbout;
    private Button buttonExit;

    public MediaPlayer mediaPlayer;

    public MainActivity mainActivity = MainActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startMusic();

        //Assigning values to buttons
        buttonStart = findViewById(R.id.buttonStart);
        buttonLoad = findViewById(R.id.buttonLoad);
        buttonAbout = findViewById(R.id.buttonAbout);
        buttonExit = findViewById(R.id.buttonExit);

        //Set onClick method to each of buttons
        buttonStart.setOnClickListener(this);
        buttonLoad.setOnClickListener(this);
        buttonAbout.setOnClickListener(this);
        buttonExit.setOnClickListener(this);

        initCountries();
    }

    private void startMusic() {
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.bossantigua);
        mediaPlayer.setLooping(true);
        //mediaPlayer.start();
    }

    private void initCountries() {
        int length = getResources().getStringArray(R.array.countries).length;
        for (int i = 0; i < length; i++) {
            Country.countries.add(new Country(i, MainActivity.this));
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == buttonStart.getId()) {
            Intent intent = new Intent(MainActivity.this, StartActivity.class);
            startActivity(intent);
        } else if (view.getId() == buttonLoad.getId()) {
        } else if (view.getId() == buttonAbout.getId()) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        } else if (view.getId() == buttonExit.getId()) {
            //Rudimentary function in my opinion. Android player can always freely close the app.
            super.finishAffinity();
        }
    }
}
