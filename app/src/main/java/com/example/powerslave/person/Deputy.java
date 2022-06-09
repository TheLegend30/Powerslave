package com.example.powerslave.person;

import android.net.Uri;

import com.example.powerslave.government.Continent;
import com.example.powerslave.government.Country;
import com.example.powerslave.government.Ideology;

public class Deputy extends Person {

    public Deputy(String name, String surname, Sex sex, Country country, Ideology ideology, Uri portrait) {
        super(name, surname, sex, country, ideology, portrait);
    }
}
