package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

public class MinistryOfCulture extends Ministry{

    private int cultural_workers;
    private int cultural_workers_salary;

    private int monuments_events;

    private int tourists_count;


    public MinistryOfCulture(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);
    }
}
