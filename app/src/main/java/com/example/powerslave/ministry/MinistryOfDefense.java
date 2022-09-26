package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.R;
import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

public class MinistryOfDefense extends Ministry {
    private int arm_power;
    private int nav_power;
    private int air_power;
    private boolean conscription;
    private boolean landlocked;

    public MinistryOfDefense(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);
        this.country = country;
        this.arm_power = context.getResources().getIntArray(R.array.army)[countryKey];
        this.nav_power = context.getResources().getIntArray(R.array.navy)[countryKey];
        this.air_power = context.getResources().getIntArray(R.array.air_force)[countryKey];
        this.conscription = Boolean.parseBoolean(context.getResources().getStringArray(R.array.conscription)[countryKey]);
        this.landlocked = Boolean.parseBoolean(context.getResources().getStringArray(R.array.landlocked)[countryKey]);
        this.general_budget = (arm_power * 1000) + (nav_power * 5000) + (air_power * 8000);
    }

    public int getArm_power() {
        return arm_power;
    }

    public void setArm_power(int arm_power) {
        this.arm_power = arm_power;
    }

    public int getNav_power() {
        return nav_power;
    }

    public void setNav_power(int nav_power) {
        this.nav_power = nav_power;
    }

    public int getAir_power() {
        return air_power;
    }

    public void setAir_power(int air_power) {
        this.air_power = air_power;
    }

    public boolean isConscription() {
        return conscription;
    }

    public void setConscription(boolean conscription) {
        this.conscription = conscription;
    }

    public boolean isLandlocked() {
        return landlocked;
    }

    public void setLandlocked(boolean landlocked) {
        this.landlocked = landlocked;
    }



    @Override
    public String toString() {
        String string;
        string = "Army Power: " + arm_power + "\n";
        string += "Navy Power: " + nav_power + "\n";
        string += "Air Force Power: " + air_power + "\n";
        string += "Conscription: " + (isConscription() ? "Yes" : "No") + "\n";
        string += "Landlocked: " + (isLandlocked() ? "Yes" : "No") + "\n";
        string += "General Budget: " + getGeneral_budget() + " ƒ ";
        string += "(" + String.format("%.2f", (float) getGeneral_budget() / country.getMinistryOfEconomy().getGdp() * 100) + "%)" + "\n";
        return string;
    }

}
