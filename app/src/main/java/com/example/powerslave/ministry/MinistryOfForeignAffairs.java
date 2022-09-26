package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

import java.util.ArrayList;

public class MinistryOfForeignAffairs extends Ministry {

    private int diplomats;
    private int diplomats_salary;

    private static ArrayList<Country> countries = Country.countries;
    private static ArrayList<Relation> relations = new ArrayList<>();

    public MinistryOfForeignAffairs(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);
    }

    private class Relation {
        private Country country1;
        private Country country2;

        private int relation;

        Relation(Country country1, Country country2, int relation) {
            this.country1 = country1;
            this.country2 = country2;

            if (relation > 100) {
                this.relation = 100;
            } else if (relation < -100) {
                this.relation = -100;
            } else {
                this.relation = relation;
            }
        }
    }
}
