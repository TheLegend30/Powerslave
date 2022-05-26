package com.example.powerslave;

import androidx.annotation.AnyRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.InputStream;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    //Buttons in StartActivity
    private ImageButton backArrowStart;
    private ImageButton forwardArrowStart;
    private ImageButton economyButtonStart;
    private ImageButton militaryButtonStart;
    public static Spinner countrySpinnerStart;
    private ImageView flagViewStart;
    private TextView textCapitalStart;
    private TextView textGovernmentStart;
    private TextView textLocationStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //Assigning values
        backArrowStart = findViewById(R.id.backArrowStart);
        forwardArrowStart = findViewById(R.id.forwardArrowStart);
        economyButtonStart = findViewById(R.id.economyButtonStart);
        militaryButtonStart = findViewById(R.id.militaryButtonStart);
        flagViewStart = findViewById(R.id.flagViewStart);
        countrySpinnerStart = (Spinner) findViewById(R.id.countrySpinnerStart);
        textCapitalStart = findViewById(R.id.textCapitalStart);
        textGovernmentStart = findViewById(R.id.textGovernmentStart);
        textLocationStart = findViewById(R.id.textLocationStart);

        //OnClick methods
        backArrowStart.setOnClickListener(this);
        forwardArrowStart.setOnClickListener(this);
        economyButtonStart.setOnClickListener(this);
        militaryButtonStart.setOnClickListener(this);

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

                flagViewStart.setImageURI(country.getFlagSrc());
                textCapitalStart.setText("Capital: " + country.getCapitalName());
                textGovernmentStart.setText("Government: " + country.getRegionalForm() + " " + country.getGovernmentType());
                textLocationStart.setText("Location: " + country.getContinent());
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
        } else if (view.getId() == forwardArrowStart.getId()) {
            Intent intent = new Intent(StartActivity.this, ChooseRulerActivity.class);
            startActivity(intent);
        } else if (view.getId() == economyButtonStart.getId()) {
            Intent intent = new Intent(StartActivity.this, EconomyStartActivity.class);
            startActivity(intent);
        } else if (view.getId() == militaryButtonStart.getId()) {
            Intent intent = new Intent(StartActivity.this, MilitaryStartActivity.class);
            startActivity(intent);
        }
    }



}