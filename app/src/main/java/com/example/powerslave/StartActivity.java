package com.example.powerslave;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    //Buttons in StartActivity
    ImageButton backArrowStart;
    Spinner countrySpinnerStart;
    ImageView flagViewStart;
    TextView textCapitalStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //Assigning values
        backArrowStart = findViewById(R.id.backArrowStart);
        flagViewStart = findViewById(R.id.flagViewStart);
        countrySpinnerStart = (Spinner) findViewById(R.id.countrySpinnerStart);
        textCapitalStart = findViewById(R.id.textCapitalStart);

        //OnClick methods
        backArrowStart.setOnClickListener(this);

        ArrayAdapter countryAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, MainActivity.countries);

        countrySpinnerStart.setAdapter(countryAdapter);
        countrySpinnerStart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 MainActivity.Country country = (MainActivity.Country) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        countrySpinnerStart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                MainActivity.Country country = MainActivity.countries.get(i);

                flagViewStart.setImageResource(country.getFlagSrc());
                textCapitalStart.setText("Capital: " + country.getCapitalName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == backArrowStart.getId()) {
            StartActivity.this.finish();
        }
    }
}