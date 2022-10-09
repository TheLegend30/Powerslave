package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

import java.util.Random;

public class MinistryOfDefense extends Ministry {
    private int generals;
    private int generals_limit;
    private int generals_need;

    private float generals_salary;
    private float generals_salary_need;

    private int admirals;
    private int admirals_limit;
    private int admirals_need;

    private float admirals_salary;
    private float admirals_salary_need;

    private int air_force_officers;
    private int air_force_officers_limit;
    private int air_force_officers_need;

    private float air_force_officers_salary;
    private float air_force_officers_salary_need;

    private int army_power;
    private int army_power_limit;

    private int navy_power;
    private int navy_power_limit;

    private int air_power;
    private int air_power_limit;

    private boolean conscription;

    private MinistryOfEconomy economy;

    public MinistryOfDefense(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);

        this.name = "Ministry of Defense";
        this.economy = country.getMinistryOfEconomy();

        statsRandomizer();

        this.general_budget = (army_power * 1000) + (navy_power * 5000) + (air_power * 8000);
    }


    @Override
    public String toString() {
        String string;
        string = "Army Power: " + this.army_power + "\n";
        string += "Navy Power: " + this.navy_power + "\n";
        string += "Air Force Power: " + this.air_power + "\n";
        string += "Generals: " + this.generals + "\n";
        string += "Generals limit: " + this.generals_limit + "\n";
        string += "Generals need: " + this.generals_need + "\n";
        string += "Generals salary: " + this.generals_salary + "\n";
        string += "Generals salary need: " + this.generals_salary_need + "\n";
        string += "Admirals: " + this.admirals + "\n";
        string += "Admirals limit: " + this.admirals_limit + "\n";
        string += "Admirals need: " + this.admirals_need + "\n";
        string += "Admirals salary: " + this.admirals_salary + "\n";
        string += "Admirals salary need: " + this.admirals_salary_need + "\n";
        string += "Air Force officers: " + this.air_force_officers + "\n";
        string += "Air Force officers limit: " + this.air_force_officers_limit + "\n";
        string += "Air Force officers need: " + this.air_force_officers_need + "\n";
        string += "Air Force officers salary: " + this.air_force_officers_salary + "\n";
        string += "Air Force officers salary need: " + this.air_force_officers_salary_need + "\n";
        string += "Conscription: " + (this.conscription ? "Yes" : "No") + "\n";
        string += "Landlocked: " + (this.economy.isLandlocked() ? "Yes" : "No") + "\n";
        return string;
    }

    @Override
    public void updateMinistry() {
        super.updateMinistry();
    }

    @Override
    public void statsRandomizer() {
        super.statsRandomizer();
        Random random = new Random();

        float modifier_generals = 0f;
        float modifier_generals_salary = 0f;

        float modifier_admirals = 0f;
        float modifier_admirals_salary = 0f;

        float modifier_air_force_officers = 0f;
        float modifier_air_force_officers_salary = 0f;

        float modifier_army = 0f;
        float modifier_navy = 0f;
        float modifier_air = 0f;

        switch (this.country.getContinent()) {
            case EY:
                modifier_generals = 0.65f;
                modifier_generals_salary = 3f;

                modifier_admirals = 0.5f;
                modifier_admirals_salary = 3.15f;

                modifier_air_force_officers = 0.35f;
                modifier_air_force_officers_salary = 3.25f;

                modifier_army = 0.7f;
                modifier_navy = 0.5f;
                modifier_air = 0.3f;
                break;
            case NY:
                modifier_generals = 0.75f;
                modifier_generals_salary = 3.25f;

                modifier_admirals = 0.55f;
                modifier_admirals_salary = 3.35f;

                modifier_air_force_officers = 0.45f;
                modifier_air_force_officers_salary = 3.45f;

                modifier_army = 0.75f;
                modifier_navy = 0.6f;
                modifier_air = 0.4f;
                break;
            case SY:
                modifier_generals = 0.55f;
                modifier_generals_salary = 2.85f;

                modifier_admirals = 0.45f;
                modifier_admirals_salary = 3.05f;

                modifier_air_force_officers = 0.3f;
                modifier_air_force_officers_salary = 3.15f;

                modifier_army = 0.5f;
                modifier_navy = 0.4f;
                modifier_air = 0.2f;
                break;
            case WY:
                modifier_generals = 0.8f;
                modifier_generals_salary = 3.45f;

                modifier_admirals = 0.6f;
                modifier_admirals_salary = 3.75f;

                modifier_air_force_officers = 0.55f;
                modifier_air_force_officers_salary = 3.95f;

                modifier_army = 0.8f;
                modifier_navy = 0.6f;
                modifier_air = 0.4f;
                break;
            case IB:
                modifier_generals = 0.6f;
                modifier_generals_salary = 2.5f;

                modifier_admirals = 0.35f;
                modifier_admirals_salary = 2.85f;

                modifier_air_force_officers = 0.15f;
                modifier_air_force_officers_salary = 3f;

                modifier_army = 0.45f;
                modifier_navy = 0.25f;
                modifier_air = 0.15f;
                break;
            case OB:
                modifier_generals = 0.55f;
                modifier_generals_salary = 2.4f;

                modifier_admirals = 0.35f;
                modifier_admirals_salary = 2.85f;

                modifier_air_force_officers = 0.15f;
                modifier_air_force_officers_salary = 2.9f;

                modifier_army = 0.4f;
                modifier_navy = 0.2f;
                modifier_air = 0.1f;
                break;
            case CA:
                modifier_generals = 0.5f;
                modifier_generals_salary = 2.3f;

                modifier_admirals = 0.25f;
                modifier_admirals_salary = 2.55f;

                modifier_air_force_officers = 0.1f;
                modifier_air_force_officers_salary = 2.75f;
                break;
            case FA:
                modifier_generals = 0.45f;
                modifier_generals_salary = 2.25f;

                modifier_admirals = 0.25f;
                modifier_admirals_salary = 2.5f;

                modifier_air_force_officers = 0.1f;
                modifier_air_force_officers_salary = 2.65f;

                modifier_army = 0.35f;
                modifier_navy = 0.2f;
                modifier_air = 0.1f;
                break;
            case ME:
                modifier_generals = 0.35f;
                modifier_generals_salary = 2f;

                modifier_admirals = 0.2f;
                modifier_admirals_salary = 2.15f;

                modifier_air_force_officers = 0.05f;
                modifier_air_force_officers_salary = 2.35f;

                modifier_army = 0.3f;
                modifier_navy = 0.1f;
                modifier_air = 0.05f;
                break;
            case GE:
                modifier_generals = 0.85f;
                modifier_generals_salary = 3.5f;

                modifier_admirals = 0.7f;
                modifier_admirals_salary = 3.85f;

                modifier_air_force_officers = 0.65f;
                modifier_air_force_officers_salary = 4f;

                modifier_army = 0.75f;
                modifier_navy = 0.7f;
                modifier_air = 0.65f;
                break;
        }

        this.conscription = random.nextBoolean();

        this.army_power_limit = (int) ((this.economy.getLabor_force() / (conscription ? 450f : 650f)) * (modifier_army + random.nextFloat() * (0.03 - (-0.03)) + (-0.03)));
        this.army_power = this.army_power_limit;

        this.navy_power_limit = (int) ((this.economy.getLabor_force() / (conscription ? 1050f : 1350f)) * (modifier_navy + random.nextFloat() * (0.03 - (-0.03)) + (-0.03)) * (this.economy.isLandlocked() ? 0 : 1));
        this.navy_power = this.navy_power_limit;

        this.air_power_limit = (int) ((this.economy.getLabor_force() / (conscription ? 1450f : 1850f)) * (modifier_air + random.nextFloat() * (0.03 - (-0.03)) + (-0.03)));
        this.air_power = this.air_power_limit;

        this.generals_need = army_power / 100;
        this.admirals_need = navy_power / 80;
        this.air_force_officers_need = air_power / 50;

        this.generals_salary_need = (int) (economy.getGdp_per_person() * 3.25);
        this.admirals_salary_need = (int) (economy.getGdp_per_person() * 3.55 * (this.economy.isLandlocked() ? 0 : 1));
        this.air_force_officers_salary_need = (int) (economy.getGdp_per_person() * 3.85);

        this.generals_limit = (int) (this.generals_need * (modifier_generals + random.nextFloat() * (0.03 - (-0.03)) + (-0.03)));
        this.generals = this.generals_limit;
        this.generals_salary = (float) (this.economy.getGdp_per_person() * (modifier_generals_salary + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));

        this.admirals_limit = (int) (this.admirals_need * (modifier_admirals + random.nextFloat() * (0.03 - (-0.03)) + (-0.03)) * (this.economy.isLandlocked() ? 0 : 1));
        this.admirals = this.admirals_limit;
        this.admirals_salary = (float) (this.economy.getGdp_per_person() * (modifier_admirals_salary + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))) * (this.economy.isLandlocked() ? 0 : 1));

        this.air_force_officers_limit = (int) (this.air_force_officers_need * (modifier_air_force_officers + random.nextFloat() * (0.03 - (-0.03)) + (-0.03)));
        this.air_force_officers = this.air_force_officers_limit;
        this.air_force_officers_salary = (float) (this.economy.getGdp_per_person() * (modifier_air_force_officers_salary + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));
    }
}
