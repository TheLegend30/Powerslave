package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

import java.util.Random;

public class MinistryOfAgriculture extends Ministry {
    private int farmers;
    private int farmersLimit;
    private float farmersSalary;
    private float farmersSalaryNeed;

    private int miners;
    private int minersLimit;
    private float minersSalary;
    private float minersSalaryNeed;

    private float rawFoodOutput;
    private float rawOutput;

    private float rawFoodReserve = 0;
    private float rawReserve = 0;

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
        string = "Farmers: " + this.farmers + "\n";
        string = "Farmers limit: " + this.farmersLimit + "\n";
        string += "Farmers' salary: " + (this.farmersSalary / this.economy.getCurrencyToGulden()) + " " + this.economy.getCurrency() + "\n";
        string += "Farmers' need salary: " + (this.farmersSalaryNeed / this.economy.getCurrencyToGulden()) + " " + this.economy.getCurrency() + "\n";

        string += "Miners: " + this.miners + "\n";
        string += "Miners limit: " + this.minersLimit + "\n";
        string += "Miners' salary: " + (this.minersSalary / this.economy.getCurrencyToGulden()) + " " + this.economy.getCurrency() + "\n";
        string += "Miners' need salary: " + (this.minersSalaryNeed / this.economy.getCurrencyToGulden()) + " " + this.economy.getCurrency() + "\n";

        string += "General budget: " + generalBudget + " ƒ" + "\n";
        string += "General need budget: " + generalBudgetNeed + " ƒ" + "\n";

        string += "Raw food output: " + this.rawFoodOutput + " units" + "\n";
        string += "Raw food reserve: " + this.rawFoodReserve + " units" + "\n";
        string += "Raw output: " + this.rawOutput + " units" + "\n";
        string += "Raw reserve: " + this.rawReserve + " units" + "\n";

        return super.toString() + "\n" + string;
    }

    @Override
    public void updateMinistry() {
        super.updateMinistry();
        this.farmersSalaryNeed = this.economy.getGdpPerPerson() / 4;
        this.minersSalaryNeed = this.economy.getGdpPerPerson() / 3.5f;
        this.generalBudgetNeed = (this.economy.getBudget() * 0.1f) + ((miners * 0.05f) + (farmers * 0.01f));

        this.efficiency *= ((generalBudget + minersSalary + farmersSalary) / (generalBudgetNeed + minersSalaryNeed + farmersSalaryNeed));
        this.rawFoodOutput = efficiency * farmers * 2.35f;
        this.rawOutput = efficiency * miners * 1.95f;
    }

    @Override
    public void statsRandomizer() {
        super.statsRandomizer();
        Random random = new Random();

        float modifierFarmers = 0f;
        float modifierFarmersSalary = 0f;

        float modifierMiners = 0f;
        float modifierMinersSalary = 0f;

        float modifierGeneralBudget = 0f;

        switch (this.country.getContinent()) {
            case EY:
                modifierFarmers = 0.15f;
                modifierFarmersSalary = 0.10f;

                modifierMiners = 0.15f;
                modifierMinersSalary = 0.12f;

                modifierGeneralBudget = 0.08f;
                break;
            case NY:
                modifierFarmers = 0.055f;
                modifierFarmersSalary = 0.25f;

                modifierMiners = 0.06f;
                modifierMinersSalary = 0.28f;

                modifierGeneralBudget = 0.05f;
                break;
            case SY:
                modifierFarmers = 0.13f;
                modifierFarmersSalary = 0.12f;

                modifierMiners = 0.12f;
                modifierMinersSalary = 0.14f;

                modifierGeneralBudget = 0.10f;
                break;
            case WY:
                modifierFarmers = 0.05f;
                modifierFarmersSalary = 0.28f;

                modifierMiners = 0.045f;
                modifierMinersSalary = 0.30f;

                modifierGeneralBudget = 0.04f;
                break;
            case IB:
                modifierFarmers = 0.25f;
                modifierFarmersSalary = 0.09f;

                modifierMiners = 0.20f;
                modifierMinersSalary = 0.11f;

                modifierGeneralBudget = 0.20f;
                break;
            case OB:
                modifierFarmers = 0.26f;
                modifierFarmersSalary = 0.085f;

                modifierMiners = 0.18f;
                modifierMinersSalary = 0.10f;

                modifierGeneralBudget = 0.22f;
                break;
            case CA:
                modifierFarmers = 0.20f;
                modifierFarmersSalary = 0.07f;

                modifierMiners = 0.18f;
                modifierMinersSalary = 0.09f;

                modifierGeneralBudget = 0.18f;
                break;
            case FA:
                modifierFarmers = 0.35f;
                modifierFarmersSalary = 0.065f;

                modifierMiners = 0.30f;
                modifierMinersSalary = 0.8f;

                modifierGeneralBudget = 0.19f;
                break;
            case ME:
                modifierFarmers = 0.45f;
                modifierFarmersSalary = 0.05f;

                modifierMiners = 0.32f;
                modifierMinersSalary = 0.075f;

                modifierGeneralBudget = 0.45f;
                break;
            case GE:
                modifierFarmers = 0.08f;
                modifierFarmersSalary = 0.20f;

                modifierMiners = 0.15f;
                modifierMinersSalary = 0.23f;

                modifierGeneralBudget = 0.075f;
                break;
        }


        this.farmersLimit = (int) (this.economy.getLabor_force() * (modifierFarmers + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));
        this.farmers = this.farmersLimit;
        this.farmersSalary = (float) (this.economy.getGdpPerPerson() * (modifierFarmersSalary + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));

        this.minersLimit = (int) (this.economy.getLabor_force() * (modifierMiners + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));
        this.miners = this.minersLimit;
        this.minersSalary = (float) (this.economy.getGdpPerPerson() * (modifierMinersSalary + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));

        this.generalBudget = (float) (this.economy.getBudget() * (modifierGeneralBudget + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));
    }
}

