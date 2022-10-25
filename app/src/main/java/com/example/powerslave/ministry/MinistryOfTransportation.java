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
    private int airportsNeed;

    private int ports;
    private int portsNeed;

    private int railroadKms;
    private int railroadKmsNeed;

    private int roadKms;
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

        statsRandomizer();
    }

    private enum Building {
        HOSPITAL,
        SCHOOL,
        COLLEGE,
        MONUMENT,
        DEPARTMENT,
        PRISON,
        AIRPORT,
        PORT,
        RAILROAD_KMS,
        ROAD_KMS;

        @Override
        public String toString() {
            switch (this) {
                case HOSPITAL:
                    return "Hospital";
                case SCHOOL:
                    return "School";
                case COLLEGE:
                    return "College";
                case MONUMENT:
                    return "Monument";
                case DEPARTMENT:
                    return "Department";
                case PRISON:
                    return "Prison";
                case AIRPORT:
                    return "Airport";
                case PORT:
                    return "Port";
                case RAILROAD_KMS:
                    return "Kms of railroad";
                case ROAD_KMS:
                    return "Kms of road";
                default:
                    return "Not existing building";
            }
        }
    }

    private class BuildingQueue {
        private Building building;
        private int workers, count, weeks;

        private BuildingQueue(Building building, int workers, int count, int weeks) {
            this.building = building;
            this.workers = workers;
            this.count = count;
            this.weeks = weeks/*(int) (weeks / efficiency)*/;
        }

        @Override
        public String toString() {
            return "Building: " + building + " Workers: " + workers + " Count: " + count + " Time left: " + weeks + "\n";
        }

        public Building getBuilding() {
            return building;
        }

        public int getWorkers() {
            return workers;
        }

        public int getCount() {
            return count;
        }

        public int getWeeks() {
            return weeks;
        }

        public int getWeeksForBuilding() {
            switch (this.building) {
                case HOSPITAL:
                    return 8;
                case SCHOOL:
                    return 9;
                case COLLEGE:
                    return 12;
                case MONUMENT:
                    return 10;
                case DEPARTMENT:
                    return 10;
                case PRISON:
                    return 12;
                case AIRPORT:
                    return 16;
                case PORT:
                    return 18;
                case RAILROAD_KMS:
                    return 1;
                case ROAD_KMS:
                    return 1;
                default:
                    return 9001;
            }
        }
    }

    public void buildHospital(int count) {
        if (freeBuilders >= getHospitalBuildersNeed() * count) {
            int index = 0;
            boolean doesContain = false;

            for (int i = 0; i < queue.size(); i++) {
                if (queue.get(i).getBuilding() == Building.HOSPITAL) {
                    index = i;
                    doesContain = true;
                    break;
                }
            }

            if (doesContain) {
                queue.add(new BuildingQueue(Building.HOSPITAL, queue.get(index).getWorkers() + (getHospitalBuildersNeed() * count), queue.get(index).getCount() + count, queue.get(index).getWeeks() + (8 * count)));
                queue.remove(index);
            } else {
                queue.add(new BuildingQueue(Building.HOSPITAL, getHospitalBuildersNeed() * count, count, 8 * count));
            }
            freeBuilders -= getHospitalBuildersNeed() * count;
        }

    }

    public static int getHospitalBuildersNeed() {
        return 1000;
    }

    public void buildSchool(int count) {
        if (freeBuilders >= getSchoolBuildersNeed() * count) {
            int index = 0;
            boolean doesContain = false;

            for (int i = 0; i < queue.size(); i++) {
                if (queue.get(i).getBuilding() == Building.SCHOOL) {
                    index = i;
                    doesContain = true;
                    break;
                }
            }
            if (doesContain) {
                queue.add(new BuildingQueue(Building.SCHOOL, queue.get(index).getWorkers() + (getSchoolBuildersNeed() * count), queue.get(index).getCount() + count, queue.get(index).getWeeks() + (9 * count)));
                queue.remove(index);
            } else {
                queue.add(new BuildingQueue(Building.SCHOOL, getSchoolBuildersNeed() * count, count, 9 * count));
            }
            freeBuilders -= getSchoolBuildersNeed() * count;
        }
    }

    public static int getSchoolBuildersNeed() {
        return 900;
    }

    public void buildCollege(int count) {
        if (freeBuilders >= getCollegeBuildersNeed() * count) {
            int index = 0;
            boolean doesContain = false;

            for (int i = 0; i < queue.size(); i++) {
                if (queue.get(i).getBuilding() == Building.COLLEGE) {
                    index = i;
                    doesContain = true;
                    break;
                }
            }
            if (doesContain) {
                queue.add(new BuildingQueue(Building.COLLEGE, queue.get(index).getWorkers() + (getCollegeBuildersNeed() * count), queue.get(index).getCount() + count, queue.get(index).getWeeks() + (12 * count)));
                queue.remove(index);
            } else {
                queue.add(new BuildingQueue(Building.COLLEGE, getCollegeBuildersNeed() * count, count, 12 * count));
            }
            freeBuilders -= getCollegeBuildersNeed() * count;
        }
    }

    public static int getCollegeBuildersNeed() {
        return 2000;
    }

    public void buildMonument(int count) {
        if (freeBuilders >= getMonumentBuildersNeed() * count) {
            int index = 0;
            boolean doesContain = false;

            for (int i = 0; i < queue.size(); i++) {
                if (queue.get(i).getBuilding() == Building.MONUMENT) {
                    index = i;
                    doesContain = true;
                    break;
                }
            }
            if (doesContain) {
                queue.add(new BuildingQueue(Building.MONUMENT, queue.get(index).getWorkers() + (getMonumentBuildersNeed() * count), queue.get(index).getCount() + count, queue.get(index).getWeeks() + (10 * count)));
                queue.remove(index);
            } else {
                queue.add(new BuildingQueue(Building.MONUMENT, getMonumentBuildersNeed() * count, count, 10 * count));
            }
            freeBuilders -= getMonumentBuildersNeed() * count;
        }
    }

    public static int getMonumentBuildersNeed() {
        return 1500;
    }

    public void buildDepartment(int count) {
        if (freeBuilders >= getDepartmentBuildersNeed() * count) {
            int index = 0;
            boolean doesContain = false;

            for (int i = 0; i < queue.size(); i++) {
                if (queue.get(i).getBuilding() == Building.DEPARTMENT) {
                    index = i;
                    doesContain = true;
                    break;
                }
            }
            if (doesContain) {
                queue.add(new BuildingQueue(Building.DEPARTMENT, queue.get(index).getWorkers() + (getDepartmentBuildersNeed() * count), queue.get(index).getCount() + count, queue.get(index).getWeeks() + (10 * count)));
                queue.remove(index);
            } else {
                queue.add(new BuildingQueue(Building.DEPARTMENT, getDepartmentBuildersNeed() * count, count, 10 * count));
            }
            freeBuilders -= getDepartmentBuildersNeed() * count;
        }
    }

    public static int getDepartmentBuildersNeed() {
        return 1700;
    }

    public void buildPrison(int count) {
        if (freeBuilders >= getPrisonsBuildersNeed() * count) {
            int index = 0;
            boolean doesContain = false;

            for (int i = 0; i < queue.size(); i++) {
                if (queue.get(i).getBuilding() == Building.PRISON) {
                    index = i;
                    doesContain = true;
                    break;
                }
            }
            if (doesContain) {
                queue.add(new BuildingQueue(Building.PRISON, queue.get(index).getWorkers() + (getPrisonsBuildersNeed() * count), queue.get(index).getCount() + count, queue.get(index).getWeeks() + (12 * count)));
                queue.remove(index);
            } else {
                queue.add(new BuildingQueue(Building.PRISON, getPrisonsBuildersNeed() * count, count, 12 * count));
            }
            freeBuilders -= getPrisonsBuildersNeed() * count;
        }
    }

    public static int getPrisonsBuildersNeed() {
        return 2500;
    }

    public void buildAirport(int count) {
        if (freeBuilders >= getAirportsBuildersNeed() * count) {
            int index = 0;
            boolean doesContain = false;

            for (int i = 0; i < queue.size(); i++) {
                if (queue.get(i).getBuilding() == Building.AIRPORT) {
                    index = i;
                    doesContain = true;
                    break;
                }
            }
            if (doesContain) {
                queue.add(new BuildingQueue(Building.AIRPORT, queue.get(index).getWorkers() + (getAirportsBuildersNeed() * count), queue.get(index).getCount() + count, queue.get(index).getWeeks() + (16 * count)));
                queue.remove(index);
            } else {
                queue.add(new BuildingQueue(Building.AIRPORT, getAirportsBuildersNeed() * count, count, 16 * count));
            }
            freeBuilders -= getAirportsBuildersNeed() * count;
        }
    }

    public static int getAirportsBuildersNeed() {
        return 3500;
    }

    public void buildPorts(int count) {
        if (freeBuilders >= getPortsBuildersNeed() * count) {
            int index = 0;
            boolean doesContain = false;

            for (int i = 0; i < queue.size(); i++) {
                if (queue.get(i).getBuilding() == Building.PORT) {
                    index = i;
                    doesContain = true;
                    break;
                }
            }
            if (doesContain) {
                queue.add(new BuildingQueue(Building.PORT, queue.get(index).getWorkers() + (getPortsBuildersNeed() * count), queue.get(index).getCount() + count, queue.get(index).getWeeks() + (18 * count)));
                queue.remove(index);
            } else {
                queue.add(new BuildingQueue(Building.PORT, getPortsBuildersNeed() * count, count, 18 * count));
            }
            freeBuilders -= getPortsBuildersNeed() * count;
        }
    }

    public static int getPortsBuildersNeed() {
        return 4000;
    }

    public void buildRailroad(int count) {
        if (freeBuilders >= getRailroadBuildersNeed() * count) {
            int index = 0;
            boolean doesContain = false;

            for (int i = 0; i < queue.size(); i++) {
                if (queue.get(i).getBuilding() == Building.RAILROAD_KMS) {
                    index = i;
                    doesContain = true;
                    break;
                }
            }
            if (doesContain) {
                queue.add(new BuildingQueue(Building.RAILROAD_KMS, queue.get(index).getWorkers() + (getRailroadBuildersNeed() * count), queue.get(index).getCount() + count, queue.get(index).getWeeks() + (int) Math.ceil(1 * count / 10)));
                queue.remove(index);
            } else {
                queue.add(new BuildingQueue(Building.RAILROAD_KMS, getRailroadBuildersNeed() * count, count, (int) Math.ceil(count / 10.0)));
            }
            freeBuilders -= getRailroadBuildersNeed() * count;
        }
    }

    public static int getRailroadBuildersNeed() {
        return 20;
    }

    public void buildRoad(int count) {
        if (freeBuilders >= getRoadBuildersNeed() * count) {
            int index = 0;
            boolean doesContain = false;

            for (int i = 0; i < queue.size(); i++) {
                if (queue.get(i).getBuilding() == Building.ROAD_KMS) {
                    index = i;
                    doesContain = true;
                    break;
                }
            }
            if (doesContain) {
                queue.add(new BuildingQueue(Building.ROAD_KMS, queue.get(index).getWorkers() + (getRoadBuildersNeed() * count), queue.get(index).getCount() + count, queue.get(index).getWeeks() + (int) Math.ceil(1 * count / 12)));
                queue.remove(index);
            } else {
                queue.add(new BuildingQueue(Building.ROAD_KMS, getRoadBuildersNeed() * count, count, (int) Math.ceil(count / 12.0)));
            }
            freeBuilders -= getRoadBuildersNeed() * count;
        }
    }

    public static int getRoadBuildersNeed() {
        return 15;
    }

    public void checkIfFinished() {
        for (Iterator<BuildingQueue> iterator = queue.iterator(); iterator.hasNext(); ) {
            BuildingQueue b = iterator.next();
            if ((b.count * b.getWeeksForBuilding()) - b.weeks == b.getWeeksForBuilding() || b.building == Building.ROAD_KMS || b.building == Building.RAILROAD_KMS) {
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
                    case MONUMENT:
                        country.getMinistryOfCulture().setMonuments(country.getMinistryOfCulture().getMonuments() + 1);
                        break;
                    case DEPARTMENT:
                        country.getMinistryOfInternalAffairs().setDepartments(country.getMinistryOfInternalAffairs().getDepartments() + 1);
                        break;
                    case PRISON:
                        country.getMinistryOfJustice().setPrisons(country.getMinistryOfJustice().getPrisons() + 1);
                        break;
                    case AIRPORT:
                        country.getMinistryOfTransportation().setAirports(country.getMinistryOfTransportation().getAirports() + 1);
                        break;
                    case PORT:
                        country.getMinistryOfTransportation().setPorts(country.getMinistryOfTransportation().getPorts() + 1);
                        break;
                    case RAILROAD_KMS:
                        country.getMinistryOfTransportation().setRailroadKms(country.getMinistryOfTransportation().getRailroadKms() + (b.count - 10 < 0 ? b.count : 10));
                        break;
                    case ROAD_KMS:
                        country.getMinistryOfTransportation().setRoadKms(country.getMinistryOfTransportation().getRoadKms() + (b.count - 12 < 0 ? b.count : 12));
                        break;
                }
                int workersHere = b.workers / b.count;
                if (b.building == Building.RAILROAD_KMS) {
                    b.count -= (b.count - 10 < 0 ? b.count : 10);
                } else if (b.building == Building.ROAD_KMS) {
                    b.count -= (b.count - 12 < 0 ? b.count : 12);
                } else {
                    b.count--;
                }
                b.workers -= workersHere;
                freeBuilders += workersHere;
                if (b.count == 0) iterator.remove();
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

    public int getMaximumBuildersLimit() {
        return maximumBuildersLimit;
    }

    public float getBuildersSalaryNeed() {
        return buildersSalaryNeed;
    }

    public int getAirports() {
        return airports;
    }

    public int getPorts() {
        return ports;
    }

    public int getRailroadKms() {
        return railroadKms;
    }

    public int getRoadKms() {
        return roadKms;
    }

    public void setBuildersLimit(int buildersLimit) {
        this.buildersLimit = buildersLimit;
    }

    public void setBuildersSalary(float buildersSalary) {
        this.buildersSalary = buildersSalary;
    }

    public void setAirports(int airports) {
        this.airports = airports;
    }

    public void setPorts(int ports) {
        this.ports = ports;
    }

    public void setRailroadKms(int railroadKms) {
        this.railroadKms = railroadKms;
    }

    public void setRoadKms(int roadKms) {
        this.roadKms = roadKms;
    }

    public String queueToString() {
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
        string += "Airports need: " + airportsNeed + "\n";
        string += "Ports: " + ports + "\n";
        string += "Ports need: " + portsNeed + "\n";
        string += "Railroads kms: " + railroadKms + "\n";
        string += "Railroads kms need: " + railroadKmsNeed + "\n";
        string += "Roads kms: " + roadKms + "\n";
        string += "Roads kms need: " + roadKmsNeed + "\n";
        string += "Building materials need: " + buildingMaterialsNeed + "\n";
        string += "General budget: " + String.format("%.2f", generalBudget) + " ƒ" + "\n";
        string += "General need budget: " + String.format("%.2f", generalBudgetNeed) + " ƒ" + "\n";
        return super.toString() + string;
    }


    @Override
    public void updateMinistry() {
        super.updateMinistry();

        airportsNeed = (int) ((economy.getPopulation() / 9500) + (culture.getTouristsCount() / 2000));
        portsNeed = (int) (economy.getPopulation() / 10000 * (economy.isLandlocked() ? 0 : 1));
        railroadKmsNeed = (int) (economy.getPopulation() / 1500 + economy.getArea() / 2500);
        roadKmsNeed = (int) (economy.getPopulation() / 350 + economy.getArea() / 1500);

        buildingMaterialsNeed = (builders - freeBuilders) * 4.5f;

        buildersSalaryNeed = economy.getGdpPerPerson() * 0.4f;
        maximumBuildersLimit = (int) (economy.getLaborForce() * 0.1);

        generalBudgetNeed = (builders * 0.35f) + (builders * buildersSalaryNeed) + (airports * 1800f) + (ports * 1400f) + (railroadKms * 0.09f) + (roadKms * 0.05f);
        generalBudget += (builders * buildersSalaryNeed);

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

        switch (this.country.getContinent()) {
            case EY:
                modifierBuilders = 0.06f;
                modifierBuildersSalary = 0.35f;

                modifierAirports = 0.3f;
                modifierPorts = 0.5f;
                modifierRailroadKms = 0.4f;
                modifierRoadKms = 0.6f;

                break;
            case NY:
                modifierBuilders = 0.08f;
                modifierBuildersSalary = 0.45f;

                modifierAirports = 0.5f;
                modifierPorts = 0.65f;
                modifierRailroadKms = 0.6f;
                modifierRoadKms = 0.8f;

                break;
            case SY:
                modifierBuilders = 0.05f;
                modifierBuildersSalary = 0.3f;

                modifierAirports = 0.25f;
                modifierPorts = 0.45f;
                modifierRailroadKms = 0.35f;
                modifierRoadKms = 0.55f;

                break;
            case WY:
                modifierBuilders = 0.09f;
                modifierBuildersSalary = 0.48f;

                modifierAirports = 0.6f;
                modifierPorts = 0.75f;
                modifierRailroadKms = 0.7f;
                modifierRoadKms = 0.85f;

                break;
            case IB:
                modifierBuilders = 0.045f;
                modifierBuildersSalary = 0.3f;

                modifierAirports = 0.2f;
                modifierPorts = 0.4f;
                modifierRailroadKms = 0.3f;
                modifierRoadKms = 0.5f;

                break;
            case OB:
                modifierBuilders = 0.04f;
                modifierBuildersSalary = 0.25f;

                modifierAirports = 0.25f;
                modifierPorts = 0.4f;
                modifierRailroadKms = 0.3f;
                modifierRoadKms = 0.55f;

                break;
            case CA:
                modifierBuilders = 0.035f;
                modifierBuildersSalary = 0.25f;

                modifierAirports = 0.15f;
                modifierPorts = 0.25f;
                modifierRailroadKms = 0.3f;
                modifierRoadKms = 0.45f;

                break;
            case FA:
                modifierBuilders = 0.03f;
                modifierBuildersSalary = 0.2f;

                modifierAirports = 0.1f;
                modifierPorts = 0.2f;
                modifierRailroadKms = 0.25f;
                modifierRoadKms = 0.3f;

                break;
            case ME:
                modifierBuilders = 0.025f;
                modifierBuildersSalary = 0.15f;

                modifierAirports = 0.1f;
                modifierPorts = 0.2f;
                modifierRailroadKms = 0.15f;
                modifierRoadKms = 0.2f;

                break;
            case GE:
                modifierBuilders = 0.09f;
                modifierBuildersSalary = 0.4f;

                modifierAirports = 0.55f;
                modifierPorts = 0.7f;
                modifierRailroadKms = 0.75f;
                modifierRoadKms = 0.8f;

                break;
        }

        airportsNeed = (int) ((economy.getPopulation() / 9500) + (culture.getTouristsCount() / 2000));
        portsNeed = (int) (economy.getPopulation() / 10000 * (economy.isLandlocked() ? 0 : 1));
        railroadKmsNeed = (int) (economy.getPopulation() / 1500 + economy.getArea() / 2500);
        roadKmsNeed = (int) (economy.getPopulation() / 350 + economy.getArea() / 1500);

        buildersLimit = (int) (economy.getLaborForce() * (modifierBuilders + (random.nextFloat() * (0.005 - (-0.005)) + (-0.005))));
        builders = buildersLimit;
        freeBuilders = builders;
        buildersSalary = economy.getGdpPerPerson() * (modifierBuildersSalary + (random.nextFloat() * (0.01f - (-0.01f)) + (-0.01f)));

        airports = (int) (airportsNeed * (modifierAirports + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));
        ports = (int) (portsNeed * (modifierPorts + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));
        railroadKms = (int) (railroadKmsNeed * (modifierRailroadKms + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));
        roadKms = (int) (roadKmsNeed * (modifierRoadKms + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));


    }

    @Override
    public void workersIncreasing() {
        super.workersIncreasing();
        builders += buildersLimit * 0.2 * country.getMinistryOfEducation().efficiency * efficiency;
        if (builders > buildersLimit) builders = buildersLimit;
    }
}
