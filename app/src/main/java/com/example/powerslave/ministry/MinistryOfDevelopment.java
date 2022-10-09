package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

import java.util.Random;

public class MinistryOfDevelopment extends Ministry {
    private int clerks;
    private int clerks_limit;
    private float clerks_salary;
    private float clerks_salary_need;

    private int entrepreneurs;

    private float services_output;
    private float trade_output;

    private float electrics_need;
    private float light_industry_need;
    private float food_need;

    private float services_need;

    private MinistryOfEconomy economy;

    public MinistryOfDevelopment(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);
        this.name = "Ministry of Development and Trade";
        this.economy = this.country.getMinistryOfEconomy();

        statsRandomizer();
    }

    @Override
    public String toString() {
        String string;
        string = "Clerks: " + clerks + "\n";
        string += "Clerks limit: " + clerks_limit + "\n";
        string += "Clerks salary: " + clerks_salary + "ƒ" + "\n";
        string += "Clerks salary need: " + clerks_salary_need + "ƒ" + "\n";
        string += "Entrepreneurs: " + entrepreneurs + "\n";
        string += "General budget: " + general_budget + "\n";
        string += "General budget need: " + general_budget_need + "\n";
        string += "Services output: " + services_output + "\n";
        string += "Services need: " + services_need + "\n";
        string += "Trade output: " + trade_output + "\n";
        string += "Electrics need: " + electrics_need + "\n";
        string += "Light industry need: " + light_industry_need + "\n";
        string += "Food need: " + food_need + "\n";
        return super.toString() + string;
    }

    @Override
    public void updateMinistry() {
        super.updateMinistry();
        this.clerks_salary_need = this.economy.getGdp_per_person() / 1.15f;
        this.general_budget_need = (this.economy.getBudget() * 0.15f) + ((clerks * 0.11f));

        efficiency *= clerks_salary / clerks_salary_need;
        efficiency *= general_budget / general_budget_need;

        services_output = clerks * efficiency * 0.9f;
        trade_output = entrepreneurs * efficiency * 3.25f;

        services_need = economy.getPopulation() / 25f;

        electrics_need = (services_output * 0.5f) + (trade_output * 0.75f);
        light_industry_need = (services_output * 0.65f) + (trade_output * 0.95f);
        food_need = (services_output * 0.5f) + (trade_output * 0.95f);
    }

    @Override
    public void statsRandomizer() {
        Random random = new Random();
        float modifier_clerks = 0f;
        float modifier_clerks_salary = 0f;
        float modifier_entrepreneurs = 0f;

        float modifier_general_budget = 0f;

        switch (this.country.getContinent()) {
            case EY:
                modifier_clerks = 0.1f;
                modifier_clerks_salary = 0.75f;
                modifier_entrepreneurs = 0.02f;

                modifier_general_budget = 0.14f;
                break;
            case NY:
                modifier_clerks = 0.13f;
                modifier_clerks_salary = 0.8f;
                modifier_entrepreneurs = 0.04f;

                modifier_general_budget = 0.165f;
                break;
            case SY:
                modifier_clerks = 0.1f;
                modifier_clerks_salary = 0.72f;
                modifier_entrepreneurs = 0.025f;

                modifier_general_budget = 0.135f;
                break;
            case WY:
                modifier_clerks = 0.18f;
                modifier_clerks_salary = 0.85f;
                modifier_entrepreneurs = 0.08f;

                modifier_general_budget = 0.17f;
                break;
            case IB:
                modifier_clerks = 0.09f;
                modifier_clerks_salary = 0.7f;
                modifier_entrepreneurs = 0.025f;

                modifier_general_budget = 0.12f;
                break;
            case OB:
                modifier_clerks = 0.085f;
                modifier_clerks_salary = 0.68f;
                modifier_entrepreneurs = 0.023f;

                modifier_general_budget = 0.11f;
                break;
            case CA:
                modifier_clerks = 0.08f;
                modifier_clerks_salary = 0.63f;
                modifier_entrepreneurs = 0.02f;

                modifier_general_budget = 0.105f;
                break;
            case FA:
                modifier_clerks = 0.075f;
                modifier_clerks_salary = 0.6f;
                modifier_entrepreneurs = 0.02f;

                modifier_general_budget = 0.1f;
                break;
            case ME:
                modifier_clerks = 0.065f;
                modifier_clerks_salary = 0.55f;
                modifier_entrepreneurs = 0.015f;

                modifier_general_budget = 0.085f;
                break;
            case GE:
                modifier_clerks = 0.16f;
                modifier_clerks_salary = 0.83f;
                modifier_entrepreneurs = 0.045f;

                modifier_general_budget = 0.125f;
                break;
        }

        this.clerks_limit = (int) (this.economy.getLabor_force() * (modifier_clerks + (random.nextFloat() * (0.01f - (-0.01f)) + (-0.01f))));
        this.clerks = this.clerks_limit;
        this.clerks_salary = this.economy.getGdp_per_person() * (modifier_clerks_salary + (random.nextFloat() * (0.01f - (-0.01f)) + (-0.01f)));

        this.entrepreneurs = (int) (this.economy.getLabor_force() * (modifier_entrepreneurs + (random.nextFloat() * (0.0005f - (-0.0005f)) + (-0.0005f))));

        this.general_budget = (float) (this.economy.getBudget() * (modifier_general_budget + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));
    }
}
