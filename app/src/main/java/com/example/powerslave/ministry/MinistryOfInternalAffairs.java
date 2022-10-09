package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.R;
import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

import java.util.Random;

public class MinistryOfInternalAffairs extends Ministry {

    private int policemen;
    private int policemen_limit;

    private int policemen_need;

    private float policemen_salary;
    private float policemen_salary_need;

    private int departments;
    private int departments_limit;

    private int departments_need;

    private int crime;

    private int level_of_security;

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
        string += "Level of security (1 - minimum, 6 - maximum): " + this.level_of_security + "\n";
        string += "Policemen: " + this.policemen + "\n";
        string += "Policemen limit: " + this.policemen_limit + "\n";
        string += "Policemen salary: " + this.policemen_salary + " ƒ" + "\n";
        string += "Policemen salary need: " + this.policemen_salary_need + " ƒ" + "\n";
        string += "Departments: " + this.departments + "\n";
        string += "Policemen need: " + this.policemen_need + "\n";
        string += "Departments need: " + this.departments_need + "\n";
        return super.toString() + "\n" + string;
    }

    @Override
    public void updateMinistry() {
        super.updateMinistry();
        this.efficiency *= ((float) departments / (float) departments_need);
        this.efficiency *= (policemen_salary / policemen_salary_need);
        this.efficiency *= ((float) policemen / (float) policemen_need);
        this.efficiency *= ((float) level_of_security / 4f);
    }

    @Override
    public void statsRandomizer() {
        super.statsRandomizer();

        Random random = new Random();

        float modifier_policemen = 0f;
        float modifier_policemen_salary = 0f;

        float modifier_departments = 0f;
        int modifier_crime = 0;

        int modifier_level_of_security = 0;

        switch (this.country.getContinent()) {
            case EY:
                modifier_policemen = 0.032f;
                modifier_policemen_salary = 0.7f;

                modifier_departments = 0.7f;
                modifier_crime = 3000;
                modifier_level_of_security = 4;
                break;
            case NY:
                modifier_policemen = 0.04f;
                modifier_policemen_salary = 0.8f;

                modifier_departments = 0.8f;
                modifier_crime = 2300;

                modifier_level_of_security = 2;
                break;
            case SY:
                modifier_policemen = 0.03f;
                modifier_policemen_salary = 0.72f;

                modifier_departments = 0.65f;
                modifier_crime = 3150;

                modifier_level_of_security = 3;
                break;
            case WY:
                modifier_policemen = 0.042f;
                modifier_policemen_salary = 0.83f;

                modifier_departments = 0.82f;
                modifier_crime = 2350;

                modifier_level_of_security = 2;
                break;
            case IB:
                modifier_policemen = 0.015f;
                modifier_policemen_salary = 0.6f;

                modifier_departments = 0.55f;
                modifier_crime = 3565;

                modifier_level_of_security = 3;
                break;
            case OB:
                modifier_policemen = 0.013f;
                modifier_policemen_salary = 0.58f;

                modifier_departments = 0.5f;
                modifier_crime = 3690;

                modifier_level_of_security = 3;
                break;
            case CA:
                modifier_policemen = 0.011f;
                modifier_policemen_salary = 0.5f;

                modifier_departments = 0.48f;
                modifier_crime = 4165;

                modifier_level_of_security = 3;
                break;
            case FA:
                modifier_policemen = 0.01f;
                modifier_policemen_salary = 0.45f;

                modifier_departments = 0.45f;
                modifier_crime = 4570;

                modifier_level_of_security = 3;
                break;
            case ME:
                modifier_policemen = 0.007f;
                modifier_policemen_salary = 0.35f;

                modifier_departments = 0.37f;
                modifier_crime = 4965;

                modifier_level_of_security = 3;
                break;
            case GE:
                modifier_policemen = 0.035f;
                modifier_policemen_salary = 0.75f;

                modifier_departments = 0.73f;
                modifier_crime = 2390;

                modifier_level_of_security = 4;
                break;
        }

        this.policemen_limit = (int) (this.economy.getLabor_force() * (modifier_policemen + (random.nextFloat() * (0.002 - (-0.002)) + (-0.02))));
        this.policemen = this.policemen_limit;

        this.policemen_salary = (float) (this.economy.getGdp_per_person() * (modifier_policemen_salary + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));


        this.policemen_need = (int) (this.economy.getPopulation() / 200.0);
        this.policemen_salary_need = (float) (this.economy.getGdp_per_person() / 1.25);
        this.departments_need = (int) Math.ceil(policemen / 300.0);


        this.departments_limit = (int) (this.departments_need * (modifier_departments + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));
        this.departments = this.departments_limit;

        this.crime = (int) (modifier_crime + (random.nextInt((500 - (-500)) + 1) + (-500)));

        this.level_of_security = (modifier_level_of_security + (random.nextInt((1 - (-1))) + (-1)));

    }

    public int getCrime() {
        return crime;
    }
}
