package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.government.Ideology;
import com.example.powerslave.person.Minister;
import com.example.powerslave.person.Ruler;

import java.util.HashMap;

public class Parliament extends Ministry {

    private Ideology your_ideology;
    private HashMap<Ideology, Integer> parties = new HashMap<>();

    public Parliament(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);
    }
}
