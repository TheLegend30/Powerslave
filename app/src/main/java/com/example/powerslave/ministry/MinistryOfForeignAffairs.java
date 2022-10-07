package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MinistryOfForeignAffairs extends Ministry {

    private int diplomats;
    private int diplomats_salary;

    public HashMap<Country, Integer> countries;

    public MinistryOfForeignAffairs(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);

        countries = new HashMap<>();
    }

    //    TODO: Relation class
    @Override
    public void updateMinistry() {
        super.updateMinistry();
        for (int i = 0; i < Country.countries.size(); i++) {
            if (country.equals(Country.countries.get(i))) continue;
            countries.put(Country.countries.get(i), 0);
        }
    }
}

