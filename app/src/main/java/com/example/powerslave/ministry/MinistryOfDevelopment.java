package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

import java.util.Random;

public class MinistryOfDevelopment extends Ministry {
    private int clerks;
    private int clerksLimit;
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

    @Override
    public String toString() {
        String string;
        string = "Clerks: " + clerks + "\n";
        string += "Clerks limit: " + clerksLimit + "\n";
        string += "Clerks salary: " + clerksSalary + "ƒ" + "\n";
        string += "Clerks salary need: " + clerksSalaryNeed + "ƒ" + "\n";
        string += "Entrepreneurs: " + entrepreneurs + "\n";
        string += "General budget: " + generalBudget + "\n";
        string += "General budget need: " + generalBudgetNeed + "\n";
        string += "Services output: " + servicesOutput + "\n";
        string += "Services need: " + servicesNeed + "\n";
        string += "Trade output: " + tradeOutput + "\n";
        string += "Electrics need: " + electricsNeed + "\n";
        string += "Light industry need: " + lightIndustryNeed + "\n";
        string += "Food need: " + foodNeed + "\n";
        return super.toString() + string;
    }

    @Override
    public void updateMinistry() {
        super.updateMinistry();
        this.clerksSalaryNeed = this.economy.getGdpPerPerson() / 1.15f;
        this.generalBudgetNeed = (this.economy.getBudget() * 0.15f) + ((clerks * 0.11f));

        efficiency *= clerksSalary / clerksSalaryNeed;
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

        float modifierGeneralBudget = 0f;

        switch (this.country.getContinent()) {
            case EY:
                modifierClerks = 0.1f;
                modifierClerksSalary = 0.75f;
                modifierEntrepreneurs = 0.02f;

                modifierGeneralBudget = 0.14f;
                break;
            case NY:
                modifierClerks = 0.13f;
                modifierClerksSalary = 0.8f;
                modifierEntrepreneurs = 0.04f;

                modifierGeneralBudget = 0.165f;
                break;
            case SY:
                modifierClerks = 0.1f;
                modifierClerksSalary = 0.72f;
                modifierEntrepreneurs = 0.025f;

                modifierGeneralBudget = 0.135f;
                break;
            case WY:
                modifierClerks = 0.18f;
                modifierClerksSalary = 0.85f;
                modifierEntrepreneurs = 0.08f;

                modifierGeneralBudget = 0.17f;
                break;
            case IB:
                modifierClerks = 0.09f;
                modifierClerksSalary = 0.7f;
                modifierEntrepreneurs = 0.025f;

                modifierGeneralBudget = 0.12f;
                break;
            case OB:
                modifierClerks = 0.085f;
                modifierClerksSalary = 0.68f;
                modifierEntrepreneurs = 0.023f;

                modifierGeneralBudget = 0.11f;
                break;
            case CA:
                modifierClerks = 0.08f;
                modifierClerksSalary = 0.63f;
                modifierEntrepreneurs = 0.02f;

                modifierGeneralBudget = 0.105f;
                break;
            case FA:
                modifierClerks = 0.075f;
                modifierClerksSalary = 0.6f;
                modifierEntrepreneurs = 0.02f;

                modifierGeneralBudget = 0.1f;
                break;
            case ME:
                modifierClerks = 0.065f;
                modifierClerksSalary = 0.55f;
                modifierEntrepreneurs = 0.015f;

                modifierGeneralBudget = 0.085f;
                break;
            case GE:
                modifierClerks = 0.16f;
                modifierClerksSalary = 0.83f;
                modifierEntrepreneurs = 0.045f;

                modifierGeneralBudget = 0.125f;
                break;
        }

        this.clerksLimit = (int) (this.economy.getLabor_force() * (modifierClerks + (random.nextFloat() * (0.01f - (-0.01f)) + (-0.01f))));
        this.clerks = this.clerksLimit;
        this.clerksSalary = this.economy.getGdpPerPerson() * (modifierClerksSalary + (random.nextFloat() * (0.01f - (-0.01f)) + (-0.01f)));

        this.entrepreneurs = (int) (this.economy.getLabor_force() * (modifierEntrepreneurs + (random.nextFloat() * (0.0005f - (-0.0005f)) + (-0.0005f))));

        this.generalBudget = (float) (this.economy.getBudget() * (modifierGeneralBudget + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));
    }
}
