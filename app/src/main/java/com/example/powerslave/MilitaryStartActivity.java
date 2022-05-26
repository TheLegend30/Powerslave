package com.example.powerslave;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MilitaryStartActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textViewMilitaryStart;
    ImageButton backArrowMilitaryStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_military_start);

        textViewMilitaryStart = findViewById(R.id.textViewMilitaryStart);
        backArrowMilitaryStart = findViewById(R.id.backArrowMilitaryStart);

        textViewMilitaryStart.setText(MainActivity.countries.get(StartActivity.countrySpinnerStart.getSelectedItemPosition()).getMilitary().toString());

        backArrowMilitaryStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == backArrowMilitaryStart.getId()) {
            MilitaryStartActivity.this.finish();
        }
    }
}