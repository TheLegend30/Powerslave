package com.example.powerslave.ministry;

import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.powerslave.MainActivity;
import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

import java.util.Random;

public abstract class Ministry {
    protected int countryKey;
    protected String name;
    protected Minister minister;
    protected Context context;
    protected Country country;

    protected float generalBudget;
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
        return name + "\n" + "Efficiency of the ministry: " + String.format("%.2f", efficiency * 100) + "%\n\n";
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


    public void updateMinistry() {
        this.efficiency = (((minister.getCompetency() / 1.25f) + (minister.getLoyalty() / 4f)) / 100f);
    }

    public void statsRandomizer() {
    }

    public void workersIncreasing(){

    }
}
