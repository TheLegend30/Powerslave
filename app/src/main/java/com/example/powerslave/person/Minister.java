package com.example.powerslave.person;

import android.net.Uri;

import com.example.powerslave.government.Continent;
import com.example.powerslave.government.Country;
import com.example.powerslave.government.Ideology;

import java.util.Random;

public class Minister extends Person {
    private float loyalty;
    private float competency;



    public Minister(String name, String surname, Sex sex, Country country, Ideology ideology, float loyalty, float competency, Uri portrait) {
        super(name, surname, sex, country, ideology, portrait);
        this.loyalty = loyalty;
        this.competency = competency;
        this.portrait = portrait;
    }

    public float getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(float loyalty) {
        this.loyalty = loyalty;
    }

    public float getCompetency() {
        return competency;
    }

    public void setCompetency(float competency) {
        this.competency = competency;
    }

    public Uri getPortrait() {
        return portrait;
    }

    public void setPortrait(Uri portrait) {
        this.portrait = portrait;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "L: " + loyalty + "\n" +
                "C: " + competency;
    }

}
