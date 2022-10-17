package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

import java.util.Random;

public class MinistryOfDefense extends Ministry {
    private int generals;
    private int generalsLimit;
    private int generalsNeed;

    private float generalsSalary;
    private float generalsSalaryNeed;

    private int admirals;
    private int admiralsLimit;
    private int admiralsNeed;

    private float admiralsSalary;
    private float admiralsSalaryNeed;

    private int airForceOfficers;
    private int airForceOfficersLimit;
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
        string = "Army Power: " + this.armyPower + "\n";
        string += "Navy Power: " + this.navyPower + "\n";
        string += "Air Force Power: " + this.airPower + "\n";
        string += "Generals: " + this.generals + "\n";
        string += "Generals limit: " + this.generalsLimit + "\n";
        string += "Generals need: " + this.generalsNeed + "\n";
        string += "Generals salary: " + this.generalsSalary + " ƒ" + "\n";
        string += "Generals salary need: " + this.generalsSalaryNeed + " ƒ" + "\n";
        string += "Admirals: " + this.admirals + "\n";
        string += "Admirals limit: " + this.admiralsLimit + "\n";
        string += "Admirals need: " + this.admiralsNeed + "\n";
        string += "Admirals salary: " + this.admiralsSalary + " ƒ" + "\n";
        string += "Admirals salary need: " + this.admiralsSalaryNeed + " ƒ" + "\n";
        string += "Air Force officers: " + this.airForceOfficers + "\n";
        string += "Air Force officers limit: " + this.airForceOfficersLimit + "\n";
        string += "Air Force officers need: " + this.airForceOfficersNeed + "\n";
        string += "Air Force officers salary: " + this.airForceOfficersSalary + " ƒ" + "\n";
        string += "Air Force officers salary need: " + this.airForceOfficersSalaryNeed + " ƒ" + "\n";
        string += "General budget: " + this.generalBudget + " ƒ" + "\n";
        string += "General budget need: " + this.generalBudgetNeed + " ƒ" + "\n";
        string += "Conscription: " + (this.conscription ? "Yes" : "No") + "\n";
        string += "Landlocked: " + (this.economy.isLandlocked() ? "Yes" : "No") + "\n";
        return super.toString() + string;
    }

    @Override
    public void updateMinistry() {
        super.updateMinistry();

        this.generalBudgetNeed = (float) (this.economy.getGdp() * 0.12f) + (armyPower * 10f) + (navyPower * 50f) + (airPower * 80f);

        efficiency *= generalsSalary / generalsSalaryNeed;
        efficiency *= admiralsSalary / admiralsSalaryNeed;
        efficiency *= airForceOfficersSalary / airForceOfficersSalaryNeed;
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

        switch (this.country.getContinent()) {
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

                modifierGeneralBudget = 0.13f;
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

                modifierGeneralBudget = 0.11f;
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

                modifierGeneralBudget = 0.10f;
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

                modifierGeneralBudget = 0.15f;
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

                modifierGeneralBudget = 0.16f;
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

                modifierGeneralBudget = 0.18f;
                break;
            case CA:
                modifierGenerals = 0.5f;
                modifierGeneralsSalary = 2.3f;

                modifierAdmirals = 0.25f;
                modifierAdmiralsSalary = 2.55f;

                modifierAirForceOfficers = 0.1f;
                modifierAirForceOfficersSalary = 2.75f;

                modifierGeneralBudget = 0.12f;
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

                modifierGeneralBudget = 0.1f;
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

                modifierGeneralBudget = 0.18f;
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

                modifierGeneralBudget = 0.2f;
                break;
        }

        this.conscription = random.nextBoolean();

        this.armyPowerLimit = (int) ((this.economy.getLabor_force() / (conscription ? 450f : 650f)) * (modifierArmy + random.nextFloat() * (0.03 - (-0.03)) + (-0.03)));
        this.armyPower = this.armyPowerLimit;

        this.navyPowerLimit = (int) ((this.economy.getLabor_force() / (conscription ? 1050f : 1350f)) * (modifierNavy + random.nextFloat() * (0.03 - (-0.03)) + (-0.03)) * (this.economy.isLandlocked() ? 0 : 1));
        this.navyPower = this.navyPowerLimit;

        this.airPowerLimit = (int) ((this.economy.getLabor_force() / (conscription ? 1450f : 1850f)) * (modifierAir + random.nextFloat() * (0.03 - (-0.03)) + (-0.03)));
        this.airPower = this.airPowerLimit;

        this.generalsNeed = armyPower / 100;
        this.admiralsNeed = navyPower / 80;
        this.airForceOfficersNeed = airPower / 50;

        this.generalsSalaryNeed = (int) (economy.getGdpPerPerson() * 3.25);
        this.admiralsSalaryNeed = (int) (economy.getGdpPerPerson() * 3.55 * (this.economy.isLandlocked() ? 0 : 1));
        this.airForceOfficersSalaryNeed = (int) (economy.getGdpPerPerson() * 3.85);

        this.generalsLimit = (int) (this.generalsNeed * (modifierGenerals + random.nextFloat() * (0.03 - (-0.03)) + (-0.03)));
        this.generals = this.generalsLimit;
        this.generalsSalary = (float) (this.economy.getGdpPerPerson() * (modifierGeneralsSalary + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));

        this.admiralsLimit = (int) (this.admiralsNeed * (modifierAdmirals + random.nextFloat() * (0.03 - (-0.03)) + (-0.03)) * (this.economy.isLandlocked() ? 0 : 1));
        this.admirals = this.admiralsLimit;
        this.admiralsSalary = (float) (this.economy.getGdpPerPerson() * (modifierAdmiralsSalary + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))) * (this.economy.isLandlocked() ? 0 : 1));

        this.airForceOfficersLimit = (int) (this.airForceOfficersNeed * (modifierAirForceOfficers + random.nextFloat() * (0.03 - (-0.03)) + (-0.03)));
        this.airForceOfficers = this.airForceOfficersLimit;
        this.airForceOfficersSalary = (float) (this.economy.getGdpPerPerson() * (modifierAirForceOfficersSalary + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));

        this.generalBudget = (float) (this.economy.getBudget() * (modifierGeneralBudget + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));
    }
}
