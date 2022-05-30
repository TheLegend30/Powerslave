package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.R;
import com.example.powerslave.government.City;
import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

import java.util.ArrayList;

public class MinistryOfEconomy extends Ministry {

    private long population;
    private long area;
    private float density;

    private long gdp;
    private float gdp_per_person;
    private long debt;
    private float inflation;
    private String currency;
    private ArrayList<City> cities;

    private Country country;

    private float developmentIndex;

    public MinistryOfEconomy(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context);
        this.name = "Ministry of Economy and Finances";
        this.country = country;
        this.area = context.getResources().getIntArray(R.array.area)[countryKey];
        this.population = context.getResources().getIntArray(R.array.population)[countryKey];
        this.density = (float) population / area;

        this.gdp = context.getResources().getIntArray(R.array.gdp)[countryKey] * 10L;
        this.gdp_per_person = ((float) gdp / population);
        this.debt = (long) (gdp * (Float.parseFloat(context.getResources().getStringArray(R.array.debt)[countryKey]) / 100));

        this.inflation = Float.parseFloat(context.getResources().getStringArray(R.array.inflation)[countryKey]);
        this.currency = context.getResources().getStringArray(R.array.currency)[countryKey];

        this.developmentIndex = (country.getMinistryOfHealthcare().getLife_expectancy() * country.getMinistryOfEducation().getLiteracy() * gdp_per_person) / 1000;
    }

    public long getGdp() {
        return gdp;
    }

    public void setGdp(long gdp) {
        this.gdp = gdp;
    }

    public String getCurrency() {
        return country.getAdjective() + " " + currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getDevelopmentIndex() {
        return developmentIndex;
    }

    public String getDevelopmentIndexString() {
        float ave_di = 0f;
        String devString = "(Middle)";
        for (Country country : Country.countries) {
            ave_di += country.getMinistryOfEconomy().developmentIndex;
        }
        ave_di /= Country.countries.size();
        if (developmentIndex <= ave_di) {
            if (developmentIndex <= ave_di - (ave_di * 0.75)) {
                devString = "(Very Low)";
            } else if (developmentIndex <= ave_di - (ave_di * 0.5)) {
                devString = "(Low)";
            }
        } else {
            if (developmentIndex >= ave_di + (ave_di * 0.75)) {
                devString = "(Very High)";
            } else if (developmentIndex >= ave_di + (ave_di * 0.5)) {
                devString = "(High)";
            }
        }
        return String.format("%.2f", developmentIndex) + " " + devString;
    }

    public void setDevelopmentIndex(float developmentIndex) {
        this.developmentIndex = developmentIndex;
    }

    @Override
    public String toString() {
        String string;
        string = "Area: " + area + " km^2" + "\n";
        string += "Population: " + population + "\n";
        string += "Density: " + String.format("%.2f", density) + "/km^2" + "\n";
        string += "GDP: " + gdp + " ƒ" + "\n";
        string += "GDP per Capita: " + String.format("%.2f", gdp_per_person) + " ƒ" + "\n";
        string += "National Debt: " + debt + " ƒ " + String.format("(%.2f", (float) debt / gdp * 100).concat(" %)") + "\n";
        string += "Inflation: " + String.format("%.2f", inflation) + " %" + "\n";
        string += "Currency: " + country.getAdjective() + " " + currency + "\n";
        string += "Development Index: " + getDevelopmentIndexString() + "\n";
        return super.toString() + "\n" + string;
    }
}
