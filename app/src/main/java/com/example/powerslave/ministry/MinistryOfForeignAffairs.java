package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

import java.util.ArrayList;
import java.util.Collections;

public class MinistryOfForeignAffairs extends Ministry {
    // TODO: Foreign affairs rework
    private int diplomats = 0;
    private int diplomatsFree = 0;
    private int diplomatsLimit = 0;
    private int maximumDiplomatsLimit;
    private float diplomatsSalary = 0f;
    private float diplomatsSalaryNeed;

    private int allies = 0;
    private int tradePartners = 0;

    public ArrayList<Relation> countriesRelations;

    private MinistryOfEconomy economy;

    public MinistryOfForeignAffairs(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);
        this.name = "Ministry of Foreign Affairs";
        this.economy = country.getMinistryOfEconomy();

        this.countriesRelations = new ArrayList<>();
        this.diplomatsSalaryNeed = this.economy.getGdpPerPerson() * 4.5f;
        this.maximumDiplomatsLimit = (int) (economy.getLaborForce() * 0.002);
    }

    public class Relation implements Comparable<Relation> {
        private Country country;
        private float relation = 0;
        private int diplomatsThere = 0;
        private boolean isTradePartner = false, isAlly = false, isInWar = false;

        Relation(Country country) {
            this.country = country;
        }

        public Country getCountry() {
            return country;
        }

        public void setCountry(Country country) {
            this.country = country;
        }

        public float getRelation() {
            return relation;
        }

        public void setRelation(int relation) {
            if (relation >= 100) {
                this.relation = 100;
            } else if (relation <= -100) {
                this.relation = -100;
            }
            this.relation = relation;
        }

        public int getDiplomatsThere() {
            return diplomatsThere;
        }

        public void setDiplomatsThere(int diplomatsThere) {
            this.diplomatsThere = diplomatsThere;
        }

        public boolean isTradePartner() {
            return isTradePartner;
        }

        public void setTradePartner(boolean tradePartner) {
            isTradePartner = tradePartner;
        }

        public boolean isAlly() {
            return isAlly;
        }

        public void setAlly(boolean ally) {
            isAlly = ally;
        }

        public boolean isInWar() {
            return isInWar;
        }

        public void setInWar(boolean inWar) {
            isInWar = inWar;
        }

        @Override
        public String toString() {
            return country.getName();
        }

        @Override
        public int compareTo(Relation relation) {
            return this.country.getName().compareTo(relation.country.getName());
        }

        public void relationChange() {
            relation += (diplomatsThere / 200f) * efficiency;
            if(relation > 100) relation = 100f;
        }
    }

    public void setDiplomatsLimit(int diplomatsLimit) {
        this.diplomatsLimit = diplomatsLimit;
    }

    public int getDiplomatsLimit() {
        return diplomatsLimit;
    }

    public int getDiplomats() {
        return diplomats;
    }

    public void setDiplomats(int diplomats) {
        this.diplomats = diplomats;
    }

    public int getDiplomatsFree() {
        return diplomatsFree;
    }

    public void setDiplomatsFree(int diplomatsFree) {
        this.diplomatsFree = diplomatsFree;
    }

    public float getDiplomatsSalaryNeed() {
        return diplomatsSalaryNeed;
    }

    public void setDiplomatsSalaryNeed(float diplomatsSalaryNeed) {
        this.diplomatsSalaryNeed = diplomatsSalaryNeed;
    }

    public void setDiplomatsSalary(float diplomatsSalary) {
        this.diplomatsSalary = diplomatsSalary;
    }


    public int getMaximumDiplomatsLimit() {
        return maximumDiplomatsLimit;
    }

    private boolean doesRelationNotExists(Country c) {
        for (Relation r : countriesRelations) {
            if (c.getName().equals(r.getCountry().getName())) return false;
        }
        return true;
    }

    public String getCurrentRelations(Relation relation) {
        String string;
        string = "Relations level: " + String.format("%.2f", relation.getRelation()) + "\n";
        string += "Diplomats there: " + relation.getDiplomatsThere() + "\n";
        string += "Trade partner: " + (relation.isTradePartner() ? "Yes" : "No") + "\n";
        string += "Ally: " + (relation.isAlly() ? "Yes" : "No") + "\n";
        string += "In war: " + (relation.isInWar() ? "Yes" : "No") + "\n";
        return string;
    }

    @Override
    public String toString() {
        String string;
        string = "Diplomats: " + diplomats + "\n";
        string += "Free diplomats: " + diplomatsFree + "\n";
        string += "Diplomats limit: " + diplomatsLimit + " (Maximum - " + maximumDiplomatsLimit + ")" + "\n";
        string += "Diplomats salary: " + String.format("%.2f", diplomatsSalary) + " ƒ" + "\n";
        string += "Diplomats salary need: " + String.format("%.2f", diplomatsSalaryNeed) + " ƒ" + "\n";
        string += "Allies: " + allies + "\n";
        string += "Trade partners: " + tradePartners + "\n";
        return super.toString() + string;
    }


    @Override
    public void updateMinistry() {
        super.updateMinistry();
        for (Country c : Country.countries) {
            if (c != this.country && doesRelationNotExists(c))
                countriesRelations.add(new Relation(c));
        }
        tradePartners = 0;
        allies = 0;
        for (Relation r : countriesRelations) {
            diplomatsFree -= r.diplomatsThere;
            if(r.isTradePartner) tradePartners++;
            if(r.isAlly) allies++;
            r.relationChange();
        }
        Collections.sort(countriesRelations);
        efficiency *= diplomatsSalary / diplomatsSalaryNeed;
    }

    @Override
    public void workersIncreasing() {
        super.workersIncreasing();
        diplomats +=  diplomatsLimit * 0.1 * country.getMinistryOfEducation().efficiency * efficiency;
        if (diplomats > diplomatsLimit) diplomats = diplomatsLimit;
        diplomatsFree = diplomats;
    }
}

