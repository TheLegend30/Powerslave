package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

public class MinistryOfInternalAffairs extends Ministry{

    private int policemen;
    private int policemen_salary;

    private int level_of_security;

    private int departments_count;

    public MinistryOfInternalAffairs(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);
    }
}
