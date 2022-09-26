package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

public class MinistryOfTransportation extends Ministry{

    private int builders;
    private int builders_salary;

    private int airports;
    private int ports;
    private int railroad_kms;
    private int road_kms;

    public MinistryOfTransportation(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);
    }
}
