package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

import java.util.Random;

public class MinistryOfHealthcare extends Ministry {

    private float lifeExpectancy;
    private int retirementAge;

    private int doctors;
    private int doctorsLimit;
    private int maximumDoctorsLimit;
    private int doctorsNeed;
    private float doctorsSalary;
    private float doctorsSalaryNeed;

    private int pensioners;
    private float pension;
    private float pensionNeed;

    private int hospitals;
    private int hospitalsNeed;

    private float natality;
    private float mortality;
    private int popGrowth;

    private MinistryOfEconomy economy;

    public MinistryOfHealthcare(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);
        this.name = "Ministry of Healthcare";
        this.economy = country.getMinistryOfEconomy();

        statsRandomizer();
    }

    public float getLifeExpectancy() {
        return lifeExpectancy;
    }

    public int getPensioners() {
        return pensioners;
    }

    public void setLifeExpectancy(float lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }

    public int getDoctors() {
        return doctors;
    }

    public void setDoctors(int doctors) {
        this.doctors = doctors;
    }

    public int getDoctorsLimit() {
        return doctorsLimit;
    }

    public void setDoctorsLimit(int doctorsLimit) {
        this.doctorsLimit = doctorsLimit;
    }

    public int getMaximumDoctorsLimit() {
        return maximumDoctorsLimit;
    }

    public void setMaximumDoctorsLimit(int maximumDoctorsLimit) {
        this.maximumDoctorsLimit = maximumDoctorsLimit;
    }

    public float getDoctorsSalary() {
        return doctorsSalary;
    }

    public void setDoctorsSalary(float doctorsSalary) {
        this.doctorsSalary = doctorsSalary;
    }

    public float getDoctorsSalaryNeed() {
        return doctorsSalaryNeed;
    }

    public void setDoctorsSalaryNeed(float doctorsSalaryNeed) {
        this.doctorsSalaryNeed = doctorsSalaryNeed;
    }

    public float getPension() {
        return pension;
    }

    public void setPension(float pension) {
        this.pension = pension;
    }

    public float getPensionNeed() {
        return pensionNeed;
    }

    public void setPensionNeed(float pensionNeed) {
        this.pensionNeed = pensionNeed;
    }

    public int getRetirementAge() {
        return retirementAge;
    }

    public void setRetirementAge(int retirementAge) {
        this.retirementAge = retirementAge;
    }

    public int getHospitals() {
        return hospitals;
    }

    public void setHospitals(int hospitals) {
        this.hospitals = hospitals;
    }

    @Override
    public String toString() {
        String string;
        string = "Doctors: " + doctors + "\n";
        string += "Doctors limit: " + doctorsLimit + " (Maximum - " + maximumDoctorsLimit + ")" + "\n";
        string += "Doctors salary: " + String.format("%.2f", doctorsSalary) + " ƒ" + "\n";
        string += "Doctors salary need: " + String.format("%.2f", doctorsSalaryNeed) + " ƒ" + "\n";
        string += "Doctors need: " + doctorsNeed + "\n";
        string += "Pensioners: " + pensioners + "\n";
        string += "Pension: " + String.format("%.2f", pension) + " ƒ" + "\n";
        string += "Pension need: " + String.format("%.2f", pensionNeed) + " ƒ" + "\n";
        string += "Retirement age: " + retirementAge + " years" + "\n";
        string += "Life expectancy: " + String.format("%.2f", lifeExpectancy) + " years" + "\n";
        string += "Hospitals: " + hospitals + "\n";
        string += "Hospitals need: " + hospitalsNeed + "\n";
        string += "General budget: " + String.format("%.2f", generalBudget) + " ƒ" + "\n";
        string += "General budget need : " + String.format("%.2f", generalBudgetNeed) + " ƒ" + "\n";
        string += "Natality level per 100 people: " + String.format("%.2f", natality) + "\n";
        string += "Mortality level per 100 people: " + String.format("%.2f", mortality) + "\n";
        string += "Pop growth (total) in month: " + popGrowth + "\n";
        return super.toString() + string;
    }

    @Override
    public void updateMinistry() {
        super.updateMinistry();

        doctorsSalaryNeed = economy.getGdpPerPerson() * 1.15f;
        pensionNeed = economy.getGdpPerPerson() * 0.35f;

        popGrowth = (int) ((economy.getPopulation() / 100) * (natality - mortality));

        generalBudgetNeed = (doctors * 0.65f) + (doctors * doctorsSalary) + (pensioners * pension) + (hospitals * 625f);

        efficiency *= (float) doctors / doctorsNeed;
        efficiency *= (float) hospitals / hospitalsNeed;
        efficiency *= generalBudget / generalBudgetNeed;
    }

    @Override
    public void statsRandomizer() {
        super.statsRandomizer();
        Random random = new Random();

        float modifierLifeExpectancy = 0f;
        float modifierRetirementAge = 0f;

        float modifierDoctors = 0f;
        float modifierDoctorsSalary = 0f;

        float modifierPension = 0f;

        float modifierHospitals = 0f;

        float modifierNatality = 0f;
        float modifierMortality = 0f;

        float modifierGeneralBudget = 0f;

        switch (this.country.getContinent()) {
            case EY:
                modifierLifeExpectancy = 65f;
                modifierRetirementAge = 55f;

                modifierDoctors = 0.003f;
                modifierDoctorsSalary = 1.05f;

                modifierPension = 0.1f;

                modifierHospitals = 0.6f;

                modifierNatality = 2.4f;
                modifierMortality = 1.8f;

                modifierGeneralBudget = 0.7f;
                break;
            case NY:
                modifierLifeExpectancy = 70f;
                modifierRetirementAge = 60f;

                modifierDoctors = 0.004f;
                modifierDoctorsSalary = 1.35f;

                modifierPension = 0.3f;

                modifierHospitals = 0.85f;

                modifierNatality = 2.1f;
                modifierMortality = 1.6f;

                modifierGeneralBudget = 0.8f;
                break;
            case SY:
                modifierLifeExpectancy = 63f;
                modifierRetirementAge = 52f;

                modifierDoctors = 0.003f;
                modifierDoctorsSalary = 1.08f;

                modifierPension = 0.13f;

                modifierHospitals = 0.65f;

                modifierNatality = 2.8f;
                modifierMortality = 2.3f;

                modifierGeneralBudget = 0.65f;
                break;
            case WY:
                modifierLifeExpectancy = 73f;
                modifierRetirementAge = 65f;

                modifierDoctors = 0.0045f;
                modifierDoctorsSalary = 1.4f;

                modifierPension = 0.4f;

                modifierHospitals = 0.9f;

                modifierNatality = 2.2f;
                modifierMortality = 1.65f;

                modifierGeneralBudget = 0.9f;
                break;
            case IB:
                modifierLifeExpectancy = 60f;
                modifierRetirementAge = 50f;

                modifierDoctors = 0.0025f;
                modifierDoctorsSalary = 1f;

                modifierPension = 0.1f;

                modifierHospitals = 0.55f;

                modifierNatality = 3.4f;
                modifierMortality = 2.6f;

                modifierGeneralBudget = 0.6f;
                break;
            case OB:
                modifierLifeExpectancy = 58f;
                modifierRetirementAge = 50f;

                modifierDoctors = 0.0023f;
                modifierDoctorsSalary = 0.95f;

                modifierPension = 0.11f;

                modifierHospitals = 0.52f;

                modifierNatality = 3.5f;
                modifierMortality = 2.75f;

                modifierGeneralBudget = 0.65f;
                break;
            case CA:
                modifierLifeExpectancy = 58f;
                modifierRetirementAge = 45f;

                modifierDoctors = 0.002f;
                modifierDoctorsSalary = 0.85f;

                modifierPension = 0.08f;

                modifierHospitals = 0.5f;

                modifierNatality = 3.7f;
                modifierMortality = 2.9f;

                modifierGeneralBudget = 0.55f;
                break;
            case FA:
                modifierLifeExpectancy = 55f;
                modifierRetirementAge = 45f;

                modifierDoctors = 0.0018f;
                modifierDoctorsSalary = 0.8f;

                modifierPension = 0.06f;

                modifierHospitals = 0.45f;

                modifierNatality = 3.9f;
                modifierMortality = 3.2f;

                modifierGeneralBudget = 0.5f;
                break;
            case ME:
                modifierLifeExpectancy = 51f;
                modifierRetirementAge = 40f;

                modifierDoctors = 0.0014f;
                modifierDoctorsSalary = 0.65f;

                modifierPension = 0.05f;

                modifierHospitals = 0.35f;

                modifierNatality = 4.4f;
                modifierMortality = 3.4f;

                modifierGeneralBudget = 0.35f;
                break;
            case GE:
                modifierLifeExpectancy = 72f;
                modifierRetirementAge = 60f;

                modifierDoctors = 0.0043f;
                modifierDoctorsSalary = 1.35f;

                modifierPension = 0.35f;

                modifierHospitals = 0.88f;

                modifierNatality = 2.4f;
                modifierMortality = 1.85f;

                modifierGeneralBudget = 0.88f;
                break;
        }

        lifeExpectancy = modifierLifeExpectancy + (random.nextFloat() * (3f - (-3f)) + (-3f));
        retirementAge = (int) (modifierRetirementAge + (random.nextInt((2 - (-2))) + (-2)));

        doctorsLimit = (int) (economy.getLabor_force() * (modifierDoctors + (random.nextFloat() * (0.0005f - (-0.0005f)) + (-0.0005f))));
        doctors = doctorsLimit;
        doctorsSalary = economy.getGdpPerPerson() * (modifierDoctorsSalary + (random.nextFloat() * (0.005f - (-0.005f)) + (-0.005f)));

        ////////
        doctorsNeed = (int) economy.getPopulation() / 350;
        hospitalsNeed = (int) (Math.ceil(doctors / 500f));
        ////////

        pensioners = (int) (economy.getPopulation() - ((economy.getLabor_force() * (lifeExpectancy / retirementAge)) * 1.15f));

        pension = economy.getGdpPerPerson() * (modifierPension + (random.nextFloat() * (0.005f - (-0.005f)) + (-0.005f)));

        hospitals = (int) (hospitalsNeed * (modifierHospitals + (random.nextFloat() * (0.005f - (-0.005f)) + (-0.005f))));

        natality = modifierNatality + (random.nextFloat() * (0.15f - (-0.15f)) + (-0.15f));
        mortality = modifierMortality + (random.nextFloat() * (0.15f - (-0.15f)) + (-0.15f));

        generalBudgetNeed = (doctors * 0.65f) + (doctors * doctorsSalary) + (pensioners * pension) + (hospitals * 625f);
        generalBudget = generalBudgetNeed * (modifierGeneralBudget + (random.nextFloat() * (0.05f - (-0.05f)) + (-0.05f)));
        maximumDoctorsLimit = doctorsNeed * 5;
    }

    @Override
    public void workersIncreasing() {
        super.workersIncreasing();
        doctors += doctorsLimit * 0.2 * country.getMinistryOfEducation().efficiency * efficiency;
        if (doctors > doctorsLimit) doctors = doctorsLimit;
    }
}
