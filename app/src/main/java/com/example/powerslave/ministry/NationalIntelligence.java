package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

public class NationalIntelligence extends Ministry{

    private int agents;
    private int agents_salary;

    private Country priority;
    private int mission;

    public NationalIntelligence(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);
    }
}
