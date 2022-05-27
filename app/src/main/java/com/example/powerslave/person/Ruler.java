package com.example.powerslave.person;

import com.example.powerslave.government.Continent;
import com.example.powerslave.government.Ideology;

public class Ruler extends Person{
    private float support = 50f;
    public Ruler(String name, String surname, Sex sex, Continent motherland, Ideology ideology) {
        super(name, surname, sex, motherland, ideology);
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
