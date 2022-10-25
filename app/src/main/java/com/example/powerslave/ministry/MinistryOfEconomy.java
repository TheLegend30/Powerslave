package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.R;
import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

public class MinistryOfEconomy extends Ministry {
//    TODO: Make taxmans
    private long population;
    private long area;
    private float density;

    private long gdp;
    private float gdpPerPerson;
    private long debt;

    private float inflation;
    private String currency;
    private float currencyToGulden;
    private float interestRate;

    private long laborForce;
    private long unemployment;
    private long poverty;

    private boolean landlocked;

    private float lowTaxes;
    private float lowTaxesModifier = 0.2f;
    private float middleTaxes;
    private float middleTaxesModifier = 0.3f;
    private float highTaxes;
    private float highTaxesModifier = 0.25f;
    private float totalTaxes = 0;

    private long exportsAndImport = 0;

    private float tariffs = 0.03f;
    private float totalTariffs;

    private float budget = 0;

    private float developmentIndex;

    // TODO : Rework this ministry
    // TODO : Budget is parameter which updates every month

    public MinistryOfEconomy(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);
        this.name = "Ministry of Economy and Finances";
        this.country = country;
        this.area = context.getResources().getIntArray(R.array.area)[countryKey];
        this.population = context.getResources().getIntArray(R.array.population)[countryKey];
        this.density = (float) population / area;

        this.gdp = context.getResources().getIntArray(R.array.gdp)[countryKey] * 100L;
        this.gdpPerPerson = ((float) gdp / population);
        this.debt = (long) (gdp * (Float.parseFloat(context.getResources().getStringArray(R.array.debt)[countryKey]) / 100));

        this.inflation = Float.parseFloat(context.getResources().getStringArray(R.array.inflation)[countryKey]);
        this.currency = context.getResources().getStringArray(R.array.currency)[countryKey];
        this.currencyToGulden = Float.parseFloat(context.getResources().getStringArray(R.array.currency_to_gulden)[countryKey]);
        this.interestRate = Float.parseFloat(context.getResources().getStringArray(R.array.interest_rate)[countryKey]);

        this.laborForce = (long) (population * (Float.parseFloat(context.getResources().getStringArray(R.array.labor_force)[countryKey]) / 100));
        this.unemployment = laborForce;
        this.poverty = population;

        this.landlocked = Boolean.parseBoolean(context.getResources().getStringArray(R.array.landlocked)[countryKey]);
    }

    public long getGdp() {
        return gdp;
    }

    public void setGdp(long gdp) {
        this.gdp = gdp;
    }

    public String getCurrency() {
        return country.getAdjective() + " " + currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public long getLaborForce() {
        return laborForce;
    }

    public void setLaborForce(long laborForce) {
        this.laborForce = laborForce;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public boolean isLandlocked() {
        return landlocked;
    }

    public void setLandlocked(boolean landlocked) {
        this.landlocked = landlocked;
    }

    public long getArea() {
        return area;
    }

    public void setArea(long area) {
        this.area = area;
    }

    public float getDevelopmentIndex() {
        return developmentIndex;
    }

    public String getDevelopmentIndexString() {
        float ave_di = 0f;
        String devString = "(Middle)";
        for (Country country : Country.countries) {
            ave_di += country.getMinistryOfEconomy().developmentIndex;
        }
        ave_di /= Country.countries.size();
        if (developmentIndex <= ave_di) {
            if (developmentIndex <= ave_di - (ave_di * 0.75)) {
                devString = "(Very Low)";
            } else if (developmentIndex <= ave_di - (ave_di * 0.5)) {
                devString = "(Low)";
            }
        } else {
            if (developmentIndex >= ave_di + (ave_di * 0.75)) {
                devString = "(Very High)";
            } else if (developmentIndex >= ave_di + (ave_di * 0.5)) {
                devString = "(High)";
            }
        }
        return String.format("%.2f", developmentIndex) + " " + devString;
    }

    public void setDevelopmentIndex(float developmentIndex) {
        this.developmentIndex = developmentIndex;
    }

    public float getGdpPerPerson() {
        return gdpPerPerson;
    }

    public void setGdpPerPerson(float gdpPerPerson) {
        this.gdpPerPerson = gdpPerPerson;
    }

    public float getCurrencyToGulden() {
        return currencyToGulden;
    }

    public void setCurrencyToGulden(float currencyToGulden) {
        this.currencyToGulden = currencyToGulden;
    }

    public float getHighTaxesModifier() {
        return highTaxesModifier;
    }

    public float getTariffs() {
        return tariffs;
    }

    @Override
    public String toString() {
        String string;
        string = "Area: " + area + " km^2" + "\n";
        string += "Population: " + population + "\n";
        string += "Density: " + String.format("%.2f", density) + "/km^2" + "\n";
        string += "GDP: " + gdp + " ƒ" + "\n";
        string += "GDP per Capita: " + String.format("%.2f", gdpPerPerson) + " ƒ" + " (" + (gdpPerPerson / currencyToGulden) + " " + currency + ")" + "\n";
        string += "National Debt: " + debt + " ƒ " + String.format("(%.2f", (float) debt / gdp * 100).concat(" %)") + "\n";
        string += "Budget: " + String.format("%.2f", budget) + " ƒ " + "\n";
        string += "Inflation: " + String.format("%.2f", inflation) + " %" + "\n";
        string += "Currency: " + country.getAdjective() + " " + currency + "\n";
        string += "1 " + currency + " is " + String.format("%.5f", currencyToGulden) + " of Golden Florin (ƒ)" + "\n";
        string += "Interest Rate: " + interestRate + " %" + "\n";
        string += "Labor Force: " + laborForce + " (" + String.format("%.1f", (float) laborForce / population * 100.0) + " %)" + "\n";
        string += "Unemployment Rate: " + unemployment + " (" + String.format("%.1f", (float) unemployment / population * 100.0) + " %)" + "\n";
        string += "Poverty Rate: " + poverty + " (" + String.format("%.1f", (float) poverty / population * 100.0) + " %)" + "\n";
        string += "Landlocked: " + (this.landlocked ? "Yes" : "No") + "\n";
        string += "Development Index: " + getDevelopmentIndexString() + "\n";
        return super.toString() + string;
    }

    @Override
    public void updateMinistry() {
        super.updateMinistry();
        developmentIndex = (country.getMinistryOfHealthcare().getLifeExpectancy() * country.getMinistryOfEducation().getLiteracy() * gdpPerPerson) / 100;
        MinistryOfAgriculture agriculture = country.getMinistryOfAgriculture();
        MinistryOfIndustry industry = country.getMinistryOfIndustry();
        MinistryOfDevelopment development = country.getMinistryOfDevelopment();
        MinistryOfDefense defense = country.getMinistryOfDefense();

        lowTaxes = lowTaxesModifier * ((agriculture.getMiners() * agriculture.getMinersSalary()) + (agriculture.getFarmers() * agriculture.getFarmersSalary()) + (industry.getLowWorkers() * industry.getLowWorkersSalary()));
        middleTaxes = middleTaxesModifier * ((industry.getMiddleWorkers() * industry.getMiddleWorkersSalary()) + (development.getClerks() * development.getClerksSalary()));
        highTaxes = highTaxesModifier * ((industry.getHighWorkers() * industry.getHighWorkersSalary()) + (development.getTradeOutput() * 1.5f));
        totalTaxes += lowTaxes + middleTaxes + highTaxes;

        exportsAndImport += 0.3f * (agriculture.getRawOutput() - industry.getRawNeed());
        exportsAndImport += 0.35f * ( agriculture.getRawFoodOutput() - industry.getRawFoodNeed());

        exportsAndImport += 1.85f * (industry.getAlloysOutput() - industry.getAlloysNeed());
        exportsAndImport += 1.45f * (industry.getChemicalsOutput() - industry.getChemicalsNeed());
        exportsAndImport += 1.5f * industry.getBuildingMaterialsOutput();
        exportsAndImport += 1.7f * (industry.getFoodOutput() - development.getFoodNeed());

        exportsAndImport += 3.35f * (industry.getMechanicsOutput() - industry.getMechanicsNeed());
        exportsAndImport += 2.05f * (industry.getFuelOutput() - defense.getFuelNeed());
        exportsAndImport += 3.15 * (industry.getLightIndustryOutput() - development.getLightIndustryNeed());

        exportsAndImport += 5.3f * (industry.getMilitaryIndustryOutput() - defense.getMilitaryIndustryNeed());
        exportsAndImport += 4.25f * (industry.getElectricsOutput() - development.getElectricsNeed());

        exportsAndImport += 6.55f * (development.getServicesOutput() - development.getServicesNeed());

        exportsAndImport *= country.getMinistryOfTransportation().efficiency * 75f;

        totalTariffs = exportsAndImport * tariffs * country.getMinistryOfTransportation().efficiency;

        budget = exportsAndImport + totalTariffs + totalTaxes;

        budget -= country.getMinistryOfAgriculture().generalBudget;
        budget -= country.getMinistryOfCulture().generalBudget;
        budget -= country.getMinistryOfDefense().generalBudget;
        budget -= country.getMinistryOfDevelopment().generalBudget;
        budget -= country.getMinistryOfEducation().generalBudget;
        budget -= country.getMinistryOfForeignAffairs().generalBudget;
        budget -= country.getMinistryOfHealthcare().generalBudget;
        budget -= country.getMinistryOfIndustry().generalBudget;
        budget -= country.getMinistryOfInternalAffairs().generalBudget;
        budget -= country.getMinistryOfJustice().generalBudget;
        budget -= country.getMinistryOfTransportation().generalBudget;
        budget -= country.getNationalIntelligence().generalBudget;
        budget -= country.getParliament().generalBudget;

    }
}
