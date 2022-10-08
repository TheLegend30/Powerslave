package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

import java.util.Random;

public class MinistryOfIndustry extends Ministry {
    private int low_workers;
    private int low_workers_limit;
    private float low_workers_salary;
    private float low_workers_salary_need;

    private int middle_workers;
    private int middle_workers_limit;
    private float middle_workers_salary;
    private float middle_workers_salary_need;

    private int high_workers;
    private int high_workers_limit;
    private float high_workers_salary;
    private float high_workers_salary_need;

    // first output
    private float alloys_output;
    private float chemicals_output;
    private float building_materials_output;
    private float food_output;

    // second output
    private float mechanics_output;
    private float fuel_output;
    private float light_industry_output;

    // third output
    private float military_industry_output;
    private float electrics_output;


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

        this.low_workers_salary_need = this.economy.getGdp_per_person() / 2.05f;
        this.middle_workers_salary_need = this.economy.getGdp_per_person() / 1.25f;
        this.high_workers_salary_need = this.economy.getGdp_per_person() / 1.05f;
        this.general_budget_need = (float) ((this.economy.getBudget() * 0.15) + ((low_workers * 0.08) + (middle_workers * 0.1)) + (high_workers * 0.15));

        statsRandomizer();
    }

    @Override
    public String toString() {
        String string;
        string = "Low-level workers: " + low_workers + "\n";
        string += "Low-level workers salary: " + low_workers_salary + "ƒ" + "\n";
        string += "Low-level workers salary need: " + low_workers_salary_need + "ƒ" + "\n";
        string += "Middle-level workers: " + middle_workers + "\n";
        string += "Middle-level workers salary: " + middle_workers_salary + "\n";
        string += "Middle-level workers salary need: " + middle_workers_salary_need + "\n";
        string += "High-level workers: " + high_workers + "\n";
        string += "High-level workers salary: " + high_workers_salary + "\n";
        string += "High-level workers salary need: " + high_workers_salary_need + "\n";
        string += "General budget: " + general_budget + " ƒ" + "\n";
        string += "General need budget: " + general_budget_need + " ƒ" + "\n";
        string += "Alloys output: " + alloys_output + " units" + "\n";
        string += "Alloys reserve: " + alloys_reserve + " units" + "\n";
        string += "Chemicals output: " + chemicals_output + " units" + "\n";
        string += "Chemicals reserve: " + chemicals_reserve + " units" + "\n";
        string += "Building materials output: " + building_materials_output + " units" + "\n";
        string += "Building materials reserve: " + building_materials_reserve + " units" + "\n";
        string += "Food output: " + food_output + " units" + "\n";
        string += "Food reserve: " + food_reserve + " units" + "\n";
        string += "Mechanics output: " + mechanics_output + " units" + "\n";
        string += "Mechanics reserve: " + mechanics_reserve + " units" + "\n";
        string += "Fuel output: " + fuel_output + " units" + "\n";
        string += "Fuel reserve: " + fuel_reserve + " units" + "\n";
        string += "Light industry output: " + light_industry_output + " units" + "\n";
        string += "Light industry  reserve: " + light_industry_reserve + " units" + "\n";
        string += "Electrics output: " + electrics_output + " units" + "\n";
        string += "Electrics reserve: " + electrics_reserve + " units" + "\n";
        string += "Military industry output: " + military_industry_output + " units" + "\n";
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
        efficiency *= low_workers_salary / low_workers_salary_need;
        efficiency *= middle_workers_salary / middle_workers_salary_need;
        efficiency *= high_workers_salary / high_workers_salary_need;
        efficiency *= general_budget / general_budget_need;

        alloys_output = efficiency * low_workers * 1.85f;
        chemicals_output = efficiency * low_workers * 1.25f;
        building_materials_output = efficiency * low_workers * 1.5f;
        food_output = efficiency * low_workers * 3.75f;

        mechanics_output = efficiency * middle_workers * 1.5f;
        fuel_output = efficiency * middle_workers * 1.85f;
        light_industry_output = efficiency * middle_workers * 1.45f;

        electrics_output = efficiency * high_workers * 1.15f;
        military_industry_output = efficiency * high_workers * 1.15f;

        raw_food_need = (food_output * 0.75f) + (light_industry_output * 0.35f);
        raw_need = (alloys_output * 0.95f) + (building_materials_output * 0.85f) + (light_industry_output * 0.35f) + (chemicals_output * 0.25f);

        alloys_need = (mechanics_output * 0.55f) + (military_industry_output * 1.15f);
        chemicals_need = (fuel_output * 1.5f) + (light_industry_output * 0.15f);
        mechanics_need = (electrics_output * 0.55f) + (military_industry_output * 0.65f);

    }

    @Override
    public void statsRandomizer() {
        super.statsRandomizer();
        Random random = new Random();

        float modifier_low_workers = 0f;
        float modifier_low_workers_salary = 0f;

        float modifier_middle_workers = 0f;
        float modifier_middle_workers_salary = 0f;

        float modifier_high_workers = 0f;
        float modifier_high_workers_salary = 0f;

        float modifier_general_budget = 0f;

        switch (this.country.getContinent()) {
            case EY:
                modifier_low_workers = 0.3f;
                modifier_low_workers_salary = 0.35f;

                modifier_middle_workers = 0.1f;
                modifier_middle_workers_salary = 0.65f;

                modifier_high_workers = 0.05f;
                modifier_high_workers_salary = 0.95f;

                modifier_general_budget = 0.15f;
                break;
            case NY:
                modifier_low_workers = 0.2f;
                modifier_low_workers_salary = 0.55f;

                modifier_middle_workers = 0.2f;
                modifier_middle_workers_salary = 0.85f;

                modifier_high_workers = 0.1f;
                modifier_high_workers_salary = 1.25f;

                modifier_general_budget = 0.2f;
                break;
            case SY:
                modifier_low_workers = 0.35f;
                modifier_low_workers_salary = 0.3f;

                modifier_middle_workers = 0.05f;
                modifier_middle_workers_salary = 0.75f;

                modifier_high_workers = 0.05f;
                modifier_high_workers_salary = 0.85f;

                modifier_general_budget = 0.13f;
                break;
            case WY:
                modifier_low_workers = 0.1f;
                modifier_low_workers_salary = 0.65f;

                modifier_middle_workers = 0.25f;
                modifier_middle_workers_salary = 0.95f;

                modifier_high_workers = 0.15f;
                modifier_high_workers_salary = 1.3f;

                modifier_general_budget = 0.18f;
                break;
            case IB:
                modifier_low_workers = 0.25f;
                modifier_low_workers_salary = 0.25f;

                modifier_middle_workers = 0.05f;
                modifier_middle_workers_salary = 0.5f;

                modifier_high_workers = 0.04f;
                modifier_high_workers_salary = 0.85f;

                modifier_general_budget = 0.1f;
                break;
            case OB:
                modifier_low_workers = 0.2f;
                modifier_low_workers_salary = 0.25f;

                modifier_middle_workers = 0.05f;
                modifier_middle_workers_salary = 0.45f;

                modifier_high_workers = 0.03f;
                modifier_high_workers_salary = 0.8f;

                modifier_general_budget = 0.1f;
                break;
            case CA:
                modifier_low_workers = 0.15f;
                modifier_low_workers_salary = 0.23f;

                modifier_middle_workers = 0.04f;
                modifier_middle_workers_salary = 0.45f;

                modifier_high_workers = 0.02f;
                modifier_high_workers_salary = 0.8f;

                modifier_general_budget = 0.08f;
                break;
            case FA:
                modifier_low_workers = 0.12f;
                modifier_low_workers_salary = 0.23f;

                modifier_middle_workers = 0.03f;
                modifier_middle_workers_salary = 0.45f;

                modifier_high_workers = 0.02f;
                modifier_high_workers_salary = 0.75f;

                modifier_general_budget = 0.08f;
                break;
            case ME:
                modifier_low_workers = 0.1f;
                modifier_low_workers_salary = 0.2f;

                modifier_middle_workers = 0.03f;
                modifier_middle_workers_salary = 0.4f;

                modifier_high_workers = 0.01f;
                modifier_high_workers_salary = 0.7f;

                modifier_general_budget = 0.07f;
                break;
            case GE:
                modifier_low_workers = 0.1f;
                modifier_low_workers_salary = 0.6f;

                modifier_middle_workers = 0.3f;
                modifier_middle_workers_salary = 1f;

                modifier_high_workers = 0.2f;
                modifier_high_workers_salary = 1.2f;

                modifier_general_budget = 0.17f;
                break;
        }
        this.low_workers_limit = (int) (this.economy.getLabor_force() * (modifier_low_workers + ((random.nextFloat() * (0.005 - (-0.005)) + (-0.005)))));
        this.low_workers = this.low_workers_limit;
        this.low_workers_salary = (float) (this.economy.getGdp_per_person() * (modifier_low_workers_salary + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));

        this.middle_workers_limit = (int) (this.economy.getLabor_force() * (modifier_middle_workers + ((random.nextFloat() * (0.005 - (-0.005)) + (-0.005)))));
        this.middle_workers = this.middle_workers_limit;
        this.middle_workers_salary = (float) (this.economy.getGdp_per_person() * (modifier_middle_workers_salary + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));

        this.high_workers_limit = (int) (this.economy.getLabor_force() * (modifier_high_workers + ((random.nextFloat() * (0.005 - (-0.005)) + (-0.005)))));
        this.high_workers = this.high_workers_limit;
        this.high_workers_salary = (float) (this.economy.getGdp_per_person() * (modifier_high_workers_salary + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));

        this.general_budget = (float) (this.economy.getBudget() * (modifier_general_budget + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));
    }

}
