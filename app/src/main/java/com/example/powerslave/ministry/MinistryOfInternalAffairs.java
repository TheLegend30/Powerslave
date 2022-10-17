package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

import java.util.Random;

public class MinistryOfInternalAffairs extends Ministry {

    private int policemen;
    private int policemenLimit;
    private int policemenNeed;
    private float policemenSalary;
    private float policemenSalaryNeed;

    private int departments;
    private int departmentsLimit;

    private int departmentsNeed;

    private int crime;

    private int levelOfSecurity;

    private MinistryOfEconomy economy;

    public MinistryOfInternalAffairs(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);

        this.name = "Ministry of Internal Affairs";
        economy = country.getMinistryOfEconomy();

        statsRandomizer();
    }

    @Override
    public String toString() {
        String string;
        string = "Crime per 100,000 people: " + this.crime + "\n";
        string += "Level of security (1 - minimum, 6 - maximum): " + this.levelOfSecurity + "\n";
        string += "Policemen: " + this.policemen + "\n";
        string += "Policemen limit: " + this.policemenLimit + "\n";
        string += "Policemen salary: " + this.policemenSalary + " ƒ" + "\n";
        string += "Policemen salary need: " + this.policemenSalaryNeed + " ƒ" + "\n";
        string += "Departments: " + this.departments + "\n";
        string += "Policemen need: " + this.policemenNeed + "\n";
        string += "Departments need: " + this.departmentsNeed + "\n";
        return super.toString() + string;
    }

    @Override
    public void updateMinistry() {
        super.updateMinistry();
        this.efficiency *= ((float) departments / (float) departmentsNeed);
        this.efficiency *= (policemenSalary / policemenSalaryNeed);
        this.efficiency *= ((float) policemen / (float) policemenNeed);
        this.efficiency *= ((float) levelOfSecurity / 4f);
    }

    @Override
    public void statsRandomizer() {
        super.statsRandomizer();

        Random random = new Random();

        float modifierPolicemen = 0f;
        float modifierPolicemenSalary = 0f;

        float modifierDepartments = 0f;
        int modifierCrime = 0;

        int modifierLevelOfSecurity = 0;

        switch (this.country.getContinent()) {
            case EY:
                modifierPolicemen = 0.032f;
                modifierPolicemenSalary = 0.7f;

                modifierDepartments = 0.7f;
                modifierCrime = 3000;
                modifierLevelOfSecurity = 4;
                break;
            case NY:
                modifierPolicemen = 0.04f;
                modifierPolicemenSalary = 0.8f;

                modifierDepartments = 0.8f;
                modifierCrime = 2300;

                modifierLevelOfSecurity = 2;
                break;
            case SY:
                modifierPolicemen = 0.03f;
                modifierPolicemenSalary = 0.72f;

                modifierDepartments = 0.65f;
                modifierCrime = 3150;

                modifierLevelOfSecurity = 3;
                break;
            case WY:
                modifierPolicemen = 0.042f;
                modifierPolicemenSalary = 0.83f;

                modifierDepartments = 0.82f;
                modifierCrime = 2350;

                modifierLevelOfSecurity = 2;
                break;
            case IB:
                modifierPolicemen = 0.015f;
                modifierPolicemenSalary = 0.6f;

                modifierDepartments = 0.55f;
                modifierCrime = 3565;

                modifierLevelOfSecurity = 3;
                break;
            case OB:
                modifierPolicemen = 0.013f;
                modifierPolicemenSalary = 0.58f;

                modifierDepartments = 0.5f;
                modifierCrime = 3690;

                modifierLevelOfSecurity = 3;
                break;
            case CA:
                modifierPolicemen = 0.011f;
                modifierPolicemenSalary = 0.5f;

                modifierDepartments = 0.48f;
                modifierCrime = 4165;

                modifierLevelOfSecurity = 3;
                break;
            case FA:
                modifierPolicemen = 0.01f;
                modifierPolicemenSalary = 0.45f;

                modifierDepartments = 0.45f;
                modifierCrime = 4570;

                modifierLevelOfSecurity = 3;
                break;
            case ME:
                modifierPolicemen = 0.007f;
                modifierPolicemenSalary = 0.35f;

                modifierDepartments = 0.37f;
                modifierCrime = 4965;

                modifierLevelOfSecurity = 3;
                break;
            case GE:
                modifierPolicemen = 0.035f;
                modifierPolicemenSalary = 0.75f;

                modifierDepartments = 0.73f;
                modifierCrime = 2390;

                modifierLevelOfSecurity = 4;
                break;
        }

        this.policemenLimit = (int) (this.economy.getLabor_force() * (modifierPolicemen + (random.nextFloat() * (0.002 - (-0.002)) + (-0.02))));
        this.policemen = this.policemenLimit;

        this.policemenSalary = (float) (this.economy.getGdpPerPerson() * (modifierPolicemenSalary + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));


        this.policemenNeed = (int) (this.economy.getPopulation() / 200.0);
        this.policemenSalaryNeed = (float) (this.economy.getGdpPerPerson() / 1.25);
        this.departmentsNeed = (int) Math.ceil(policemen / 300.0);


        this.departmentsLimit = (int) (this.departmentsNeed * (modifierDepartments + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));
        this.departments = this.departmentsLimit;

        this.crime = (int) (modifierCrime + (random.nextInt((500 - (-500)) + 1) + (-500)));

        this.levelOfSecurity = (modifierLevelOfSecurity + (random.nextInt((1 - (-1))) + (-1)));

    }

    public int getCrime() {
        return crime;
    }
}
