package com.example.powerslave.ministry;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.powerslave.R;
import com.example.powerslave.person.Minister;

public class MinistryOfHealthcare extends Ministry {

    private float life_expectancy;


    public MinistryOfHealthcare(int countryKey, Minister minister, Context context) {
        super(countryKey, minister, context);
        this.name = "Ministry of Healthcare";
        this.life_expectancy = Float.parseFloat(context.getResources().getStringArray(R.array.life_expectancy)[countryKey]);
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

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Life expectancy: " + getLife_expectancyString();
    }
}
