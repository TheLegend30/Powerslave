package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

import java.util.Random;

// TODO: economic policies
public class MinistryOfDevelopment extends Ministry {
    private int clerks;
    private int clerksLimit;
    private int maximumClerksLimit;
    private float clerksSalary;
    private float clerksSalaryNeed;

    private int entrepreneurs;

    private float servicesOutput;
    private float tradeOutput;

    private float electricsNeed;
    private float lightIndustryNeed;
    private float foodNeed;

    private float servicesNeed;

    private MinistryOfEconomy economy;

    public MinistryOfDevelopment(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);
        this.name = "Ministry of Development and Trade";
        this.economy = this.country.getMinistryOfEconomy();

        statsRandomizer();
    }

    public int getClerks() {
        return clerks;
    }

    public float getClerksSalary() {
        return clerksSalary;
    }

    public int getEntrepreneurs() {
        return entrepreneurs;
    }

    public float getTradeOutput() {
        return tradeOutput;
    }

    public float getServicesOutput() {
        return servicesOutput;
    }

    public float getElectricsNeed() {
        return electricsNeed;
    }

    public float getLightIndustryNeed() {
        return lightIndustryNeed;
    }

    public float getFoodNeed() {
        return foodNeed;
    }

    public float getServicesNeed() {
        return servicesNeed;
    }

    public int getClerksLimit() {
        return clerksLimit;
    }

    public int getMaximumClerksLimit() {
        return maximumClerksLimit;
    }

    public float getClerksSalaryNeed() {
        return clerksSalaryNeed;
    }

    public void setClerks(int clerks) {
        this.clerks = clerks;
    }

    public void setClerksLimit(int clerksLimit) {
        this.clerksLimit = clerksLimit;
    }

    public void setMaximumClerksLimit(int maximumClerksLimit) {
        this.maximumClerksLimit = maximumClerksLimit;
    }

    public void setClerksSalary(float clerksSalary) {
        this.clerksSalary = clerksSalary;
    }

    public void setClerksSalaryNeed(float clerksSalaryNeed) {
        this.clerksSalaryNeed = clerksSalaryNeed;
    }

    public void setEntrepreneurs(int entrepreneurs) {
        this.entrepreneurs = entrepreneurs;
    }

    public void setServicesOutput(float servicesOutput) {
        this.servicesOutput = servicesOutput;
    }

    public void setTradeOutput(float tradeOutput) {
        this.tradeOutput = tradeOutput;
    }

    public void setElectricsNeed(float electricsNeed) {
        this.electricsNeed = electricsNeed;
    }

    public void setLightIndustryNeed(float lightIndustryNeed) {
        this.lightIndustryNeed = lightIndustryNeed;
    }

    public void setFoodNeed(float foodNeed) {
        this.foodNeed = foodNeed;
    }

    public void setServicesNeed(float servicesNeed) {
        this.servicesNeed = servicesNeed;
    }

    @Override
    public String toString() {
        String string;
        string = "Clerks: " + clerks + "\n";
        string += "Clerks limit: " + clerksLimit + " (Maximum - " + maximumClerksLimit + ")" + "\n";
        string += "Clerks salary: " + String.format("%.2f", clerksSalary) + " ƒ" + "\n";
        string += "Clerks salary need: " + String.format("%.2f", clerksSalaryNeed) + " ƒ" +"\n";
        string += "Entrepreneurs: " + entrepreneurs + "\n";
        string += "General budget: " + String.format("%.2f", generalBudget) + " ƒ" + "\n";
        string += "General budget need: " + String.format("%.2f", generalBudgetNeed) + " ƒ" + "\n";
        string += "Services output: " + servicesOutput + "\n";
        string += "Services need: " + servicesNeed + "\n";
        string += "Trade output: " + tradeOutput + "\n";
        string += "Electrics need: " + electricsNeed + " units" + "\n";
        string += "Light industry need: " + lightIndustryNeed + " units" + "\n";
        string += "Food need: " + foodNeed + " units" + "\n";
        return super.toString() + string;
    }



    @Override
    public void updateMinistry() {
        super.updateMinistry();
        clerksSalaryNeed = economy.getGdpPerPerson() * 0.75f;

        generalBudgetNeed = (clerks * 0.2f) + (clerks * clerksSalaryNeed);
        generalBudget += (clerks * clerksSalary);
        maximumClerksLimit = (int) (economy.getLabor_force() * 0.2f);

        efficiency *= generalBudget / generalBudgetNeed;

        servicesOutput = clerks * efficiency * 0.9f;
        tradeOutput = entrepreneurs * efficiency * 3.25f;

        servicesNeed = economy.getPopulation() / 25f;

        electricsNeed = (servicesOutput * 0.5f) + (tradeOutput * 0.75f);
        lightIndustryNeed = (servicesOutput * 0.65f) + (tradeOutput * 0.95f);
        foodNeed = (servicesOutput * 0.5f) + (tradeOutput * 0.95f);
    }

    @Override
    public void statsRandomizer() {
        Random random = new Random();
        float modifierClerks = 0f;
        float modifierClerksSalary = 0f;
        float modifierEntrepreneurs = 0f;

        switch (country.getContinent()) {
            case EY:
                modifierClerks = 0.1f;
                modifierClerksSalary = 0.75f;
                modifierEntrepreneurs = 0.02f;

                break;
            case NY:
                modifierClerks = 0.13f;
                modifierClerksSalary = 0.8f;
                modifierEntrepreneurs = 0.04f;

                break;
            case SY:
                modifierClerks = 0.1f;
                modifierClerksSalary = 0.72f;
                modifierEntrepreneurs = 0.025f;

                break;
            case WY:
                modifierClerks = 0.18f;
                modifierClerksSalary = 0.85f;
                modifierEntrepreneurs = 0.08f;

                break;
            case IB:
                modifierClerks = 0.09f;
                modifierClerksSalary = 0.7f;
                modifierEntrepreneurs = 0.025f;

                break;
            case OB:
                modifierClerks = 0.085f;
                modifierClerksSalary = 0.68f;
                modifierEntrepreneurs = 0.023f;

                break;
            case CA:
                modifierClerks = 0.08f;
                modifierClerksSalary = 0.63f;
                modifierEntrepreneurs = 0.02f;

                break;
            case FA:
                modifierClerks = 0.075f;
                modifierClerksSalary = 0.6f;
                modifierEntrepreneurs = 0.02f;

                break;
            case ME:
                modifierClerks = 0.065f;
                modifierClerksSalary = 0.55f;
                modifierEntrepreneurs = 0.015f;

                break;
            case GE:
                modifierClerks = 0.16f;
                modifierClerksSalary = 0.83f;
                modifierEntrepreneurs = 0.025f;

                break;
        }

        clerksLimit = (int) (economy.getLabor_force() * (modifierClerks + (random.nextFloat() * (0.01f - (-0.01f)) + (-0.01f))));
        clerks = clerksLimit;
        clerksSalary = economy.getGdpPerPerson() * (modifierClerksSalary + (random.nextFloat() * (0.01f - (-0.01f)) + (-0.01f)));

        entrepreneurs = (int) (economy.getLabor_force() * (modifierEntrepreneurs + (random.nextFloat() * (0.0005f - (-0.0005f)) + (-0.0005f))));
    }

    @Override
    public void workersIncreasing() {
        super.workersIncreasing();
        clerks += clerksLimit * 0.3 * country.getMinistryOfEducation().efficiency * efficiency;
        if (clerks > clerksLimit) clerks = clerksLimit;
        entrepreneurs += 0.0001 * economy.getLabor_force() * country.getMinistryOfEducation().getLiberty() * (0.15f / economy.getHigh_taxes_modifier()) * (0.05f / economy.getTariffs());
    }
}
