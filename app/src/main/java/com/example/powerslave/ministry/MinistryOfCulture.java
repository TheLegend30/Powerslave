package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

import java.util.Random;
// TODO: Liberty
public class MinistryOfCulture extends Ministry{

    private int cultural_workers;
    private int cultural_workers_limit;
    private float cultural_workers_salary;
    private float cultural_workers_salary_need;

    private int monuments_and_events;
    private int monuments_and_events_need;

    private int tourists_count;

    private MinistryOfEconomy economy;

    public MinistryOfCulture(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);
        this.name = "Ministry of Culture";
        this.economy = country.getMinistryOfEconomy();

        statsRandomizer();
    }

    @Override
    public String toString() {
        String string;
        string = "Cultural workers: " + cultural_workers + "\n";
        string += "Cultural workers limit: " + cultural_workers_limit + " ƒ" +  "\n";
        string += "Cultural workers salary: " + cultural_workers_salary + " ƒ" + "\n";
        string += "Cultural workers salary need: " + cultural_workers_salary_need + "\n";
        string += "Monuments and events: " + monuments_and_events + "\n";
        string += "Monuments and events need: " + monuments_and_events_need + "\n";
        string += "Tourists count: " + tourists_count + "\n";
        return super.toString() + string;
    }

    @Override
    public void updateMinistry() {
        super.updateMinistry();

        efficiency *= cultural_workers_salary / cultural_workers_salary_need;
        efficiency *= (float) monuments_and_events / monuments_and_events_need;

        tourists_count = (int) (country.getMinistryOfEducation().getLiberty() * efficiency * monuments_and_events * 3000);
    }

    @Override
    public void statsRandomizer() {
        super.statsRandomizer();
        Random random = new Random();

        float modifier_cultural_workers = 0f;
        float modifier_cultural_workers_salary = 0f;
        float modifier_monuments_and_events = 0f;

        switch (this.country.getContinent()) {
            case EY:
                 modifier_cultural_workers = 0.0005f;
                 modifier_cultural_workers_salary = 0.35f;
                 modifier_monuments_and_events = 0.1f;
                break;
            case NY:
                modifier_cultural_workers = 0.0009f;
                modifier_cultural_workers_salary = 0.4f;
                modifier_monuments_and_events = 0.4f;
                break;
            case SY:
                modifier_cultural_workers = 0.0004f;
                modifier_cultural_workers_salary = 0.3f;
                modifier_monuments_and_events = 0.1f;
                break;
            case WY:
                modifier_cultural_workers = 0.00095f;
                modifier_cultural_workers_salary = 0.45f;
                modifier_monuments_and_events = 0.5f;
                break;
            case IB:
                modifier_cultural_workers = 0.0003f;
                modifier_cultural_workers_salary = 0.25f;
                modifier_monuments_and_events = 0.05f;
                break;
            case OB:
                modifier_cultural_workers = 0.00033f;
                modifier_cultural_workers_salary = 0.24f;
                modifier_monuments_and_events = 0.07f;
                break;
            case CA:
                modifier_cultural_workers = 0.00025f;
                modifier_cultural_workers_salary = 0.2f;
                modifier_monuments_and_events = 0.055f;
                break;
            case FA:
                modifier_cultural_workers = 0.00023f;
                modifier_cultural_workers_salary = 0.22f;
                modifier_monuments_and_events = 0.05f;
                break;
            case ME:
                modifier_cultural_workers = 0.0002f;
                modifier_cultural_workers_salary = 0.2f;
                modifier_monuments_and_events = 0.035f;
                break;
            case GE:
                modifier_cultural_workers = 0.00092f;
                modifier_cultural_workers_salary = 0.43f;
                modifier_monuments_and_events = 0.45f;
                break;
        }

        cultural_workers_salary_need = economy.getGdp_per_person() * 0.45f;

        cultural_workers_limit = (int) (economy.getLabor_force() * (modifier_cultural_workers + (random.nextFloat() * (0.00005f - (-0.00005f)) + (-0.00005f))));
        cultural_workers = cultural_workers_limit;
        cultural_workers_salary = economy.getGdp_per_person() * (modifier_cultural_workers_salary + (random.nextFloat() * (0.05f - (-0.05f)) + (-0.05f)));

        monuments_and_events_need = cultural_workers / 50;

        monuments_and_events = (int) (monuments_and_events_need * (modifier_monuments_and_events + (random.nextFloat() * (0.005f - (-0.005f)) + (-0.005f))));
    }
}
