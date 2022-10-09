package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.R;
import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

import java.util.Random;

public class MinistryOfJustice extends Ministry {

    private int judges;
    private int judges_limit;
    private int judges_need;
    private float judges_salary;
    private float judges_salary_need;

    private int prisons;
    private int prisons_limit;
    private int prisons_need;

    private int level_of_judges_liberty;

    private boolean death_penalty;

    private MinistryOfEconomy economy;
    private MinistryOfInternalAffairs internalAffairs;

    public MinistryOfJustice(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);
        this.name = "Ministry of Justice";
        this.economy = country.getMinistryOfEconomy();
        this.internalAffairs = country.getMinistryOfInternalAffairs();

        statsRandomizer();

    }

    @Override
    public String toString() {
        String string;
        string = "Level of judges' decisions liberty (1 - minimum, 6 - maximum): " + this.level_of_judges_liberty + "\n";
        string += "Judges: " + this.judges + "\n";
        string += "Judges limit: " + this.judges + "\n";
        string += "Judges salary: " + this.judges_salary + " ƒ" + "\n";
        string += "Judges salary need: " + this.judges_salary_need + " ƒ" + "\n";
        string += "Prisons: " + this.prisons + "\n";
        string += "Judges need: " + this.judges_need + "\n";
        string += "Prisons need: " + this.prisons_need + "\n";
        string += "Death penalty: " + (this.death_penalty ? "Yes" : "No") + "\n";
        return super.toString()  + string;
    }

    @Override
    public void updateMinistry() {
        super.updateMinistry();
        this.efficiency *= (float) judges / (float) judges_need;
        this.efficiency *= (float) prisons / (float) prisons_need;
        this.efficiency *= judges_salary / judges_salary_need;
        this.efficiency *= level_of_judges_liberty / 4.5;
        this.efficiency *= death_penalty ? 1.05f : 0.95f;
    }

    @Override
    public void statsRandomizer() {
        super.statsRandomizer();
        Random random = new Random();

        float modifier_judges = 0f;
        float modifier_judges_salary = 0f;

        float modifier_prisons = 0f;

        int modifier_level_of_judges_liberty = 0;

        switch (this.country.getContinent()) {
            case EY:
                modifier_judges = 0.0015f;
                modifier_judges_salary = 1.15f;

                modifier_prisons = 0.6f;

                modifier_level_of_judges_liberty = 2;
                break;
            case NY:
                modifier_judges = 0.002f;
                modifier_judges_salary = 1.25f;

                modifier_prisons = 0.7f;

                modifier_level_of_judges_liberty = 4;
                break;
            case SY:
                modifier_judges = 0.0013f;
                modifier_judges_salary = 1.05f;

                modifier_prisons = 0.55f;
                modifier_level_of_judges_liberty = 2;
                break;
            case WY:
                modifier_judges = 0.0021f;
                modifier_judges_salary = 1.35f;

                modifier_prisons = 0.75f;
                modifier_level_of_judges_liberty = 5;
                break;
            case IB:
                modifier_judges = 0.001f;
                modifier_judges_salary = 1f;

                modifier_prisons = 0.5f;
                modifier_level_of_judges_liberty = 2;
                break;
            case OB:
                modifier_judges = 0.0011f;
                modifier_judges_salary = 0.95f;

                modifier_prisons = 0.45f;
                modifier_level_of_judges_liberty = 3;
                break;
            case CA:
                modifier_judges = 0.00105f;
                modifier_judges_salary = 0.9f;

                modifier_prisons = 0.4f;
                modifier_level_of_judges_liberty = 3;
                break;
            case FA:
                modifier_judges = 0.0009f;
                modifier_judges_salary = 0.85f;

                modifier_prisons = 0.35f;
                modifier_level_of_judges_liberty = 2;
                break;
            case ME:
                modifier_judges = 0.0007f;
                modifier_judges_salary = 0.75f;

                modifier_prisons = 0.25f;
                modifier_level_of_judges_liberty = 2;
                break;
            case GE:
                modifier_judges = 0.0018f;
                modifier_judges_salary = 1.18f;

                modifier_prisons = 0.8f;
                modifier_level_of_judges_liberty = 2;
                break;
        }

        this.judges_need = (int) (economy.getPopulation() / 1050);
        this.judges_salary_need = (int) (economy.getGdp_per_person() * 1.25);
        this.prisons_need = (int) ((internalAffairs.getCrime() * (economy.getPopulation() / 100000)) / 2500);

        this.judges_limit = (int) (economy.getLabor_force() * (modifier_judges + (random.nextFloat() * (0.0003 - (-0.0003)) + (-0.0003))));
        this.judges = this.judges_limit;

        this.judges_salary = (float) (this.economy.getGdp_per_person() * (modifier_judges_salary + (random.nextFloat() * (0.05 - (-0.05)) + (-0.05))));

        this.prisons_limit = (int) (prisons_need * (modifier_prisons + (random.nextFloat() * (0.05 - (-0.05)) + (-0.05))));
        this.prisons = this.prisons_limit;

        this.level_of_judges_liberty = (modifier_level_of_judges_liberty + (random.nextInt((1 - (-1))) + (-1)));

        this.death_penalty = random.nextBoolean();
    }
}
