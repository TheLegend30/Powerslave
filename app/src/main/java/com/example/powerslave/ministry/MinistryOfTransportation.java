package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

public class MinistryOfTransportation extends Ministry {

    private int builders;
    private int builders_limit;
    private int builders_need;

    private int builders_salary;
    private int builders_salary_need;

    private int airports;
    private int airports_limit;
    private int airports_need;

    private int ports;
    private int ports_limit;
    private int ports_need;

    private int railroad_kms;
    private int railroad_kms_limit;
    private int railroad_kms_need;

    private int road_kms;
    private int road_kms_limit;
    private int road_kms_need;

    private MinistryOfEconomy economy;

    public MinistryOfTransportation(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);

        statsRandomizer();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void updateMinistry() {
        super.updateMinistry();
    }

    @Override
    public void statsRandomizer() {
        super.statsRandomizer();


    }
}
