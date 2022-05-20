package com.example.powerslave;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    //Creating necessary objects;
    ImageView backArrowAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        //Assigning values to objects
        backArrowAbout = findViewById(R.id.backArrowAbout);

        //Set onClick method to each of buttons
        backArrowAbout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == backArrowAbout.getId()) {

            AboutActivity.this.finish();
        }
    }
}