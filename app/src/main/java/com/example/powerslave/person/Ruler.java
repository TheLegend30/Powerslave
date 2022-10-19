package com.example.powerslave.person;

import android.net.Uri;

import com.example.powerslave.government.Country;
import com.example.powerslave.government.Ideology;

public class Ruler extends Person {
    private float rating = 50f;
    private int term_in_weeks = 40;

    public Ruler(String name, String surname, Sex sex, Country country, Ideology ideology, Uri portrait) {
        super(name, surname, sex, country, ideology, portrait);
    }

    public float getRating() {
        return rating;
    }

    public String getRatingString() {
        return String.format("%.2f", rating);
    }

    public void setRating(float support) {
        if (support > 100) {
            support = 100;
        } else if (support < 0) {
            support = 0;
        }
        this.rating = support;
    }

    public int getTerm_in_weeks() {
        return term_in_weeks;
    }

    public void setTerm_in_weeks(int term_in_weeks) {
        this.term_in_weeks = term_in_weeks;
    }

    public void changeRating() {
        float rating = 1f;
        rating *= (country.getMinistryOfHealthcare().getEfficiency() / 0.5f);
        rating *= (country.getMinistryOfEducation().getEfficiency() / 0.5f);
        setRating(getRating() + (rating - 1));
    }
}
