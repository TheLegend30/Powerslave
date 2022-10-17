package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

import java.util.Random;
// TODO: Liberty
public class MinistryOfCulture extends Ministry{

    private int culturalWorkers;
    private int culturalWorkersLimit;
    private float culturalWorkersSalary;
    private float culturalWorkersSalaryNeed;

    private int monumentsAndEvents;
    private int monumentsAndEventsNeed;

    private int touristsCount;

    private MinistryOfEconomy economy;

    public MinistryOfCulture(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);
        this.name = "Ministry of Culture";
        this.economy = country.getMinistryOfEconomy();

        statsRandomizer();
    }

    public int getTouristsCount() {
        return touristsCount;
    }

    public void setTouristsCount(int touristsCount) {
        this.touristsCount = touristsCount;
    }

    @Override
    public String toString() {
        String string;
        string = "Cultural workers: " + culturalWorkers + "\n";
        string += "Cultural workers limit: " + culturalWorkersLimit + " ƒ" +  "\n";
        string += "Cultural workers salary: " + culturalWorkersSalary + " ƒ" + "\n";
        string += "Cultural workers salary need: " + culturalWorkersSalaryNeed + "\n";
        string += "Monuments and events: " + monumentsAndEvents + "\n";
        string += "Monuments and events need: " + monumentsAndEventsNeed + "\n";
        string += "Tourists count: " + touristsCount + "\n";
        return super.toString() + string;
    }

    @Override
    public void updateMinistry() {
        super.updateMinistry();

        efficiency *= culturalWorkersSalary / culturalWorkersSalaryNeed;
        efficiency *= (float) monumentsAndEvents / monumentsAndEventsNeed;

        touristsCount = (int) (country.getMinistryOfEducation().getLiberty() * efficiency * monumentsAndEvents * 3000);
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

        culturalWorkersSalaryNeed = economy.getGdpPerPerson() * 0.45f;

        culturalWorkersLimit = (int) (economy.getLabor_force() * (modifier_cultural_workers + (random.nextFloat() * (0.00005f - (-0.00005f)) + (-0.00005f))));
        culturalWorkers = culturalWorkersLimit;
        culturalWorkersSalary = economy.getGdpPerPerson() * (modifier_cultural_workers_salary + (random.nextFloat() * (0.05f - (-0.05f)) + (-0.05f)));

        monumentsAndEventsNeed = culturalWorkers / 50;

        monumentsAndEvents = (int) (monumentsAndEventsNeed * (modifier_monuments_and_events + (random.nextFloat() * (0.005f - (-0.005f)) + (-0.005f))));
    }
}
