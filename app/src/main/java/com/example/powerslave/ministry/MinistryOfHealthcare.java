package com.example.powerslave.ministry;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.powerslave.R;
import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

import java.util.Random;

public class MinistryOfHealthcare extends Ministry {

    private float life_expectancy;
    private int retirement_age;

    private int doctors;
    private int doctors_limit;
    private int doctors_need;
    private float doctors_salary;
    private float doctors_salary_need;

    private int pensioners;
    private float pension;
    private float pension_need;

    private int hospitals;
    private int hospitals_need;

    private float natality;
    private float mortality;
    private int pop_growth;

    private MinistryOfEconomy economy;

    public MinistryOfHealthcare(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);
        this.name = "Ministry of Healthcare";
        this.economy = country.getMinistryOfEconomy();

        statsRandomizer();
    }

    public float getLife_expectancy() {
        return life_expectancy;
    }

    public int getPensioners() {
        return pensioners;
    }

    @Override
    public String toString() {
        String string;
        string = "Doctors: " + doctors + "\n";
        string += "Doctors limit: " + doctors_limit + "\n";
        string += "Doctors salary: " + doctors_salary + " ƒ" + "\n";
        string += "Doctors salary need: " + doctors_salary_need + " ƒ" + "\n";
        string += "Doctors need: " + doctors_need + "\n";
        string += "Pensioners: " + pensioners + "\n";
        string += "Pension: " + pension + " ƒ" + "\n";
        string += "Pension need: " + pension_need + " ƒ" + "\n";
        string += "Hospitals: " + hospitals + "\n";
        string += "Hospitals need: " + hospitals_need + "\n";
        string += "Natality level per 100 people: " + natality + "\n";
        string += "Mortality level per 100 people: " + mortality + "\n";
        string += "Pop growth (total): " + pop_growth + "\n";
        return super.toString() + string;
    }

    @Override
    public void updateMinistry() {
        super.updateMinistry();

        doctors_salary_need = economy.getGdp_per_person() * 1.15f;
        pension_need = economy.getGdp_per_person() * 0.35f;

        pop_growth = (int) ((economy.getPopulation() / 100) * (natality - mortality));

        efficiency *= doctors_salary / doctors_salary_need;
        efficiency *= (float) doctors / doctors_need;
        efficiency *= (float) hospitals / hospitals_need;
    }

    @Override
    public void statsRandomizer() {
        super.statsRandomizer();
        Random random = new Random();

        float modifier_life_expectancy = 0f;
        float modifier_retirement_age = 0f;

        float modifier_doctors = 0f;
        float modifier_doctors_salary = 0f;

        float modifier_pension = 0f;

        float modifier_hospitals = 0f;

        float modifier_natality = 0f;
        float modifier_mortality = 0f;

        switch (this.country.getContinent()) {
            case EY:
                modifier_life_expectancy = 65f;
                modifier_retirement_age = 55f;

                modifier_doctors = 0.003f;
                modifier_doctors_salary = 1.05f;

                modifier_pension = 0.1f;

                modifier_hospitals = 0.6f;

                modifier_natality = 2.4f;
                modifier_mortality = 1.8f;
                break;
            case NY:
                modifier_life_expectancy = 70f;
                modifier_retirement_age = 60f;

                modifier_doctors = 0.004f;
                modifier_doctors_salary = 1.35f;

                modifier_pension = 0.3f;

                modifier_hospitals = 0.85f;

                modifier_natality = 2.1f;
                modifier_mortality = 1.6f;
                break;
            case SY:
                modifier_life_expectancy = 63f;
                modifier_retirement_age = 52f;

                modifier_doctors = 0.003f;
                modifier_doctors_salary = 1.08f;

                modifier_pension = 0.13f;

                modifier_hospitals = 0.65f;

                modifier_natality = 2.8f;
                modifier_mortality = 2.3f;
                break;
            case WY:
                modifier_life_expectancy = 73f;
                modifier_retirement_age = 65f;

                modifier_doctors = 0.0045f;
                modifier_doctors_salary = 1.4f;

                modifier_pension = 0.4f;

                modifier_hospitals = 0.9f;

                modifier_natality = 2.2f;
                modifier_mortality = 1.65f;
                break;
            case IB:
                modifier_life_expectancy = 60f;
                modifier_retirement_age = 50f;

                modifier_doctors = 0.0025f;
                modifier_doctors_salary = 1f;

                modifier_pension = 0.1f;

                modifier_hospitals = 0.55f;

                modifier_natality = 3.4f;
                modifier_mortality = 2.6f;
                break;
            case OB:
                modifier_life_expectancy = 58f;
                modifier_retirement_age = 50f;

                modifier_doctors = 0.0023f;
                modifier_doctors_salary = 0.95f;

                modifier_pension = 0.11f;

                modifier_hospitals = 0.52f;

                modifier_natality = 3.5f;
                modifier_mortality = 2.75f;
                break;
            case CA:
                modifier_life_expectancy = 58f;
                modifier_retirement_age = 45f;

                modifier_doctors = 0.002f;
                modifier_doctors_salary = 0.85f;

                modifier_pension = 0.08f;

                modifier_hospitals = 0.5f;

                modifier_natality = 3.7f;
                modifier_mortality = 2.9f;
                break;
            case FA:
                modifier_life_expectancy = 55f;
                modifier_retirement_age = 45f;

                modifier_doctors = 0.0018f;
                modifier_doctors_salary = 0.8f;

                modifier_pension = 0.06f;

                modifier_hospitals = 0.45f;

                modifier_natality = 3.9f;
                modifier_mortality = 3.2f;
                break;
            case ME:
                modifier_life_expectancy = 51f;
                modifier_retirement_age = 40f;

                modifier_doctors = 0.0014f;
                modifier_doctors_salary = 0.65f;

                modifier_pension = 0.05f;

                modifier_hospitals = 0.35f;

                modifier_natality = 4.4f;
                modifier_mortality = 3.4f;
                break;
            case GE:
                modifier_life_expectancy = 72f;
                modifier_retirement_age = 60f;

                modifier_doctors = 0.0043f;
                modifier_doctors_salary = 1.35f;

                modifier_pension = 0.35f;

                modifier_hospitals = 0.88f;

                modifier_natality = 2.4f;
                modifier_mortality = 1.85f;
                break;
        }

        life_expectancy = modifier_life_expectancy + (random.nextFloat() * (3f - (-3f)) + (-3f));
        retirement_age = (int) (modifier_retirement_age + (random.nextInt((2 - (-2))) + (-2)));

        doctors_limit = (int) (economy.getLabor_force() * (modifier_doctors + (random.nextFloat() * (0.0005f - (-0.0005f)) + (-0.0005f))));
        doctors = doctors_limit;
        doctors_salary = (float) (economy.getGdp_per_person() * (modifier_doctors_salary + (random.nextFloat() * (0.005f - (-0.005f)) + (-0.005f))));

        ////////
        doctors_need = (int) economy.getPopulation() / 350;
        hospitals_need = (int) (Math.ceil(doctors / 500f));
        ////////

        pensioners = (int) (economy.getPopulation() - ((economy.getLabor_force() * (life_expectancy / retirement_age)) * 1.15f));

        pension = (float) (economy.getGdp_per_person() * (modifier_pension + (random.nextFloat() * (0.005f - (-0.005f)) + (-0.005f))));

        hospitals = (int) (hospitals_need * (modifier_hospitals + (random.nextFloat() * (0.005f - (-0.005f)) + (-0.005f))));

        natality = modifier_natality + (random.nextFloat() * (0.15f - (-0.15f)) + (-0.15f));
        mortality = modifier_mortality + (random.nextFloat() * (0.15f - (-0.15f)) + (-0.15f));

    }
}
