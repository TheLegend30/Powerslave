package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

import java.util.Random;

public class MinistryOfIndustry extends Ministry {
    private int lowWorkers;
    private int lowWorkersLimit;
    private int maximumLowWorkersLimit;
    private float lowWorkersSalary;
    private float lowWorkersSalaryNeed;

    private int middleWorkers;
    private int middleWorkersLimit;
    private int maximumMiddleWorkersLimit;
    private float middleWorkersSalary;
    private float middleWorkersSalaryNeed;

    private int highWorkers;
    private int highWorkersLimit;
    private int maximumHighWorkersLimit;
    private float highWorkersSalary;
    private float highWorkersSalaryNeed;

    // first output
    private float alloysOutput;
    private float chemicalsOutput;
    private float buildingMaterialsOutput;
    private float foodOutput;

    // second output
    private float mechanicsOutput;
    private float fuelOutput;
    private float lightIndustryOutput;

    // third output
    private float militaryIndustryOutput;
    private float electricsOutput;


    //For ministry
    private float rawFoodNeed;
    private float rawNeed;

    private float alloysNeed;
    private float chemicalsNeed;
    private float mechanicsNeed;

    MinistryOfEconomy economy;

    public MinistryOfIndustry(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);
        this.name = "Ministry of Industry";
        this.economy = this.country.getMinistryOfEconomy();

        statsRandomizer();
    }

    public int getLowWorkers() {
        return lowWorkers;
    }

    public float getLowWorkersSalary() {
        return lowWorkersSalary;
    }

    public int getMiddleWorkers() {
        return middleWorkers;
    }

    public float getMiddleWorkersSalary() {
        return middleWorkersSalary;
    }

    public int getHighWorkers() {
        return highWorkers;
    }

    public float getHighWorkersSalary() {
        return highWorkersSalary;
    }

    public float getAlloysOutput() {
        return alloysOutput;
    }

    public float getChemicalsOutput() {
        return chemicalsOutput;
    }

    public float getBuildingMaterialsOutput() {
        return buildingMaterialsOutput;
    }

    public float getFoodOutput() {
        return foodOutput;
    }

    public float getMechanicsOutput() {
        return mechanicsOutput;
    }

    public float getFuelOutput() {
        return fuelOutput;
    }

    public float getLightIndustryOutput() {
        return lightIndustryOutput;
    }

    public float getMilitaryIndustryOutput() {
        return militaryIndustryOutput;
    }

    public float getElectricsOutput() {
        return electricsOutput;
    }

    public float getRawFoodNeed() {
        return rawFoodNeed;
    }

    public float getRawNeed() {
        return rawNeed;
    }

    public float getAlloysNeed() {
        return alloysNeed;
    }

    public float getChemicalsNeed() {
        return chemicalsNeed;
    }

    public float getMechanicsNeed() {
        return mechanicsNeed;
    }



    @Override
    public String toString() {
        String string;
        string = "Low-level workers: " + lowWorkers + "\n";
        string += "Low-level workers limit: " + lowWorkersLimit + " (Maximum - " + maximumLowWorkersLimit + ")" + "\n";
        string += "Low-level workers salary: "  + String.format("%.2f", lowWorkersSalary) + " ƒ" + "\n";
        string += "Low-level workers salary need: "  + String.format("%.2f", lowWorkersSalaryNeed) + " ƒ" + "\n";
        string += "Middle-level workers: " + middleWorkers + " (Maximum - " + maximumMiddleWorkersLimit + ")" + "\n";
        string += "Middle-level workers limit: " + middleWorkersLimit + "\n";
        string += "Middle-level workers salary: " + String.format("%.2f", middleWorkersSalary) + " ƒ" + "\n";
        string += "Middle-level workers salary need: " + String.format("%.2f", middleWorkersSalaryNeed) + " ƒ" + "\n";
        string += "High-level workers: " + highWorkers + " (Maximum - " + maximumHighWorkersLimit + ")" + "\n";
        string += "High-level workers limit: " + highWorkersLimit + "\n";
        string += "High-level workers salary: " + String.format("%.2f", highWorkersSalary) + " ƒ" + "\n";
        string += "High-level workers salary need: " + String.format("%.2f", highWorkersSalaryNeed) + " ƒ" + "\n";
        string += "General budget: " + String.format("%.2f", generalBudget) + " ƒ" + "\n";
        string += "General need budget: " + String.format("%.2f", generalBudgetNeed) + " ƒ" + "\n";
        string += "Alloys output: " + alloysOutput + " units" + "\n";
        string += "Chemicals output: " + chemicalsOutput + " units" + "\n";
        string += "Building materials output: " + buildingMaterialsOutput + " units" + "\n";
        string += "Food output: " + foodOutput + " units" + "\n";
        string += "Mechanics output: " + mechanicsOutput + " units" + "\n";
        string += "Fuel output: " + fuelOutput + " units" + "\n";
        string += "Light industry output: " + lightIndustryOutput + " units" + "\n";
        string += "Electrics output: " + electricsOutput + " units" + "\n";
        string += "Military industry output: " + militaryIndustryOutput + " units" + "\n";
        string += "Raw food need: " + rawFoodNeed + " units" + "\n";
        string += "Raw need: " + rawNeed + " units" + "\n";
        string += "Alloys need: " + alloysNeed + " units" + "\n";
        string += "Chemicals need: " + chemicalsNeed + " units" + "\n";
        string += "Mechanics need: " + mechanicsNeed + " units" + "\n";
        return super.toString() + string;
    }

    @Override
    public void updateMinistry() {
        super.updateMinistry();
        this.lowWorkersSalaryNeed = this.economy.getGdpPerPerson() * 0.6f;
        this.middleWorkersSalaryNeed = this.economy.getGdpPerPerson() * 0.85f;
        this.highWorkersSalaryNeed = this.economy.getGdpPerPerson() * 1.15f;

        maximumLowWorkersLimit = (int) (economy.getLabor_force() * 0.45);
        maximumMiddleWorkersLimit = (int) (economy.getLabor_force() * 0.45);
        maximumHighWorkersLimit = (int) (economy.getLabor_force() * 0.45);

        generalBudgetNeed = (lowWorkers * 0.05f) + (middleWorkers * 0.07f) + (highWorkers * 0.1f) + (lowWorkers * lowWorkersSalaryNeed) + (middleWorkers * middleWorkersSalaryNeed) + (highWorkers * highWorkersSalaryNeed);
        generalBudget += (lowWorkers * lowWorkersSalary) + (middleWorkers * middleWorkersSalary) + (highWorkers * highWorkersSalary);

        efficiency *= generalBudget / generalBudgetNeed;

        alloysOutput = efficiency * lowWorkers * 1.85f;
        chemicalsOutput = efficiency * lowWorkers * 1.25f;
        buildingMaterialsOutput = efficiency * lowWorkers * 1.5f;
        foodOutput = efficiency * lowWorkers * 3.75f;

        mechanicsOutput = efficiency * middleWorkers * 1.5f;
        fuelOutput = efficiency * middleWorkers * 1.85f;
        lightIndustryOutput = efficiency * middleWorkers * 1.45f;

        electricsOutput = efficiency * highWorkers * 1.15f;
        militaryIndustryOutput = efficiency * highWorkers * 1.15f;

        rawFoodNeed = (foodOutput * 0.75f) + (lightIndustryOutput * 0.35f);
        rawNeed = (alloysOutput * 0.95f) + (buildingMaterialsOutput * 0.85f) + (lightIndustryOutput * 0.35f) + (chemicalsOutput * 0.25f);

        alloysNeed = (mechanicsOutput * 0.55f) + (militaryIndustryOutput * 1.15f);
        chemicalsNeed = (fuelOutput * 1.5f) + (lightIndustryOutput * 0.15f);
        mechanicsNeed = (electricsOutput * 0.55f) + (militaryIndustryOutput * 0.65f);

    }

    @Override
    public void statsRandomizer() {
        super.statsRandomizer();
        Random random = new Random();

        float modifierLowWorkers = 0f;
        float modifierLowWorkersSalary = 0f;

        float modifierMiddleWorkers = 0f;
        float modifierMiddleWorkersSalary = 0f;

        float modifierHighWorkers = 0f;
        float modifierHighWorkersSalary = 0f;

        switch (country.getContinent()) {
            case EY:
                modifierLowWorkers = 0.3f;
                modifierLowWorkersSalary = 0.35f;

                modifierMiddleWorkers = 0.1f;
                modifierMiddleWorkersSalary = 0.65f;

                modifierHighWorkers = 0.05f;
                modifierHighWorkersSalary = 0.95f;

                break;
            case NY:
                modifierLowWorkers = 0.2f;
                modifierLowWorkersSalary = 0.55f;

                modifierMiddleWorkers = 0.2f;
                modifierMiddleWorkersSalary = 0.85f;

                modifierHighWorkers = 0.1f;
                modifierHighWorkersSalary = 1.25f;

                break;
            case SY:
                modifierLowWorkers = 0.35f;
                modifierLowWorkersSalary = 0.3f;

                modifierMiddleWorkers = 0.05f;
                modifierMiddleWorkersSalary = 0.75f;

                modifierHighWorkers = 0.05f;
                modifierHighWorkersSalary = 0.85f;

                break;
            case WY:
                modifierLowWorkers = 0.1f;
                modifierLowWorkersSalary = 0.65f;

                modifierMiddleWorkers = 0.25f;
                modifierMiddleWorkersSalary = 0.95f;

                modifierHighWorkers = 0.15f;
                modifierHighWorkersSalary = 1.3f;

                break;
            case IB:
                modifierLowWorkers = 0.25f;
                modifierLowWorkersSalary = 0.25f;

                modifierMiddleWorkers = 0.05f;
                modifierMiddleWorkersSalary = 0.5f;

                modifierHighWorkers = 0.04f;
                modifierHighWorkersSalary = 0.85f;

                break;
            case OB:
                modifierLowWorkers = 0.2f;
                modifierLowWorkersSalary = 0.25f;

                modifierMiddleWorkers = 0.05f;
                modifierMiddleWorkersSalary = 0.45f;

                modifierHighWorkers = 0.03f;
                modifierHighWorkersSalary = 0.8f;

                break;
            case CA:
                modifierLowWorkers = 0.15f;
                modifierLowWorkersSalary = 0.23f;

                modifierMiddleWorkers = 0.04f;
                modifierMiddleWorkersSalary = 0.45f;

                modifierHighWorkers = 0.02f;
                modifierHighWorkersSalary = 0.8f;

                break;
            case FA:
                modifierLowWorkers = 0.12f;
                modifierLowWorkersSalary = 0.23f;

                modifierMiddleWorkers = 0.03f;
                modifierMiddleWorkersSalary = 0.45f;

                modifierHighWorkers = 0.02f;
                modifierHighWorkersSalary = 0.75f;

                break;
            case ME:
                modifierLowWorkers = 0.1f;
                modifierLowWorkersSalary = 0.2f;

                modifierMiddleWorkers = 0.03f;
                modifierMiddleWorkersSalary = 0.4f;

                modifierHighWorkers = 0.01f;
                modifierHighWorkersSalary = 0.7f;

                break;
            case GE:
                modifierLowWorkers = 0.1f;
                modifierLowWorkersSalary = 0.6f;

                modifierMiddleWorkers = 0.3f;
                modifierMiddleWorkersSalary = 1f;

                modifierHighWorkers = 0.2f;
                modifierHighWorkersSalary = 1.2f;

                break;
        }
        lowWorkersLimit = (int) (economy.getLabor_force() * (modifierLowWorkers + ((random.nextFloat() * (0.005 - (-0.005)) + (-0.005)))));
        lowWorkers = lowWorkersLimit;
        lowWorkersSalary = (float) (economy.getGdpPerPerson() * (modifierLowWorkersSalary + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));

        middleWorkersLimit = (int) (economy.getLabor_force() * (modifierMiddleWorkers + ((random.nextFloat() * (0.005 - (-0.005)) + (-0.005)))));
        middleWorkers = middleWorkersLimit;
        middleWorkersSalary = (float) (economy.getGdpPerPerson() * (modifierMiddleWorkersSalary + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));

        highWorkersLimit = (int) (economy.getLabor_force() * (modifierHighWorkers + ((random.nextFloat() * (0.005 - (-0.005)) + (-0.005)))));
        highWorkers = highWorkersLimit;
        highWorkersSalary = (float) (economy.getGdpPerPerson() * (modifierHighWorkersSalary + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));


    }

}
