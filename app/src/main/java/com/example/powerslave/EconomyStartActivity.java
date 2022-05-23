package com.example.powerslave;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class EconomyStartActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton backArrowEconomyStart;
    private TextView textAreaStart;
    private TextView textPopulationStart;
    private TextView textDensityStart;
    private TextView textGDPStart;
    private TextView textGDPPerPersonStart;
    private TextView textNationalDebtStart;
    private TextView textInflationStart;
    private TextView textCurrencyStart;
    private TextView textLifeExpectancyStart;
    private TextView textLiteracyStart;
    private TextView textLOLDStart;
    private MainActivity.Country country = MainActivity.countries.get(StartActivity.countrySpinnerStart.getSelectedItemPosition());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_economy_start);

        backArrowEconomyStart = findViewById(R.id.backArrowEconomyStart);
        textAreaStart = findViewById(R.id.textAreaStart);
        textPopulationStart = findViewById(R.id.textPopulationStart);
        textDensityStart = findViewById(R.id.textDensityStart);
        textGDPStart = findViewById(R.id.textGDPStart);
        textGDPPerPersonStart = findViewById(R.id.textGDPPerPersonStart);
        textNationalDebtStart = findViewById(R.id.textNationalDebtStart);
        textInflationStart = findViewById(R.id.textInflationStart);
        textCurrencyStart = findViewById(R.id.textCurrencyStart);
        textLifeExpectancyStart = findViewById(R.id.textLifeExpectancyStart);
        textLiteracyStart = findViewById(R.id.textLiteracyStart);
        textLOLDStart = findViewById(R.id.textLOLDStart);

        textAreaStart.setText("Area: " + country.getEconomy().getAreaString());
        textPopulationStart.setText("Population: " + country.getEconomy().getPopulation());
        textDensityStart.setText("Density: " + country.getEconomy().getDensityString());
        textGDPStart.setText("GDP: " + country.getEconomy().getGdpString());
        textGDPPerPersonStart.setText("GDP Per Capita: " + country.getEconomy().getGdp_per_person_String());
        textNationalDebtStart.setText("National Debt: " + country.getEconomy().getDebtString());
        textInflationStart.setText("Inflation: " + country.getEconomy().getInflationString());
        textCurrencyStart.setText("Currency: " + country.getEconomy().getCurrency());
        textLifeExpectancyStart.setText("Life Expectancy: " + country.getEconomy().getLife_expectancyString());
        textLiteracyStart.setText("Literacy: " + country.getEconomy().getLiteracyString());
        textLOLDStart.setText("Level of life development: " + country.getEconomy().getDevelopmentIndexString());


        backArrowEconomyStart.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == backArrowEconomyStart.getId()) {
            EconomyStartActivity.this.finish();
        }
    }
}