package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class MinistryOfTransportation extends Ministry {

    private int builders;
    private int freeBuilders;
    private int buildersLimit;
    private int maximumBuildersLimit;
    private float buildersSalary;
    private float buildersSalaryNeed;

    private int airports;
    private int airportsLimit;
    private int airportsNeed;

    private int ports;
    private int portsLimit;
    private int portsNeed;

    private int railroadKms;
    private int railroadKmsLimit;
    private int railroadKmsNeed;

    private int roadKms;
    private int roadKmsLimit;
    private int roadKmsNeed;

    private float buildingMaterialsNeed;

    private MinistryOfEconomy economy;
    private MinistryOfCulture culture;

    public ArrayList<BuildingQueue> queue = new ArrayList<>();

    public MinistryOfTransportation(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);
        this.name = "Ministry of Transportation and Building";
        this.economy = country.getMinistryOfEconomy();
        this.culture = country.getMinistryOfCulture();

        this.buildersSalaryNeed = this.economy.getGdpPerPerson() * 0.4f;
        this.maximumBuildersLimit = (int) (economy.getLabor_force() * 0.1);

        this.airportsNeed = (int) ((this.economy.getPopulation() / 9500) + (this.culture.getTouristsCount() / 2000));
        this.portsNeed = (int) (this.economy.getPopulation() / 10000 * (this.economy.isLandlocked() ? 0 : 1));
        this.railroadKmsNeed = (int) (this.economy.getPopulation() / 1500 + this.economy.getArea() / 2500);
        this.roadKmsNeed = (int) (this.economy.getPopulation() / 350 + this.economy.getArea() / 1500);

        statsRandomizer();
    }

    private enum Building {
        HOSPITAL,
        SCHOOL,
        COLLEGE,
        PRISON;

        @Override
        public String toString() {
            switch (this) {
                case HOSPITAL:
                    return "Hospital";
                case SCHOOL:
                    return "School";
                case COLLEGE:
                    return "College";
                case PRISON:
                    return "Prison";
                default:
                    return "Not existing building";
            }
        }
    }

    private class BuildingQueue {
        private Building building;
        private int workers, weeks;

        private BuildingQueue(Building building, int workers, int weeks) {
            this.building = building;
            this.workers = workers;
            this.weeks = weeks;
        }

        @Override
        public String toString() {
            return "Building: " + building + " Workers: " + workers + " Time left: " + weeks + "\n";
        }
    }

    public void buildHospital() {
        if (freeBuilders >= 1000) {
            queue.add(new BuildingQueue(Building.HOSPITAL, 1000, 8));
            freeBuilders -= 1000;
        }

    }

    public void buildSchool() {
        if (freeBuilders >= 900) {
            queue.add(new BuildingQueue(Building.SCHOOL, 900, 8));
            freeBuilders -= 900;
        }
    }

    public void buildCollege() {
        if (freeBuilders >= 2000) {
            queue.add(new BuildingQueue(Building.COLLEGE, 2000, 8));
            freeBuilders -= 2000;
        }
    }
    public void buildPrison() {
        if (freeBuilders >= 2500) {
            queue.add(new BuildingQueue(Building.PRISON, 2500, 8));
            freeBuilders -= 2500;
        }
    }

    public void checkIfFinished() {
        for (Iterator<BuildingQueue> iterator = queue.iterator(); iterator.hasNext(); ) {
            BuildingQueue b = iterator.next();
            if (b.weeks == 0) {
                switch (b.building) {
                    case HOSPITAL:
                        country.getMinistryOfHealthcare().setHospitals(country.getMinistryOfHealthcare().getHospitals() + 1);
                        break;
                    case SCHOOL:
                        country.getMinistryOfEducation().setSchools(country.getMinistryOfEducation().getSchools() + 1);
                        break;
                    case COLLEGE:
                        country.getMinistryOfEducation().setColleges(country.getMinistryOfEducation().getColleges() + 1);
                        break;
                    case PRISON:
                        country.getMinistryOfJustice().setPrisons(country.getMinistryOfJustice().getPrisons() + 1);
                        break;
                }
                freeBuilders += b.workers;
                iterator.remove();
            }
        }

    }

    // Queue in ministryActivity
    public void weekPassed() {
        for (BuildingQueue b : queue) {
            b.weeks--;
        }
        checkIfFinished();
    }

    public int getFreeBuilders() {
        return freeBuilders;
    }

    private String queueToString() {
        String string = "";
        for (BuildingQueue b : queue) {
            string += b.toString();
        }
        return string;
    }


    @Override
    public String toString() {
        String string;
        string = "Builders: " + builders + "\n";
        string += "Free builders: " + freeBuilders + "\n";
        string += "Builders limit: " + buildersLimit + " (Maximum - " + maximumBuildersLimit + ")" + "\n";
        string += "Builders salary: " + String.format("%.2f", buildersSalary) + " ƒ" + "\n";
        string += "Builders salary need: " + String.format("%.2f", buildersSalaryNeed) + " ƒ" + "\n";
        string += "Airports: " + airports + "\n";
        string += "Airports limit: " + airportsLimit + "\n";
        string += "Airports need: " + airportsNeed + "\n";
        string += "Ports: " + ports + "\n";
        string += "Ports limit: " + portsLimit + "\n";
        string += "Ports need: " + portsNeed + "\n";
        string += "Railroads kms: " + railroadKms + "\n";
        string += "Railroads kms limit: " + railroadKmsLimit + "\n";
        string += "Railroads kms need: " + railroadKmsNeed + "\n";
        string += "Roads kms: " + roadKms + "\n";
        string += "Roads kms limit: " + roadKmsLimit + "\n";
        string += "Roads kms need: " + roadKmsNeed + "\n";
        string += "General budget: " + String.format("%.2f", generalBudget) + " ƒ" + "\n";
        string += "General need budget: " + String.format("%.2f", generalBudgetNeed) + " ƒ" + "\n";
        string += "Building queue\n";
        string += queueToString();
        return super.toString() + string;
    }

    @Override
    public void updateMinistry() {
        super.updateMinistry();
        efficiency *= generalBudget / generalBudgetNeed;
        if (airports > 0) efficiency *= (float) airports / airportsNeed;
        if (!economy.isLandlocked()) efficiency *= (float) ports / portsNeed;
        efficiency *= (float) railroadKms / railroadKmsNeed;
        efficiency *= (float) roadKms / roadKmsNeed;
        weekPassed();
    }

    @Override
    public void statsRandomizer() {
        super.statsRandomizer();

        Random random = new Random();

        float modifierBuilders = 0f;
        float modifierBuildersSalary = 0f;

        float modifierAirports = 0f;
        float modifierPorts = 0f;
        float modifierRailroadKms = 0f;
        float modifierRoadKms = 0f;

        float modifierGeneralBudget = 0f;

        switch (this.country.getContinent()) {
            case EY:
                modifierBuilders = 0.06f;
                modifierBuildersSalary = 0.35f;

                modifierAirports = 0.3f;
                modifierPorts = 0.5f;
                modifierRailroadKms = 0.4f;
                modifierRoadKms = 0.6f;

                modifierGeneralBudget = 0.65f;
                break;
            case NY:
                modifierBuilders = 0.08f;
                modifierBuildersSalary = 0.45f;

                modifierAirports = 0.5f;
                modifierPorts = 0.65f;
                modifierRailroadKms = 0.6f;
                modifierRoadKms = 0.8f;

                modifierGeneralBudget = 0.85f;
                break;
            case SY:
                modifierBuilders = 0.05f;
                modifierBuildersSalary = 0.3f;

                modifierAirports = 0.25f;
                modifierPorts = 0.45f;
                modifierRailroadKms = 0.35f;
                modifierRoadKms = 0.55f;

                modifierGeneralBudget = 0.6f;
                break;
            case WY:
                modifierBuilders = 0.09f;
                modifierBuildersSalary = 0.48f;

                modifierAirports = 0.6f;
                modifierPorts = 0.75f;
                modifierRailroadKms = 0.7f;
                modifierRoadKms = 0.85f;

                modifierGeneralBudget = 0.9f;
                break;
            case IB:
                modifierBuilders = 0.045f;
                modifierBuildersSalary = 0.3f;

                modifierAirports = 0.2f;
                modifierPorts = 0.4f;
                modifierRailroadKms = 0.3f;
                modifierRoadKms = 0.5f;

                modifierGeneralBudget = 0.55f;
                break;
            case OB:
                modifierBuilders = 0.04f;
                modifierBuildersSalary = 0.25f;

                modifierAirports = 0.25f;
                modifierPorts = 0.4f;
                modifierRailroadKms = 0.3f;
                modifierRoadKms = 0.55f;

                modifierGeneralBudget = 0.5f;
                break;
            case CA:
                modifierBuilders = 0.035f;
                modifierBuildersSalary = 0.25f;

                modifierAirports = 0.15f;
                modifierPorts = 0.25f;
                modifierRailroadKms = 0.3f;
                modifierRoadKms = 0.45f;

                modifierGeneralBudget = 0.45f;
                break;
            case FA:
                modifierBuilders = 0.03f;
                modifierBuildersSalary = 0.2f;

                modifierAirports = 0.1f;
                modifierPorts = 0.2f;
                modifierRailroadKms = 0.25f;
                modifierRoadKms = 0.3f;

                modifierGeneralBudget = 0.45f;
                break;
            case ME:
                modifierBuilders = 0.025f;
                modifierBuildersSalary = 0.15f;

                modifierAirports = 0.1f;
                modifierPorts = 0.2f;
                modifierRailroadKms = 0.15f;
                modifierRoadKms = 0.2f;

                modifierGeneralBudget = 0.35f;
                break;
            case GE:
                modifierBuilders = 0.09f;
                modifierBuildersSalary = 0.4f;

                modifierAirports = 0.55f;
                modifierPorts = 0.7f;
                modifierRailroadKms = 0.75f;
                modifierRoadKms = 0.8f;

                modifierGeneralBudget = 0.75f;
                break;
        }

        buildersLimit = (int) (economy.getLabor_force() * (modifierBuilders + (random.nextFloat() * (0.005 - (-0.005)) + (-0.005))));
        builders = buildersLimit;
        freeBuilders = builders;
        buildersSalary = economy.getGdpPerPerson() * (modifierBuildersSalary + (random.nextFloat() * (0.01f - (-0.01f)) + (-0.01f)));

        airportsLimit = (int) (airportsNeed * (modifierAirports + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));
        airports = airportsLimit;
        portsLimit = (int) (portsNeed * (modifierPorts + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));
        ports = portsLimit;
        railroadKmsLimit = (int) (railroadKmsNeed * (modifierRailroadKms + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));
        railroadKms = railroadKmsLimit;
        roadKmsLimit = (int) (roadKmsNeed * (modifierRoadKms + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));
        roadKms = roadKmsLimit;

        generalBudgetNeed = (builders * 0.35f) + (builders * buildersSalary) + (airports * 1800f) + (ports * 1400f) + (railroadKms * 0.09f) + (roadKms * 0.05f);
        generalBudget = (float) (generalBudgetNeed * (modifierGeneralBudget + (random.nextFloat() * (0.05 - (-0.05)) + (-0.05))));
        maximumBuildersLimit = (int) (economy.getLabor_force() * 0.1);
    }
}
