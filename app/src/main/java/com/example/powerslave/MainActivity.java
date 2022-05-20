package com.example.powerslave;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //TODO: Settings
    //TODO: Music
    //TODO: More Pics

    //Main button on main activity
    Button buttonStart;
    Button buttonLoad;
    Button buttonAbout;
    Button buttonExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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