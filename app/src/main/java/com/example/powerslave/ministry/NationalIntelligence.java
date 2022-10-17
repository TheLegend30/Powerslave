package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

import java.util.ArrayList;
import java.util.Collections;

public class NationalIntelligence extends Ministry {

    private int agents = 0;
    private int agentsFree = 0;
    private int agentsLimit = 0;
    private int maximumAgentsLimit;
    private float agentsSalary = 0f;
    private float agentsSalaryNeed;

    public ArrayList<Mission> countriesMissions;

    private MinistryOfEconomy economy;

    public NationalIntelligence(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);
        this.name = "National Intelligence of " + country.getName();
        this.economy = country.getMinistryOfEconomy();

        this.countriesMissions = new ArrayList<>();
        this.agentsSalaryNeed = this.economy.getGdpPerPerson() * 6.5f;
        this.maximumAgentsLimit = (int) (economy.getLabor_force() * 0.001);
    }

    public class Mission implements Comparable<Mission> {
        private Country country;
        private int agentsThere = 0;
        private int missionType = 0;
        private float progressPoints = 0f;

        Mission(Country country) {
            this.country = country;
        }

        @Override
        public int compareTo(Mission mission) {
            return this.country.getName().compareTo(mission.country.getName());
        }

        @Override
        public String toString() {
            return country.getName();
        }

        public Country getCountry() {
            return country;
        }

        public void setCountry(Country country) {
            this.country = country;
        }

        public int getAgentsThere() {
            return agentsThere;
        }

        public void setAgentsThere(int agentsThere) {
            this.agentsThere = agentsThere;
        }

        public float getProgressPoints() {
            return progressPoints;
        }

        public void setProgressPoints(float progressPoints) {
            this.progressPoints = progressPoints;
        }

        public void setSabotageMission() {
            missionType = 1;
        }

        public void setSpyingMission() {
            missionType = 2;
        }

        public void setNomission() {
            missionType = 0;
        }

        public String getMissionType() {
            switch (missionType) {
                case 1:
                    return "Sabotage";
                case 2:
                    return "Spying";
                default:
                    return "No mission";
            }
        }

        public void progressChange() {
            if(!getMissionType().equals("No mission")) {
                progressPoints += (agentsThere / 80f) * efficiency;
                if(progressPoints > 100) progressPoints = 100f;
            } else {
                progressPoints = 0f;
            }
        }
    }

    public int getMaximumAgentsLimit() {
        return maximumAgentsLimit;
    }

    public void setMaximumAgentsLimit(int maximumAgentsLimit) {
        this.maximumAgentsLimit = maximumAgentsLimit;
    }

    public int getAgentsLimit() {
        return agentsLimit;
    }

    public void setAgentsLimit(int agentsLimit) {
        this.agentsLimit = agentsLimit;
    }

    public float getAgentsSalaryNeed() {
        return agentsSalaryNeed;
    }

    public float getAgentsSalary() {
        return agentsSalary;
    }

    public void setAgentsSalary(float agentsSalary) {
        this.agentsSalary = agentsSalary;
    }

    public int getAgentsFree() {
        return agentsFree;
    }

    public void setAgentsFree(int agentsFree) {
        this.agentsFree = agentsFree;
    }

    public int getAgents() {
        return agents;
    }

    public void setAgents(int agents) {
        this.agents = agents;
    }

    private boolean doesMissionNotExists(Country c) {
        for (NationalIntelligence.Mission m : countriesMissions) {
            if (c.getName().equals(m.getCountry().getName())) return false;
        }
        return true;
    }

    public String getCurrentMissions(Mission mission) {
        String string;
        string = "Mission: " + mission.getMissionType() + "\n";
        string += "Mission progress: " + String.format("%.2f", mission.getProgressPoints()) + "\n";
        string += "Agents there: " + mission.getAgentsThere() + "\n";
        return string;
    }

    @Override
    public String toString() {
        String string;
        string = "Agents: " + agents + "\n";
        string += "Agents free: " + agentsFree + "\n";
        string += "Agents limit: " + agentsLimit + " (Maximum - " + maximumAgentsLimit + ")" + "\n";
        string += "Agents salary: " + String.format("%.2f", agentsSalary) + " ƒ" + "\n";
        string += "Agents salary need: " + String.format("%.2f", agentsSalaryNeed) + " ƒ" + "\n";
        return super.toString() + string;
    }

    @Override
    public void updateMinistry() {
        super.updateMinistry();
        for (Country c : Country.countries) {
            if (c != this.country && doesMissionNotExists(c))
                countriesMissions.add(new NationalIntelligence.Mission(c));
        }

        for (NationalIntelligence.Mission m : countriesMissions) {
            agentsFree -= m.agentsThere;
            m.progressChange();
        }
        Collections.sort(countriesMissions);
        efficiency *= agentsSalary / agentsSalaryNeed;
    }

    @Override
    public void workersIncreasing() {
        super.workersIncreasing();
        agents +=  agentsLimit * 0.05 * country.getMinistryOfEducation().efficiency * efficiency;
        if (agents > agentsLimit) agents = agentsLimit;
        agentsFree = agents;
    }
}
