package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

public class MinistryOfDevelopment extends Ministry{
    private int clerks;
    private int clerks_salary;
    private int clerks_salary_need;

    private float leisure_output;
    private float tourism_output;
    private float trade_output;

    private float electrics_need;
    private float light_industry_need;
    private float food_need;

    private float leisure_need;
    private float tourism_need;
    private float trade_need;

    public MinistryOfDevelopment(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);
    }
}
