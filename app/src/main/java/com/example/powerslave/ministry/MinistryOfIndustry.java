package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

public class MinistryOfIndustry extends Ministry{
    private int low_workers;
    private int middle_workers;
    private int high_workers;

    // first output
    private float alloys_output;
    private float chemicals_output;
    private float building_materials_output;

    // second output
    private float mechanics_output;
    private float fuel_output;
    private float light_industry_output;
    private float food_output;

    // third output
    private float military_industry_output;
    private float electrics_output;

    
    private float alloys_reserve;
    private float chemicals_reserve;
    private float building_materials_reserve;
    private float mechanics_reserve;
    private float fuel_reserve;
    private float light_industry_reserve;
    private float food_reserve;
    private float military_industry_reserve;
    private float electrics_reserve;

    //For ministry
    private float raw_food_need;
    private float raw_need;

    //Other ministries needs
    private float alloys_need;
    private float chemicals_need;
    private float building_materials_need;
    private float mechanics_need;
    private float fuel_need;
    private float light_industry_need;
    private float food_need;
    private float military_industry_need;
    private float electrics_need;

    public MinistryOfIndustry(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);
    }
}
