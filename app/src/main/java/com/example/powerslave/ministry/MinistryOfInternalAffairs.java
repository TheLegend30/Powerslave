package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

import java.util.Random;

public class MinistryOfInternalAffairs extends Ministry {

    private int policemen;
    private int policemenLimit;
    private int maximumPolicemenLimit;
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

    public int getLevelOfSecurity() {
        return levelOfSecurity;
    }

    public int getPolicemen() {
        return policemen;
    }

    public void setPolicemen(int policemen) {
        this.policemen = policemen;
    }

    public int getPolicemenLimit() {
        return policemenLimit;
    }

    public void setPolicemenLimit(int policemenLimit) {
        this.policemenLimit = policemenLimit;
    }

    public int getMaximumPolicemenLimit() {
        return maximumPolicemenLimit;
    }

    public void setMaximumPolicemenLimit(int maximumPolicemenLimit) {
        this.maximumPolicemenLimit = maximumPolicemenLimit;
    }

    public int getPolicemenNeed() {
        return policemenNeed;
    }

    public void setPolicemenNeed(int policemenNeed) {
        this.policemenNeed = policemenNeed;
    }

    public float getPolicemenSalary() {
        return policemenSalary;
    }

    public void setPolicemenSalary(float policemenSalary) {
        this.policemenSalary = policemenSalary;
    }

    public float getPolicemenSalaryNeed() {
        return policemenSalaryNeed;
    }

    public void setPolicemenSalaryNeed(float policemenSalaryNeed) {
        this.policemenSalaryNeed = policemenSalaryNeed;
    }

    public int getDepartments() {
        return departments;
    }

    public void setDepartments(int departments) {
        this.departments = departments;
    }

    public int getDepartmentsLimit() {
        return departmentsLimit;
    }

    public void setDepartmentsLimit(int departmentsLimit) {
        this.departmentsLimit = departmentsLimit;
    }

    public int getDepartmentsNeed() {
        return departmentsNeed;
    }

    public void setDepartmentsNeed(int departmentsNeed) {
        this.departmentsNeed = departmentsNeed;
    }

    public void setCrime(int crime) {
        this.crime = crime;
    }

    public void setLevelOfSecurity(int levelOfSecurity) {
        this.levelOfSecurity = levelOfSecurity;
    }

    @Override
    public String toString() {
        String string;
        string = "Crime per 100,000 people: " + crime + "\n";
        string += "Level of security (1 - minimum, 6 - maximum): " + levelOfSecurity + "\n";
        string += "Policemen: " + policemen + "\n";
        string += "Policemen limit: " + policemenLimit + " (Maximum - " + maximumPolicemenLimit + ")" + "\n";
        string += "Policemen salary: " + String.format("%.2f", policemenSalary) + " ƒ" + "\n";
        string += "Policemen salary need: " + String.format("%.2f", policemenSalaryNeed) + " ƒ" + "\n";
        string += "Policemen need: " + policemenNeed + "\n";
        string += "Departments: " + departments + "\n";
        string += "Departments need: " + departmentsNeed + "\n";
        string += "General budget: " + String.format("%.2f", generalBudget) + " ƒ" + "\n";
        string += "General need budget: " + String.format("%.2f", generalBudgetNeed) + " ƒ" + "\n";
        return super.toString() + string;
    }

    @Override
    public void updateMinistry() {
        super.updateMinistry();

        policemenNeed = (int) (economy.getPopulation() / 200.0);
        policemenSalaryNeed = economy.getGdpPerPerson() * 0.8f;
        departmentsNeed = (int) Math.ceil(policemen / 300.0);

        generalBudgetNeed = (policemen * 0.75f) + (departments * 450f) + (policemen * policemenSalaryNeed);
        generalBudget += (policemen * policemenSalary);
        maximumPolicemenLimit = policemenNeed * 8;

        efficiency *= ((float) departments / (float) departmentsNeed);
        efficiency *= ((float) policemen / (float) policemenNeed);
        efficiency *= ((float) levelOfSecurity / 4f);
        efficiency *= generalBudget / generalBudgetNeed;
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

        switch (country.getContinent()) {
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

        policemenLimit = (int) (economy.getLaborForce() * (modifierPolicemen + (random.nextFloat() * (0.002f - (-0.002f)) + (-0.02f))));
        policemen = policemenLimit;
        policemenSalary = economy.getGdpPerPerson() * (modifierPolicemenSalary + (random.nextFloat() * (0.01f - (-0.01f)) + (-0.01f)));

        policemenNeed = (int) (economy.getPopulation() / 200.0);
        policemenSalaryNeed = (float) (economy.getGdpPerPerson() / 1.25);
        departmentsNeed = (int) Math.ceil(policemen / 300.0);

        departmentsLimit = (int) (departmentsNeed * (modifierDepartments + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));
        departments = departmentsLimit;

        crime = modifierCrime + (random.nextInt((500 - (-500)) + 1) + (-500));

        levelOfSecurity = (modifierLevelOfSecurity + (random.nextInt((1 - (-1))) + (-1)));
    }

    public int getCrime() {
        return crime;
    }
}
