package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

import java.util.Random;

public class MinistryOfIndustry extends Ministry {
    private int lowWorkers;
    private int lowWorkersLimit;
    private float lowWorkersSalary;
    private float lowWorkersSalaryNeed;

    private int middleWorkers;
    private int middleWorkersLimit;
    private float middleWorkersSalary;
    private float middleWorkersSalaryNeed;

    private int highWorkers;
    private int highWorkersLimit;
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


    // output
    private float alloys_reserve = 0f;
    private float chemicals_reserve = 0f;
    private float building_materials_reserve = 0f;
    private float food_reserve = 0f;

    private float mechanics_reserve = 0f;
    private float fuel_reserve = 0f;
    private float light_industry_reserve = 0f;

    private float military_industry_reserve = 0f;
    private float electrics_reserve = 0f;

    //For ministry
    private float raw_food_need;
    private float raw_need;

    private float alloys_need;
    private float chemicals_need;
    private float mechanics_need;

    MinistryOfEconomy economy;

    public MinistryOfIndustry(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);
        this.name = "Ministry of Industry";
        this.economy = this.country.getMinistryOfEconomy();

        statsRandomizer();
    }

    @Override
    public String toString() {
        String string;
        string = "Low-level workers: " + lowWorkers + "\n";
        string = "Low-level workers: " + lowWorkersLimit + "\n";
        string += "Low-level workers salary: " + lowWorkersSalary + "ƒ" + "\n";
        string += "Low-level workers salary need: " + lowWorkersSalaryNeed + "ƒ" + "\n";
        string += "Middle-level workers: " + middleWorkers + "\n";
        string += "Middle-level workers: " + middleWorkersLimit + "\n";
        string += "Middle-level workers salary: " + middleWorkersSalary + "\n";
        string += "Middle-level workers salary need: " + middleWorkersSalaryNeed + "\n";
        string += "High-level workers: " + highWorkers + "\n";
        string += "High-level workers: " + highWorkersLimit + "\n";
        string += "High-level workers salary: " + highWorkersSalary + "\n";
        string += "High-level workers salary need: " + highWorkersSalaryNeed + "\n";
        string += "General budget: " + generalBudget + " ƒ" + "\n";
        string += "General need budget: " + generalBudgetNeed + " ƒ" + "\n";
        string += "Alloys output: " + alloysOutput + " units" + "\n";
        string += "Alloys reserve: " + alloys_reserve + " units" + "\n";
        string += "Chemicals output: " + chemicalsOutput + " units" + "\n";
        string += "Chemicals reserve: " + chemicals_reserve + " units" + "\n";
        string += "Building materials output: " + buildingMaterialsOutput + " units" + "\n";
        string += "Building materials reserve: " + building_materials_reserve + " units" + "\n";
        string += "Food output: " + foodOutput + " units" + "\n";
        string += "Food reserve: " + food_reserve + " units" + "\n";
        string += "Mechanics output: " + mechanicsOutput + " units" + "\n";
        string += "Mechanics reserve: " + mechanics_reserve + " units" + "\n";
        string += "Fuel output: " + fuelOutput + " units" + "\n";
        string += "Fuel reserve: " + fuel_reserve + " units" + "\n";
        string += "Light industry output: " + lightIndustryOutput + " units" + "\n";
        string += "Light industry  reserve: " + light_industry_reserve + " units" + "\n";
        string += "Electrics output: " + electricsOutput + " units" + "\n";
        string += "Electrics reserve: " + electrics_reserve + " units" + "\n";
        string += "Military industry output: " + militaryIndustryOutput + " units" + "\n";
        string += "Military industry reserve: " + military_industry_reserve + " units" + "\n";
        string += "Raw food need: " + raw_food_need + " units" + "\n";
        string += "Raw need: " + raw_need + " units" + "\n";
        string += "Alloys need: " + alloys_need + " units" + "\n";
        string += "Chemicals need: " + chemicals_need + " units" + "\n";
        string += "Mechanics need: " + mechanics_need + " units" + "\n";
        return super.toString() + string;
    }

    @Override
    public void updateMinistry() {
        super.updateMinistry();
        this.lowWorkersSalaryNeed = this.economy.getGdpPerPerson() / 2.05f;
        this.middleWorkersSalaryNeed = this.economy.getGdpPerPerson() / 1.25f;
        this.highWorkersSalaryNeed = this.economy.getGdpPerPerson() / 1.05f;
        this.generalBudgetNeed = (float) ((this.economy.getBudget() * 0.15) + ((lowWorkers * 0.08) + (middleWorkers * 0.1)) + (highWorkers * 0.15));

        efficiency *= lowWorkersSalary / lowWorkersSalaryNeed;
        efficiency *= middleWorkersSalary / middleWorkersSalaryNeed;
        efficiency *= highWorkersSalary / highWorkersSalaryNeed;
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

        raw_food_need = (foodOutput * 0.75f) + (lightIndustryOutput * 0.35f);
        raw_need = (alloysOutput * 0.95f) + (buildingMaterialsOutput * 0.85f) + (lightIndustryOutput * 0.35f) + (chemicalsOutput * 0.25f);

        alloys_need = (mechanicsOutput * 0.55f) + (militaryIndustryOutput * 1.15f);
        chemicals_need = (fuelOutput * 1.5f) + (lightIndustryOutput * 0.15f);
        mechanics_need = (electricsOutput * 0.55f) + (militaryIndustryOutput * 0.65f);

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

        float modifierGeneralBudget = 0f;

        switch (this.country.getContinent()) {
            case EY:
                modifierLowWorkers = 0.3f;
                modifierLowWorkersSalary = 0.35f;

                modifierMiddleWorkers = 0.1f;
                modifierMiddleWorkersSalary = 0.65f;

                modifierHighWorkers = 0.05f;
                modifierHighWorkersSalary = 0.95f;

                modifierGeneralBudget = 0.15f;
                break;
            case NY:
                modifierLowWorkers = 0.2f;
                modifierLowWorkersSalary = 0.55f;

                modifierMiddleWorkers = 0.2f;
                modifierMiddleWorkersSalary = 0.85f;

                modifierHighWorkers = 0.1f;
                modifierHighWorkersSalary = 1.25f;

                modifierGeneralBudget = 0.2f;
                break;
            case SY:
                modifierLowWorkers = 0.35f;
                modifierLowWorkersSalary = 0.3f;

                modifierMiddleWorkers = 0.05f;
                modifierMiddleWorkersSalary = 0.75f;

                modifierHighWorkers = 0.05f;
                modifierHighWorkersSalary = 0.85f;

                modifierGeneralBudget = 0.13f;
                break;
            case WY:
                modifierLowWorkers = 0.1f;
                modifierLowWorkersSalary = 0.65f;

                modifierMiddleWorkers = 0.25f;
                modifierMiddleWorkersSalary = 0.95f;

                modifierHighWorkers = 0.15f;
                modifierHighWorkersSalary = 1.3f;

                modifierGeneralBudget = 0.18f;
                break;
            case IB:
                modifierLowWorkers = 0.25f;
                modifierLowWorkersSalary = 0.25f;

                modifierMiddleWorkers = 0.05f;
                modifierMiddleWorkersSalary = 0.5f;

                modifierHighWorkers = 0.04f;
                modifierHighWorkersSalary = 0.85f;

                modifierGeneralBudget = 0.1f;
                break;
            case OB:
                modifierLowWorkers = 0.2f;
                modifierLowWorkersSalary = 0.25f;

                modifierMiddleWorkers = 0.05f;
                modifierMiddleWorkersSalary = 0.45f;

                modifierHighWorkers = 0.03f;
                modifierHighWorkersSalary = 0.8f;

                modifierGeneralBudget = 0.1f;
                break;
            case CA:
                modifierLowWorkers = 0.15f;
                modifierLowWorkersSalary = 0.23f;

                modifierMiddleWorkers = 0.04f;
                modifierMiddleWorkersSalary = 0.45f;

                modifierHighWorkers = 0.02f;
                modifierHighWorkersSalary = 0.8f;

                modifierGeneralBudget = 0.08f;
                break;
            case FA:
                modifierLowWorkers = 0.12f;
                modifierLowWorkersSalary = 0.23f;

                modifierMiddleWorkers = 0.03f;
                modifierMiddleWorkersSalary = 0.45f;

                modifierHighWorkers = 0.02f;
                modifierHighWorkersSalary = 0.75f;

                modifierGeneralBudget = 0.08f;
                break;
            case ME:
                modifierLowWorkers = 0.1f;
                modifierLowWorkersSalary = 0.2f;

                modifierMiddleWorkers = 0.03f;
                modifierMiddleWorkersSalary = 0.4f;

                modifierHighWorkers = 0.01f;
                modifierHighWorkersSalary = 0.7f;

                modifierGeneralBudget = 0.07f;
                break;
            case GE:
                modifierLowWorkers = 0.1f;
                modifierLowWorkersSalary = 0.6f;

                modifierMiddleWorkers = 0.3f;
                modifierMiddleWorkersSalary = 1f;

                modifierHighWorkers = 0.2f;
                modifierHighWorkersSalary = 1.2f;

                modifierGeneralBudget = 0.17f;
                break;
        }
        this.lowWorkersLimit = (int) (this.economy.getLabor_force() * (modifierLowWorkers + ((random.nextFloat() * (0.005 - (-0.005)) + (-0.005)))));
        this.lowWorkers = this.lowWorkersLimit;
        this.lowWorkersSalary = (float) (this.economy.getGdpPerPerson() * (modifierLowWorkersSalary + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));

        this.middleWorkersLimit = (int) (this.economy.getLabor_force() * (modifierMiddleWorkers + ((random.nextFloat() * (0.005 - (-0.005)) + (-0.005)))));
        this.middleWorkers = this.middleWorkersLimit;
        this.middleWorkersSalary = (float) (this.economy.getGdpPerPerson() * (modifierMiddleWorkersSalary + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));

        this.highWorkersLimit = (int) (this.economy.getLabor_force() * (modifierHighWorkers + ((random.nextFloat() * (0.005 - (-0.005)) + (-0.005)))));
        this.highWorkers = this.highWorkersLimit;
        this.highWorkersSalary = (float) (this.economy.getGdpPerPerson() * (modifierHighWorkersSalary + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));

        this.generalBudget = (float) (this.economy.getBudget() * (modifierGeneralBudget + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));
    }

}
