package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.R;
import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

public class MinistryOfEconomy extends Ministry {

    private long population;
    private long area;
    private float density;

    private long gdp;
    private float gdp_per_person;
    private long debt;

    private float inflation;
    private String currency;
    private float currency_to_gulden;
    private float interest_rate;

    private float gdp_increase;

    private long labor_force;
    private final long unemployment;
    private long poverty;

    private long exports;
    private long taxes;
    private long tariffs;

    private long imports;

    private long budget;

    private float developmentIndex;

    public MinistryOfEconomy(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);
        this.name = "Ministry of Economy and Finances";
        this.country = country;
        this.area = context.getResources().getIntArray(R.array.area)[countryKey];
        this.population = context.getResources().getIntArray(R.array.population)[countryKey];
        this.density = (float) population / area;

        this.gdp = context.getResources().getIntArray(R.array.gdp)[countryKey] * 100L;
        this.gdp_per_person = ((float) gdp / population);
        this.debt = (long) (gdp * (Float.parseFloat(context.getResources().getStringArray(R.array.debt)[countryKey]) / 100));

        this.inflation = Float.parseFloat(context.getResources().getStringArray(R.array.inflation)[countryKey]);
        this.currency = context.getResources().getStringArray(R.array.currency)[countryKey];
        this.currency_to_gulden = Float.parseFloat(context.getResources().getStringArray(R.array.currency_to_gulden)[countryKey]);
        this.interest_rate = Float.parseFloat(context.getResources().getStringArray(R.array.interest_rate)[countryKey]);

        this.labor_force = (long) (population * (Float.parseFloat(context.getResources().getStringArray(R.array.labor_force)[countryKey]) / 100));
        this.unemployment = labor_force;
        this.poverty = population;

        this.exports = 9000000;
        this.taxes = 9000000;
        this.tariffs = 9000000;

        this.imports = 9000000;

        this.budget = exports + taxes + tariffs - imports;

        this.gdp_increase = Float.parseFloat(context.getResources().getStringArray(R.array.gdp_increase)[countryKey]);

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

    public long getLabor_force() {
        return labor_force;
    }

    public void setLabor_force(long labor_force) {
        this.labor_force = labor_force;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
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

    public float getGdp_per_person() {
        return gdp_per_person;
    }

    public void setGdp_per_person(float gdp_per_person) {
        this.gdp_per_person = gdp_per_person;
    }

    public float getCurrency_to_gulden() {
        return currency_to_gulden;
    }

    public void setCurrency_to_gulden(float currency_to_gulden) {
        this.currency_to_gulden = currency_to_gulden;
    }

    @Override
    public String toString() {
        String string;
        string = "Area: " + area + " km^2" + "\n";
        string += "Population: " + population + "\n";
        string += "Density: " + String.format("%.2f", density) + "/km^2" + "\n";
        string += "GDP: " + gdp + " ƒ" + "\n";
        string += "GDP per Capita: " + String.format("%.2f", gdp_per_person) + " ƒ" + " (" + (gdp_per_person / currency_to_gulden) + " " + currency + ")" + "\n";
        string += "National Debt: " + debt + " ƒ " + String.format("(%.2f", (float) debt / gdp * 100).concat(" %)") + "\n";
        string += "Inflation: " + String.format("%.2f", inflation) + " %" + "\n";
        string += "Currency: " + country.getAdjective() + " " + currency + "\n";
        string += "1 " + currency + " is " + String.format("%.5f", currency_to_gulden) + " of Golden Florin (ƒ)" + "\n";
        string += "Interest Rate: " + interest_rate + " %" + "\n";
        string += "GDP Increase from previous year: " + gdp_increase + " %" + "\n";
        string += "Labor Force: " + labor_force + " (" + String.format("%.1f", (float) labor_force / population * 100.0) + " %)" + "\n";
        string += "Unemployment Rate: " + unemployment + " (" + String.format("%.1f", (float) unemployment / population * 100.0) + " %)" + "\n";
        string += "Poverty Rate: " + poverty + " (" + String.format("%.1f", (float) poverty / population * 100.0) + " %)" + "\n";
        string += "Development Index: " + getDevelopmentIndexString() + "\n";
        return super.toString() + "\n" + string;
    }
}
