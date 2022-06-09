package com.example.powerslave.person;

import android.net.Uri;

import com.example.powerslave.government.Continent;
import com.example.powerslave.government.Country;
import com.example.powerslave.government.Ideology;

public class Ruler extends Person{
    private float support = 50f;
    public Ruler(String name, String surname, Sex sex, Country country, Ideology ideology, Uri portrait) {
        super(name, surname, sex, country, ideology, portrait);
    }

    public float getSupport() {
        return support;
    }

    public void setSupport(float support) {
        if (support > 100) {
            support = 100;
        } else if (support < 0) {
            support = 0;
        }
        this.support = support;
    }
}
