package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

public class MinistryOfJustice extends Ministry{

    private int judges;
    private int judges_salary;

    private int prisons;

    private int level_of_judges_liberty;

    private boolean death_penalty = true;

    public MinistryOfJustice(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);
    }
}
