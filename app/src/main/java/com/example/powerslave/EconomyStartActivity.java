package com.example.powerslave;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class EconomyStartActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton backArrowEconomyStart;
    private TextView textEconomyStart;
    private MainActivity.Country country = MainActivity.countries.get(StartActivity.countrySpinnerStart.getSelectedItemPosition());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_economy_start);

        backArrowEconomyStart = findViewById(R.id.backArrowEconomyStart);
        textEconomyStart = findViewById(R.id.textEconomyStart);

        textEconomyStart.setText(country.getEconomy().toString());

        backArrowEconomyStart.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == backArrowEconomyStart.getId()) {
            EconomyStartActivity.this.finish();
        }
    }

    @Override
    public void onBackPressed() {
        onClick(findViewById(R.id.backArrowEconomyStart));
    }

}