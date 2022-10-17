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
    private float gdpPerPerson;
    private long debt;

    private float inflation;
    private String currency;
    private float currencyToGulden;
    private float interest_rate;

    private float gdpIncrease;

    private long labor_force;
    private final long unemployment;
    private long poverty;

    private boolean landlocked;

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
        this.gdpPerPerson = ((float) gdp / population);
        this.debt = (long) (gdp * (Float.parseFloat(context.getResources().getStringArray(R.array.debt)[countryKey]) / 100));

        this.inflation = Float.parseFloat(context.getResources().getStringArray(R.array.inflation)[countryKey]);
        this.currency = context.getResources().getStringArray(R.array.currency)[countryKey];
        this.currencyToGulden = Float.parseFloat(context.getResources().getStringArray(R.array.currency_to_gulden)[countryKey]);
        this.interest_rate = Float.parseFloat(context.getResources().getStringArray(R.array.interest_rate)[countryKey]);

        this.labor_force = (long) (population * (Float.parseFloat(context.getResources().getStringArray(R.array.labor_force)[countryKey]) / 100));
        this.unemployment = labor_force;
        this.poverty = population;

        this.landlocked = Boolean.parseBoolean(context.getResources().getStringArray(R.array.landlocked)[countryKey]);

        this.exports = 9000000;
        this.taxes = 9000000;
        this.tariffs = 9000000;

        this.imports = 9000000;

        this.budget = exports + taxes + tariffs - imports;

        this.gdpIncrease = Float.parseFloat(context.getResources().getStringArray(R.array.gdp_increase)[countryKey]);

        this.developmentIndex = 1;
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

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public boolean isLandlocked() {
        return landlocked;
    }

    public void setLandlocked(boolean landlocked) {
        this.landlocked = landlocked;
    }

    public long getArea() {
        return area;
    }

    public void setArea(long area) {
        this.area = area;
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

    public float getGdpPerPerson() {
        return gdpPerPerson;
    }

    public void setGdpPerPerson(float gdpPerPerson) {
        this.gdpPerPerson = gdpPerPerson;
    }

    public float getCurrencyToGulden() {
        return currencyToGulden;
    }

    public void setCurrencyToGulden(float currencyToGulden) {
        this.currencyToGulden = currencyToGulden;
    }

    @Override
    public String toString() {
        String string;
        string = "Area: " + area + " km^2" + "\n";
        string += "Population: " + population + "\n";
        string += "Density: " + String.format("%.2f", density) + "/km^2" + "\n";
        string += "GDP: " + gdp + " ƒ" + "\n";
        string += "GDP per Capita: " + String.format("%.2f", gdpPerPerson) + " ƒ" + " (" + (gdpPerPerson / currencyToGulden) + " " + currency + ")" + "\n";
        string += "National Debt: " + debt + " ƒ " + String.format("(%.2f", (float) debt / gdp * 100).concat(" %)") + "\n";
        string += "Inflation: " + String.format("%.2f", inflation) + " %" + "\n";
        string += "Currency: " + country.getAdjective() + " " + currency + "\n";
        string += "1 " + currency + " is " + String.format("%.5f", currencyToGulden) + " of Golden Florin (ƒ)" + "\n";
        string += "Interest Rate: " + interest_rate + " %" + "\n";
        string += "GDP Increase from previous year: " + gdpIncrease + " %" + "\n";
        string += "Labor Force: " + labor_force + " (" + String.format("%.1f", (float) labor_force / population * 100.0) + " %)" + "\n";
        string += "Unemployment Rate: " + unemployment + " (" + String.format("%.1f", (float) unemployment / population * 100.0) + " %)" + "\n";
        string += "Poverty Rate: " + poverty + " (" + String.format("%.1f", (float) poverty / population * 100.0) + " %)" + "\n";
        string += "Landlocked: " + (this.landlocked ? "Yes" : "No") + "\n";
        string += "Development Index: " + getDevelopmentIndexString() + "\n";
        return super.toString() + string;
    }

    @Override
    public void updateMinistry() {
        super.updateMinistry();
        this.developmentIndex = (country.getMinistryOfHealthcare().getLifeExpectancy() * country.getMinistryOfEducation().getLiteracy() * gdpPerPerson) / 1000;
    }
}
