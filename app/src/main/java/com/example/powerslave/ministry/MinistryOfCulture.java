package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

import java.util.Random;

public class MinistryOfCulture extends Ministry{

    private int culturalWorkers;
    private int culturalWorkersNeed;
    private int culturalWorkersLimit;
    private int maximumCulturalWorkersLimit;
    private float culturalWorkersSalary;
    private float culturalWorkersSalaryNeed;

    private int monuments;

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

    public int getCulturalWorkers() {
        return culturalWorkers;
    }

    public void setCulturalWorkers(int culturalWorkers) {
        this.culturalWorkers = culturalWorkers;
    }

    public int getCulturalWorkersLimit() {
        return culturalWorkersLimit;
    }

    public void setCulturalWorkersLimit(int culturalWorkersLimit) {
        this.culturalWorkersLimit = culturalWorkersLimit;
    }

    public int getMaximumCulturalWorkersLimit() {
        return maximumCulturalWorkersLimit;
    }

    public void setMaximumCulturalWorkersLimit(int maximumCulturalWorkersLimit) {
        this.maximumCulturalWorkersLimit = maximumCulturalWorkersLimit;
    }

    public float getCulturalWorkersSalary() {
        return culturalWorkersSalary;
    }

    public void setCulturalWorkersSalary(float culturalWorkersSalary) {
        this.culturalWorkersSalary = culturalWorkersSalary;
    }

    public float getCulturalWorkersSalaryNeed() {
        return culturalWorkersSalaryNeed;
    }

    public void setCulturalWorkersSalaryNeed(float culturalWorkersSalaryNeed) {
        this.culturalWorkersSalaryNeed = culturalWorkersSalaryNeed;
    }

    public int getMonuments() {
        return monuments;
    }

    public void setMonuments(int monuments) {
        this.monuments = monuments;
    }

    public int getCulturalWorkersNeed() {
        return culturalWorkersNeed;
    }

    public void setCulturalWorkersNeed(int culturalWorkersNeed) {
        this.culturalWorkersNeed = culturalWorkersNeed;
    }

    @Override
    public String toString() {
        String string;
        string = "Cultural workers: " + culturalWorkers + "\n";
        string += "Cultural workers need: " + culturalWorkersNeed + "\n";
        string += "Cultural workers limit: " + culturalWorkersLimit  + " (Maximum - " + maximumCulturalWorkersLimit + ")" +  "\n";
        string += "Cultural workers salary: " + String.format("%.2f", culturalWorkersSalary) + " ƒ" + "\n";
        string += "Cultural workers salary need: " + String.format("%.2f", culturalWorkersSalaryNeed) + " ƒ" + "\n";
        string += "Monuments and events: " + monuments + "\n";
        string += "General budget: " + String.format("%.2f", generalBudget) + " ƒ" + "\n";
        string += "General need budget: " + String.format("%.2f", generalBudgetNeed) + " ƒ" + "\n";
        string += "Tourists count: " + touristsCount + "\n";
        return super.toString() + string;
    }

    @Override
    public void updateMinistry() {
        super.updateMinistry();

        efficiency *= (float) culturalWorkers / culturalWorkersNeed;
        efficiency *= generalBudget / generalBudgetNeed;

        touristsCount = (int) (country.getMinistryOfEducation().getLiberty() * efficiency * monuments * 3000);
    }

    @Override
    public void statsRandomizer() {
        super.statsRandomizer();
        Random random = new Random();

        float modifierCulturalWorkers = 0f;
        float modifierCulturalWorkersSalary = 0f;
        float modifierMonuments = 0f;

        switch (country.getContinent()) {
            case EY:
                 modifierCulturalWorkers = 0.0005f;
                 modifierCulturalWorkersSalary = 0.35f;
                 modifierMonuments = 0.1f;
                break;
            case NY:
                modifierCulturalWorkers = 0.0009f;
                modifierCulturalWorkersSalary = 0.4f;
                modifierMonuments = 0.4f;
                break;
            case SY:
                modifierCulturalWorkers = 0.0004f;
                modifierCulturalWorkersSalary = 0.3f;
                modifierMonuments = 0.1f;
                break;
            case WY:
                modifierCulturalWorkers = 0.00095f;
                modifierCulturalWorkersSalary = 0.45f;
                modifierMonuments = 0.5f;
                break;
            case IB:
                modifierCulturalWorkers = 0.0003f;
                modifierCulturalWorkersSalary = 0.25f;
                modifierMonuments = 0.05f;
                break;
            case OB:
                modifierCulturalWorkers = 0.00033f;
                modifierCulturalWorkersSalary = 0.24f;
                modifierMonuments = 0.07f;
                break;
            case CA:
                modifierCulturalWorkers = 0.00025f;
                modifierCulturalWorkersSalary = 0.2f;
                modifierMonuments = 0.055f;
                break;
            case FA:
                modifierCulturalWorkers = 0.00023f;
                modifierCulturalWorkersSalary = 0.22f;
                modifierMonuments = 0.05f;
                break;
            case ME:
                modifierCulturalWorkers = 0.0002f;
                modifierCulturalWorkersSalary = 0.2f;
                modifierMonuments = 0.035f;
                break;
            case GE:
                modifierCulturalWorkers = 0.00092f;
                modifierCulturalWorkersSalary = 0.43f;
                modifierMonuments = 0.45f;
                break;
        }

        culturalWorkersSalaryNeed = economy.getGdpPerPerson() * 0.45f;

        culturalWorkersLimit = (int) (economy.getLabor_force() * (modifierCulturalWorkers + (random.nextFloat() * (0.00005f - (-0.00005f)) + (-0.00005f))));
        culturalWorkers = culturalWorkersLimit;
        culturalWorkersSalary = economy.getGdpPerPerson() * (modifierCulturalWorkersSalary + (random.nextFloat() * (0.05f - (-0.05f)) + (-0.05f)));

        monuments = (int) ((economy.getArea() / 1000) * (modifierMonuments + (random.nextFloat() * (0.005f - (-0.005f)) + (-0.005f))));
        generalBudgetNeed = (culturalWorkers * 0.2f) + (culturalWorkers * culturalWorkersSalary) + (monuments * 3000f);
        generalBudget = generalBudgetNeed * (random.nextFloat() * (0.8f - (0.2f)) + (0.f));

        culturalWorkersNeed = monuments * 150;

        maximumCulturalWorkersLimit = culturalWorkersNeed * 10;
    }

    @Override
    public void workersIncreasing() {
        super.workersIncreasing();
        culturalWorkers += culturalWorkersLimit * 0.4 * country.getMinistryOfEducation().efficiency * efficiency;
        if (culturalWorkers > culturalWorkersLimit) culturalWorkers = culturalWorkersLimit;
    }
}
