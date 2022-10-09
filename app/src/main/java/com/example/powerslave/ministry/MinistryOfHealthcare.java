package com.example.powerslave.ministry;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.powerslave.R;
import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

public class MinistryOfHealthcare extends Ministry {

    private float life_expectancy;
    private int retirement_age;

    private float pension;
    private int pensioners;

    private long doctors;
    private long doctors_salary;
    private int hospitals;

    private float mortality;
    private float natality;
    private float pop_growth;

    private MinistryOfEconomy economy;

    public MinistryOfHealthcare(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);
        this.name = "Ministry of Healthcare";

        this.economy = country.getMinistryOfEconomy();

    }

    public float getLife_expectancy() {
        return life_expectancy;
    }

    public String getLife_expectancyString() {
        return life_expectancy + " years";
    }

    public void setLife_expectancy(float life_expectancy) {
        this.life_expectancy = life_expectancy;
    }

    public int getPensioners() {
        return pensioners;
    }

    public void setPensioners(int pensioners) {
        this.pensioners = pensioners;
    }

    @Override
    public String toString() {
        String string;
        string = "Life expectancy: " + getLife_expectancyString() + "\n";
        string += "Pensioners: " + this.pensioners + "\n";
        return super.toString() + string;
    }
}
