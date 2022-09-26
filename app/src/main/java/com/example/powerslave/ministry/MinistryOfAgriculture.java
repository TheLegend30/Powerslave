package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.R;
import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

public class MinistryOfAgriculture extends Ministry {
    private int farmers;
    private int farmers_limit;
    private float farmers_salary;
    private float farmers_need_salary;

    private int miners;
    private int miners_limit;
    private float miners_salary;
    private float miners_need_salary;

    private float raw_food_output;
    private float raw_output;

    private float raw_food_reserve;
    private float raw_reserve;

    private MinistryOfEconomy economy;


    public MinistryOfAgriculture(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);

        this.name = "Ministry of Agriculture and Mining";
        this.economy = country.getMinistryOfEconomy();

        this.farmers_limit = (int) ((Float.parseFloat(context.getResources().getStringArray(R.array.farmers_limit)[countryKey]) / 100.0) * economy.getLabor_force());
        this.farmers_salary = (Float.parseFloat(context.getResources().getStringArray(R.array.farmers_salary)[countryKey]) / 100) * economy.getGdp_per_person();

        this.miners_limit = (int) ((Float.parseFloat(context.getResources().getStringArray(R.array.miners_limit)[countryKey]) / 100.0) * economy.getLabor_force());
        this.miners_salary = (Float.parseFloat(context.getResources().getStringArray(R.array.miners_salary)[countryKey]) / 100) * economy.getGdp_per_person();

        this.general_budget = (Float.parseFloat(context.getResources().getStringArray(R.array.general_budget_agriculture)[countryKey]) / 100) * economy.getBudget();

        this.farmers_need_salary = economy.getGdp_per_person() / 15;
        this.miners_need_salary = economy.getGdp_per_person() / 12;
        this.general_budget_need = (float) ((economy.getGdp() / 15) + (miners * 0.05) + (farmers * 0.03));
        this.farmers = this.farmers_limit;
        this.miners = this.miners_limit;

        this.efficiency *= ((general_budget + miners_salary + farmers_salary) / (general_budget_need + miners_need_salary + farmers_need_salary));

        this.raw_food_output = (float) (efficiency * farmers * 1.15);
        this.raw_output = (float) (efficiency * miners * 1.25);

        this.raw_food_reserve = 0;
        this.raw_reserve = 0;
    }

    @Override
    public String toString() {
        String string;
        string = "Farmers: " + this.farmers + "\n";
        string += "Farmers' salary: " + (this.farmers_salary / economy.getCurrency_to_gulden()) + " " + economy.getCurrency() + "\n";
        string += "Farmers' need salary: " + (this.farmers_need_salary / economy.getCurrency_to_gulden()) + " " + economy.getCurrency() + "\n";

        string += "Miners: " + this.miners + "\n";
        string += "Miners' salary: " + (this.miners_salary / economy.getCurrency_to_gulden()) + " " + economy.getCurrency() + "\n";
        string += "Miners' need salary: " + (this.miners_need_salary / economy.getCurrency_to_gulden()) + " " + economy.getCurrency() + "\n";

        string += "General budget: " + general_budget + " ƒ" + "\n";
        string += "General need budget: " + general_budget_need + " ƒ" + "\n";

        string += "Raw food output: " + this.raw_food_output + " units" + "\n";
        string += "Raw food reserve: " + this.raw_food_reserve + " units" + "\n";
        string += "Raw output: " + this.raw_output + " units" + "\n";
        string += "Raw reserve: " + this.raw_reserve + " units" + "\n";

        return super.toString() + "\n" + string;
    }

    @Override
    public void updateMinistry(){
        super.updateMinistry();
        this.farmers_need_salary = economy.getGdp_per_person() / 15;
        this.miners_need_salary = economy.getGdp_per_person() / 12;
        this.general_budget_need = (float) ((economy.getGdp() / 15) + (miners * 0.05) + (farmers * 0.03));
        this.farmers = this.farmers_limit;
        this.miners = this.miners_limit;

        this.efficiency *= ((general_budget + miners_salary + farmers_salary) / (general_budget_need + miners_need_salary + farmers_need_salary));

        this.raw_food_output = (float) (efficiency * farmers * 1.15);
        this.raw_output = (float) (efficiency * miners * 1.25);
    }
}

