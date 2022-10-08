package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.R;
import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

import java.util.Random;

public class MinistryOfAgriculture extends Ministry {
    private int farmers;
    private int farmers_limit;
    private float farmers_salary;
    private float farmers_salary_need;

    private int miners;
    private int miners_limit;
    private float miners_salary;
    private float miners_salary_need;

    private float raw_food_output;
    private float raw_output;

    private float raw_food_reserve = 0;
    private float raw_reserve = 0;

    private MinistryOfEconomy economy;


    public MinistryOfAgriculture(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);

        this.name = "Ministry of Agriculture and Mining";
        this.economy = country.getMinistryOfEconomy();

        this.farmers_salary_need = this.economy.getGdp_per_person() / 4;
        this.miners_salary_need = this.economy.getGdp_per_person() / 3.5f;
        this.general_budget_need = (float) ((this.economy.getBudget() * 0.1) + ((miners * 0.05) + (farmers * 0.01)));

        statsRandomizer();
    }

    @Override
    public String toString() {
        String string;
        string = "Farmers: " + this.farmers + "\n";
        string += "Farmers' salary: " + (this.farmers_salary / this.economy.getCurrency_to_gulden()) + " " + this.economy.getCurrency() + "\n";
        string += "Farmers' need salary: " + (this.farmers_salary_need / this.economy.getCurrency_to_gulden()) + " " + this.economy.getCurrency() + "\n";

        string += "Miners: " + this.miners + "\n";
        string += "Miners' salary: " + (this.miners_salary / this.economy.getCurrency_to_gulden()) + " " + this.economy.getCurrency() + "\n";
        string += "Miners' need salary: " + (this.miners_salary_need / this.economy.getCurrency_to_gulden()) + " " + this.economy.getCurrency() + "\n";

        string += "General budget: " + general_budget + " ƒ" + "\n";
        string += "General need budget: " + general_budget_need + " ƒ" + "\n";

        string += "Raw food output: " + this.raw_food_output + " units" + "\n";
        string += "Raw food reserve: " + this.raw_food_reserve + " units" + "\n";
        string += "Raw output: " + this.raw_output + " units" + "\n";
        string += "Raw reserve: " + this.raw_reserve + " units" + "\n";

        return super.toString() + "\n" + string;
    }

    @Override
    public void updateMinistry() {
        super.updateMinistry();
        this.efficiency *= ((general_budget + miners_salary + farmers_salary) / (general_budget_need + miners_salary_need + farmers_salary_need));

        this.raw_food_output = efficiency * farmers * 2.35f;
        this.raw_output = efficiency * miners * 1.95f;
    }

    @Override
    public void statsRandomizer() {
        super.statsRandomizer();
        Random random = new Random();

        float modifier_farmers = 0f;
        float modifier_farmers_salary = 0f;

        float modifier_miners = 0f;
        float modifier_miners_salary = 0f;

        float modifier_general_budget = 0f;

        switch (this.country.getContinent()) {
            case EY:
                modifier_farmers = 0.15f;
                modifier_farmers_salary = 0.10f;

                modifier_miners = 0.15f;
                modifier_miners_salary = 0.12f;

                modifier_general_budget = 0.08f;
                break;
            case NY:
                modifier_farmers = 0.055f;
                modifier_farmers_salary = 0.25f;

                modifier_miners = 0.06f;
                modifier_miners_salary = 0.28f;

                modifier_general_budget = 0.05f;
                break;
            case SY:
                modifier_farmers = 0.13f;
                modifier_farmers_salary = 0.12f;

                modifier_miners = 0.12f;
                modifier_miners_salary = 0.14f;

                modifier_general_budget = 0.10f;
                break;
            case WY:
                modifier_farmers = 0.05f;
                modifier_farmers_salary = 0.28f;

                modifier_miners = 0.045f;
                modifier_miners_salary = 0.30f;

                modifier_general_budget = 0.04f;
                break;
            case IB:
                modifier_farmers = 0.25f;
                modifier_farmers_salary = 0.09f;

                modifier_miners = 0.20f;
                modifier_miners_salary = 0.11f;

                modifier_general_budget = 0.20f;
                break;
            case OB:
                modifier_farmers = 0.26f;
                modifier_farmers_salary = 0.085f;

                modifier_miners = 0.18f;
                modifier_miners_salary = 0.10f;

                modifier_general_budget = 0.22f;
                break;
            case CA:
                modifier_farmers = 0.20f;
                modifier_farmers_salary = 0.07f;

                modifier_miners = 0.18f;
                modifier_miners_salary = 0.09f;

                modifier_general_budget = 0.18f;
                break;
            case FA:
                modifier_farmers = 0.35f;
                modifier_farmers_salary = 0.065f;

                modifier_miners = 0.30f;
                modifier_miners_salary = 0.8f;

                modifier_general_budget = 0.19f;
                break;
            case ME:
                modifier_farmers = 0.45f;
                modifier_farmers_salary = 0.05f;

                modifier_miners = 0.32f;
                modifier_miners_salary = 0.075f;

                modifier_general_budget = 0.45f;
                break;
            case GE:
                modifier_farmers = 0.08f;
                modifier_farmers_salary = 0.20f;

                modifier_miners = 0.15f;
                modifier_miners_salary = 0.23f;

                modifier_general_budget = 0.075f;
                break;
        }


        this.farmers_limit = (int) (this.economy.getLabor_force() * (modifier_farmers + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));
        this.farmers = this.farmers_limit;
        this.farmers_salary = (float) (this.economy.getGdp_per_person() * (modifier_farmers_salary + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));

        this.miners_limit = (int) (this.economy.getLabor_force() * (modifier_miners + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));
        this.miners = this.miners_limit;
        this.miners_salary = (float) (this.economy.getGdp_per_person() * (modifier_miners_salary + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));

        this.general_budget = (float) (this.economy.getBudget() * (modifier_general_budget + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));
    }
}

