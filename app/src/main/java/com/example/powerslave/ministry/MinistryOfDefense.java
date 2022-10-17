package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

import java.util.Random;

public class MinistryOfDefense extends Ministry {
    private int generals;
    private int generalsLimit;
    private int maximumGeneralsLimit;
    private int generalsNeed;

    private float generalsSalary;
    private float generalsSalaryNeed;

    private int admirals;
    private int admiralsLimit;
    private int maximumAdmiralsLimit;
    private int admiralsNeed;

    private float admiralsSalary;
    private float admiralsSalaryNeed;

    private int airForceOfficers;
    private int airForceOfficersLimit;
    private int maximumAirForceOfficersLimit;
    private int airForceOfficersNeed;

    private float airForceOfficersSalary;
    private float airForceOfficersSalaryNeed;

    private int armyPower;
    private int armyPowerLimit;

    private int navyPower;
    private int navyPowerLimit;

    private int airPower;
    private int airPowerLimit;

    private boolean conscription;

    private float fuelNeed;
    private float militaryIndustryNeed;

    private MinistryOfEconomy economy;

    public MinistryOfDefense(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);
        this.name = "Ministry of Defense";
        this.economy = country.getMinistryOfEconomy();

        statsRandomizer();
    }


    @Override
    public String toString() {
        String string;
        string = "Army Power: " + armyPower + "\n";
        string += "Navy Power: " + navyPower + "\n";
        string += "Air Force Power: " + airPower + "\n";
        string += "Generals: " + generals + "\n";
        string += "Generals limit: " + generalsLimit + " (Maximum - " + maximumGeneralsLimit + ")" + "\n";
        string += "Generals need: " + generalsNeed + "\n";
        string += "Generals salary: " + String.format("%.2f", generalsSalary) + " ƒ" + "\n";
        string += "Generals salary need: " + String.format("%.2f", generalsSalaryNeed) + " ƒ" + "\n";
        string += "Admirals: " + admirals + "\n";
        string += "Admirals limit: " + admiralsLimit + " (Maximum - " + maximumAdmiralsLimit + ")" + "\n";
        string += "Admirals need: " + admiralsNeed + "\n";
        string += "Admirals salary: " + String.format("%.2f", admiralsSalary) + " ƒ" + "\n";
        string += "Admirals salary need: " + String.format("%.2f", admiralsSalaryNeed) + " ƒ" + "\n";
        string += "Air Force officers: " + airForceOfficers + "\n";
        string += "Air Force officers limit: " + airForceOfficersLimit + " (Maximum - " + maximumAirForceOfficersLimit + ")" + "\n";
        string += "Air Force officers need: " + airForceOfficersNeed + "\n";
        string += "Air Force officers salary: " + String.format("%.2f", airForceOfficersSalary) + " ƒ" + "\n";
        string += "Air Force officers salary need: " + String.format("%.2f", airForceOfficersSalaryNeed) + " ƒ" + "\n";
        string += "General budget: " + String.format("%.2f", generalBudget) + " ƒ" + "\n";
        string += "General budget need: " + String.format("%.2f", generalBudgetNeed) + " ƒ" + "\n";
        string += "Fuel need: " + fuelNeed + " units" + "\n";
        string += "Military industry need: " + militaryIndustryNeed + " units" + "\n";
        string += "Conscription: " + (conscription ? "Yes" : "No") + "\n";
        string += "Landlocked: " + (economy.isLandlocked() ? "Yes" : "No") + "\n";
        return super.toString() + string;
    }

    public float getFuelNeed() {
        return fuelNeed;
    }

    public float getMilitaryIndustryNeed() {
        return militaryIndustryNeed;
    }

    @Override
    public void updateMinistry() {
        super.updateMinistry();

        efficiency *= generalBudget / generalBudgetNeed;
        efficiency *= conscription ? 0.95 : 1.05;
    }

    @Override
    public void statsRandomizer() {
        super.statsRandomizer();
        Random random = new Random();

        float modifierGenerals = 0f;
        float modifierGeneralsSalary = 0f;

        float modifierAdmirals = 0f;
        float modifierAdmiralsSalary = 0f;

        float modifierAirForceOfficers = 0f;
        float modifierAirForceOfficersSalary = 0f;

        float modifierArmy = 0f;
        float modifierNavy = 0f;
        float modifierAir = 0f;

        float modifierGeneralBudget = 0f;

        switch (country.getContinent()) {
            case EY:
                modifierGenerals = 0.65f;
                modifierGeneralsSalary = 3f;

                modifierAdmirals = 0.5f;
                modifierAdmiralsSalary = 3.15f;

                modifierAirForceOfficers = 0.35f;
                modifierAirForceOfficersSalary = 3.25f;

                modifierArmy = 0.7f;
                modifierNavy = 0.5f;
                modifierAir = 0.3f;

                modifierGeneralBudget = 0.93f;
                break;
            case NY:
                modifierGenerals = 0.75f;
                modifierGeneralsSalary = 3.25f;

                modifierAdmirals = 0.55f;
                modifierAdmiralsSalary = 3.35f;

                modifierAirForceOfficers = 0.45f;
                modifierAirForceOfficersSalary = 3.45f;

                modifierArmy = 0.75f;
                modifierNavy = 0.6f;
                modifierAir = 0.4f;

                modifierGeneralBudget = 0.81f;
                break;
            case SY:
                modifierGenerals = 0.55f;
                modifierGeneralsSalary = 2.85f;

                modifierAdmirals = 0.45f;
                modifierAdmiralsSalary = 3.05f;

                modifierAirForceOfficers = 0.3f;
                modifierAirForceOfficersSalary = 3.15f;

                modifierArmy = 0.5f;
                modifierNavy = 0.4f;
                modifierAir = 0.2f;

                modifierGeneralBudget = 0.70f;
                break;
            case WY:
                modifierGenerals = 0.8f;
                modifierGeneralsSalary = 3.45f;

                modifierAdmirals = 0.6f;
                modifierAdmiralsSalary = 3.75f;

                modifierAirForceOfficers = 0.55f;
                modifierAirForceOfficersSalary = 3.95f;

                modifierArmy = 0.8f;
                modifierNavy = 0.6f;
                modifierAir = 0.4f;

                modifierGeneralBudget = 0.85f;
                break;
            case IB:
                modifierGenerals = 0.6f;
                modifierGeneralsSalary = 2.5f;

                modifierAdmirals = 0.35f;
                modifierAdmiralsSalary = 2.85f;

                modifierAirForceOfficers = 0.15f;
                modifierAirForceOfficersSalary = 3f;

                modifierArmy = 0.45f;
                modifierNavy = 0.25f;
                modifierAir = 0.15f;

                modifierGeneralBudget = 0.66f;
                break;
            case OB:
                modifierGenerals = 0.55f;
                modifierGeneralsSalary = 2.4f;

                modifierAdmirals = 0.35f;
                modifierAdmiralsSalary = 2.85f;

                modifierAirForceOfficers = 0.15f;
                modifierAirForceOfficersSalary = 2.9f;

                modifierArmy = 0.4f;
                modifierNavy = 0.2f;
                modifierAir = 0.1f;

                modifierGeneralBudget = 0.58f;
                break;
            case CA:
                modifierGenerals = 0.5f;
                modifierGeneralsSalary = 2.3f;

                modifierAdmirals = 0.25f;
                modifierAdmiralsSalary = 2.55f;

                modifierAirForceOfficers = 0.1f;
                modifierAirForceOfficersSalary = 2.75f;

                modifierGeneralBudget = 0.52f;
                break;
            case FA:
                modifierGenerals = 0.45f;
                modifierGeneralsSalary = 2.25f;

                modifierAdmirals = 0.25f;
                modifierAdmiralsSalary = 2.5f;

                modifierAirForceOfficers = 0.1f;
                modifierAirForceOfficersSalary = 2.65f;

                modifierArmy = 0.35f;
                modifierNavy = 0.2f;
                modifierAir = 0.1f;

                modifierGeneralBudget = 0.6f;
                break;
            case ME:
                modifierGenerals = 0.35f;
                modifierGeneralsSalary = 2f;

                modifierAdmirals = 0.2f;
                modifierAdmiralsSalary = 2.15f;

                modifierAirForceOfficers = 0.05f;
                modifierAirForceOfficersSalary = 2.35f;

                modifierArmy = 0.3f;
                modifierNavy = 0.1f;
                modifierAir = 0.05f;

                modifierGeneralBudget = 0.58f;
                break;
            case GE:
                modifierGenerals = 0.85f;
                modifierGeneralsSalary = 3.5f;

                modifierAdmirals = 0.7f;
                modifierAdmiralsSalary = 3.85f;

                modifierAirForceOfficers = 0.65f;
                modifierAirForceOfficersSalary = 4f;

                modifierArmy = 0.75f;
                modifierNavy = 0.7f;
                modifierAir = 0.65f;

                modifierGeneralBudget = 0.7f;
                break;
        }

        conscription = random.nextBoolean();

        armyPowerLimit = (int) ((economy.getLabor_force() / (conscription ? 450f : 650f)) * (modifierArmy + random.nextFloat() * (0.03 - (-0.03)) + (-0.03)));
        armyPower = armyPowerLimit;

        navyPowerLimit = (int) ((economy.getLabor_force() / (conscription ? 1050f : 1350f)) * (modifierNavy + random.nextFloat() * (0.03 - (-0.03)) + (-0.03)) * (economy.isLandlocked() ? 0 : 1));
        navyPower = navyPowerLimit;

        airPowerLimit = (int) ((economy.getLabor_force() / (conscription ? 1450f : 1850f)) * (modifierAir + random.nextFloat() * (0.03 - (-0.03)) + (-0.03)));
        airPower = airPowerLimit;

        generalsNeed = armyPower / 100;
        admiralsNeed = navyPower / 80;
        airForceOfficersNeed = airPower / 50;

        generalsSalaryNeed = (int) (economy.getGdpPerPerson() * 3.25);
        admiralsSalaryNeed = (int) (economy.getGdpPerPerson() * 3.55 * (economy.isLandlocked() ? 0 : 1));
        airForceOfficersSalaryNeed = (int) (economy.getGdpPerPerson() * 3.85);

        generalsLimit = (int) (generalsNeed * (modifierGenerals + random.nextFloat() * (0.03 - (-0.03)) + (-0.03)));
        generals = generalsLimit;
        generalsSalary = (float) (economy.getGdpPerPerson() * (modifierGeneralsSalary + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));

        admiralsLimit = (int) (admiralsNeed * (modifierAdmirals + random.nextFloat() * (0.03 - (-0.03)) + (-0.03)) * (economy.isLandlocked() ? 0 : 1));
        admirals = admiralsLimit;
        admiralsSalary = (float) (economy.getGdpPerPerson() * (modifierAdmiralsSalary + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))) * (economy.isLandlocked() ? 0 : 1));

        airForceOfficersLimit = (int) (airForceOfficersNeed * (modifierAirForceOfficers + random.nextFloat() * (0.03 - (-0.03)) + (-0.03)));
        airForceOfficers = airForceOfficersLimit;
        airForceOfficersSalary = (float) (economy.getGdpPerPerson() * (modifierAirForceOfficersSalary + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));

        generalBudgetNeed = (armyPower * 100f) + (navyPower * 500f) + (airPower * 800f) + (generals * generalsSalary) + (admirals * admiralsSalary) + (airForceOfficers * airForceOfficersSalary);
        generalBudget = (float) (generalBudgetNeed * (modifierGeneralBudget + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));
        fuelNeed = armyPower * 1.3f + navyPower * 2.9f + airPower * 3.8f;
        militaryIndustryNeed = armyPower * 0.3f + navyPower * 1.3f + airPower * 1.8f;

        maximumGeneralsLimit = generalsNeed * 10;
        maximumAdmiralsLimit = admiralsNeed * 10;
        maximumAirForceOfficersLimit = airForceOfficersNeed * 10;
    }
}
