package com.example.powerslave;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    //Buttons in StartActivity
    ImageView backArrowStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //Assigning values
        backArrowStart = findViewById(R.id.backArrowStart);

        //OnClick methods
        backArrowStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == backArrowStart.getId()) {
            super.finish();
        }
    }
}