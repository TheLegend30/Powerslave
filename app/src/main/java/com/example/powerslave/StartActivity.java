package com.example.powerslave;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Random;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    //Buttons in StartActivity
    ImageView backArrowStart;
    ImageView randomRulerNameStart;

    EditText editTextNameStart;
    EditText editTextSurnameStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //Assigning values
        backArrowStart = findViewById(R.id.backArrowStart);
        randomRulerNameStart = findViewById(R.id.randomRulerNameStart);
        editTextNameStart = findViewById(R.id.editTextNameStart);
        editTextSurnameStart = findViewById(R.id.editTextSurnameStart);

        //OnClick methods
        backArrowStart.setOnClickListener(this);
        randomRulerNameStart.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == backArrowStart.getId()) {
            super.finish();
        } else if (view.getId() == randomRulerNameStart.getId()) {
            int lengthOfArray = getResources().getStringArray(R.array.eyut_names_m).length;
            int randNumber = new Random().nextInt(lengthOfArray);
            editTextNameStart.setText(getResources().getStringArray(R.array.eyut_names_m)[randNumber]);

            lengthOfArray = getResources().getStringArray(R.array.eyut_surnames).length;
            randNumber = new Random().nextInt(lengthOfArray);
            editTextSurnameStart.setText(getResources().getStringArray(R.array.eyut_surnames)[randNumber]);
        }
    }
}