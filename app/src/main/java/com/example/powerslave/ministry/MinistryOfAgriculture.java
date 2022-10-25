package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

import java.util.Random;

public class MinistryOfAgriculture extends Ministry {
    private int farmers;
    private int farmersLimit;
    private int maximumFarmersLimit;
    private float farmersSalary;
    private float farmersSalaryNeed;

    private int miners;
    private int minersLimit;
    private int maximumMinersLimit;
    private float minersSalary;
    private float minersSalaryNeed;

    private float rawFoodOutput;
    private float rawOutput;

    private MinistryOfEconomy economy;


    public MinistryOfAgriculture(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);
        this.name = "Ministry of Agriculture and Mining";
        this.economy = country.getMinistryOfEconomy();

        statsRandomizer();
    }

    @Override
    public String toString() {
        String string;
        string = "Farmers: " + farmers + "\n";
        string += "Farmers limit: " + farmersLimit + " (Maximum - " + maximumFarmersLimit + ")" + "\n";
        string += "Farmers' salary: " + String.format("%.2f", farmersSalary) + " ƒ" + "\n";
        string += "Farmers' salary need : " + String.format("%.2f", farmersSalaryNeed) + " ƒ" + "\n";
        string += "Miners: " + miners + "\n";
        string += "Miners limit: " + minersLimit + " (Maximum - " + maximumMinersLimit + ")" + "\n";
        string += "Miners' salary: " + String.format("%.2f", minersSalary) + " ƒ" + "\n";
        string += "Miners' salary need : " + String.format("%.2f", minersSalaryNeed) + " ƒ" + "\n";
        string += "General budget: " + String.format("%.2f", generalBudget) + " ƒ" + "\n";
        string += "General budget need : " + String.format("%.2f", generalBudgetNeed) + " ƒ" + "\n";
        string += "Raw food output: " + rawFoodOutput + " units" + "\n";
        string += "Raw output: " + rawOutput + " units" + "\n";
        return super.toString() + string;
    }

    public int getFarmers() {
        return farmers;
    }

    public void setFarmers(int farmers) {
        this.farmers = farmers;
    }

    public int getFarmersLimit() {
        return farmersLimit;
    }

    public void setFarmersLimit(int farmersLimit) {
        this.farmersLimit = farmersLimit;
    }

    public int getMaximumFarmersLimit() {
        return maximumFarmersLimit;
    }

    public void setMaximumFarmersLimit(int maximumFarmersLimit) {
        this.maximumFarmersLimit = maximumFarmersLimit;
    }

    public float getFarmersSalary() {
        return farmersSalary;
    }

    public void setFarmersSalary(float farmersSalary) {
        this.farmersSalary = farmersSalary;
    }

    public float getFarmersSalaryNeed() {
        return farmersSalaryNeed;
    }

    public void setFarmersSalaryNeed(float farmersSalaryNeed) {
        this.farmersSalaryNeed = farmersSalaryNeed;
    }

    public int getMiners() {
        return miners;
    }

    public void setMiners(int miners) {
        this.miners = miners;
    }

    public int getMinersLimit() {
        return minersLimit;
    }

    public void setMinersLimit(int minersLimit) {
        this.minersLimit = minersLimit;
    }

    public int getMaximumMinersLimit() {
        return maximumMinersLimit;
    }

    public void setMaximumMinersLimit(int maximumMinersLimit) {
        this.maximumMinersLimit = maximumMinersLimit;
    }

    public float getMinersSalary() {
        return minersSalary;
    }

    public void setMinersSalary(float minersSalary) {
        this.minersSalary = minersSalary;
    }

    public float getMinersSalaryNeed() {
        return minersSalaryNeed;
    }

    public void setMinersSalaryNeed(float minersSalaryNeed) {
        this.minersSalaryNeed = minersSalaryNeed;
    }

    public float getRawFoodOutput() {
        return rawFoodOutput;
    }

    public void setRawFoodOutput(float rawFoodOutput) {
        this.rawFoodOutput = rawFoodOutput;
    }

    public float getRawOutput() {
        return rawOutput;
    }

    public void setRawOutput(float rawOutput) {
        this.rawOutput = rawOutput;
    }

    @Override
    public void updateMinistry() {
        super.updateMinistry();
        farmersSalaryNeed = economy.getGdpPerPerson() * 0.25f;
        minersSalaryNeed = economy.getGdpPerPerson() * 0.29f;
        maximumFarmersLimit = (int) (economy.getLaborForce() * 0.55);
        maximumMinersLimit = (int) (economy.getLaborForce() * 0.4);

        generalBudgetNeed = (miners * 0.015f) + (farmers * 0.01f) + (miners * minersSalary) + (farmers * farmersSalary);
        generalBudget += (miners * minersSalary) + (farmers * farmersSalary);

        efficiency *= generalBudget / generalBudgetNeed;
        rawFoodOutput = efficiency * farmers * 2.35f;
        rawOutput = efficiency * miners * 1.95f;
    }

    @Override
    public void statsRandomizer() {
        super.statsRandomizer();
        Random random = new Random();

        float modifierFarmers = 0f;
        float modifierFarmersSalary = 0f;

        float modifierMiners = 0f;
        float modifierMinersSalary = 0f;

        switch (country.getContinent()) {
            case EY:
                modifierFarmers = 0.15f;
                modifierFarmersSalary = 0.10f;

                modifierMiners = 0.15f;
                modifierMinersSalary = 0.12f;

                break;
            case NY:
                modifierFarmers = 0.055f;
                modifierFarmersSalary = 0.25f;

                modifierMiners = 0.06f;
                modifierMinersSalary = 0.28f;

                break;
            case SY:
                modifierFarmers = 0.13f;
                modifierFarmersSalary = 0.12f;

                modifierMiners = 0.12f;
                modifierMinersSalary = 0.14f;

                break;
            case WY:
                modifierFarmers = 0.05f;
                modifierFarmersSalary = 0.28f;

                modifierMiners = 0.045f;
                modifierMinersSalary = 0.30f;

                break;
            case IB:
                modifierFarmers = 0.25f;
                modifierFarmersSalary = 0.09f;

                modifierMiners = 0.20f;
                modifierMinersSalary = 0.11f;

                break;
            case OB:
                modifierFarmers = 0.26f;
                modifierFarmersSalary = 0.085f;

                modifierMiners = 0.18f;
                modifierMinersSalary = 0.10f;

                break;
            case CA:
                modifierFarmers = 0.20f;
                modifierFarmersSalary = 0.07f;

                modifierMiners = 0.18f;
                modifierMinersSalary = 0.09f;

                break;
            case FA:
                modifierFarmers = 0.35f;
                modifierFarmersSalary = 0.065f;

                modifierMiners = 0.30f;
                modifierMinersSalary = 0.8f;

                break;
            case ME:
                modifierFarmers = 0.45f;
                modifierFarmersSalary = 0.05f;

                modifierMiners = 0.32f;
                modifierMinersSalary = 0.075f;

                break;
            case GE:
                modifierFarmers = 0.08f;
                modifierFarmersSalary = 0.20f;

                modifierMiners = 0.15f;
                modifierMinersSalary = 0.23f;

                break;
        }


        farmersLimit = (int) (economy.getLaborForce() * (modifierFarmers + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));
        farmers = farmersLimit;
        farmersSalary = (float) (economy.getGdpPerPerson() * (modifierFarmersSalary + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));

        minersLimit = (int) (economy.getLaborForce() * (modifierMiners + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));
        miners = minersLimit;
        minersSalary = (float) (economy.getGdpPerPerson() * (modifierMinersSalary + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));

    }

    @Override
    public void workersIncreasing() {
        super.workersIncreasing();
        // TODO: Make if else statement ?
        farmers += farmersLimit * 0.7 * country.getMinistryOfEducation().efficiency * efficiency;
        if (farmers > farmersLimit) farmers = farmersLimit;
        miners += minersLimit * 0.7 * country.getMinistryOfEducation().efficiency * efficiency;
        if (miners > minersLimit) miners = minersLimit;
    }
}

