package com.example.powerslave.ministry;

import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.powerslave.MainActivity;
import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

public abstract class Ministry {
    protected int countryKey;
    protected String name;
    protected Minister minister;
    protected Context context;
    protected Country country;

    protected float general_budget;
    protected float general_budget_need;

    protected float outlay;

    protected float efficiency;

    protected Ministry(int countryKey, Minister minister, Context context, Country country) {
        this.countryKey = countryKey;
        this.minister = minister;
        this.context = context;
        this.country = country;
    }

    @Override
    public String toString() {
        return name + "\n" + "Efficiency of the ministry: " + String.format("%.2f", efficiency * 100) + "%\n";
    }

    @NonNull
    public String ministerToStringShort() {
        return "Minister: " + minister.getName() + " " +
                minister.getSurname() + "\n" +
                "(S:" + minister.getSex() +
                "  L:" + minister.getLoyalty() + "%" +
                "  C:" + minister.getCompetency() + "%" +
                "  I:" + minister.getIdeology() +
                ")";
    }

    public Minister getMinister() {
        return minister;
    }

    public void setMinister(Minister minister) {
        this.minister = minister;
        updateMinistry();
    }

    public float getGeneral_budget() {
        return general_budget;
    }

    public void setGeneral_budget(float general_budget) {
        this.general_budget = general_budget;
    }

    public void updateMinistry() {
        this.efficiency = (((minister.getCompetency() / 1.5f) + (minister.getLoyalty() / 3)) / 100);
    }
}
