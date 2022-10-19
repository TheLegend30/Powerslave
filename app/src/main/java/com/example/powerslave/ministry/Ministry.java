package com.example.powerslave.ministry;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

public abstract class Ministry {
    protected int countryKey;
    protected String name;
    protected Minister minister;
    protected Context context;
    protected Country country;

    protected float generalBudget;
    protected float ministryFunding = 0f;
    protected float generalBudgetNeed;

    protected float efficiency;

    protected Ministry(int countryKey, Minister minister, Context context, Country country) {
        this.countryKey = countryKey;
        this.minister = minister;
        this.context = context;
        this.country = country;
    }

    @Override
    public String toString() {
        return name + "\n" + "Efficiency of the ministry: " + String.format("%.2f", efficiency * 100) + "%\n" + "Funding of the ministry: " + String.format("%.2f", ministryFunding) + " ƒ" + "\n\n";
    }

    @NonNull
    public String ministerToStringShort() {
        return "Minister: " + minister.getName() + " " +
                minister.getSurname() + "\n" +
                "(L:" + String.format("%.2f", minister.getLoyalty()) + "%" +
                "  C:" + String.format("%.2f", minister.getCompetency()) + "%" +
                "  I:" + minister.getIdeology() +
                ")";
    }

    public Minister getMinister() {
        return minister;
    }

    public void setMinister(Minister minister) {
        this.minister = minister;
    }

    public float getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(float efficiency) {
        this.efficiency = efficiency;
    }

    public float getMinistryFunding() {
        return ministryFunding;
    }

    public void setMinistryFunding(float ministryFunding) {
        this.ministryFunding = ministryFunding;
    }

    public float getGeneralBudgetNeed() {
        return generalBudgetNeed;
    }

    public void updateMinistry() {
        this.generalBudget = ministryFunding;
        this.efficiency = (((minister.getCompetency() / 1.25f) + (minister.getLoyalty() / 4f)) / 100f);
    }

    public void statsRandomizer() {
    }

    public void workersIncreasing(){

    }
}
