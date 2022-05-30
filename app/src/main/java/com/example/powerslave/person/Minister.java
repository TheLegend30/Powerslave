package com.example.powerslave.person;

import com.example.powerslave.government.Continent;
import com.example.powerslave.government.Ideology;

public class Minister extends Person{
    private float loyalty;
    private float competency;


    public Minister(String name, String surname, Sex sex, Continent motherland, Ideology ideology, float loyalty, float competency) {
        super(name, surname, sex, motherland, ideology);
        this.loyalty = loyalty;
        this.competency = competency;
    }

    public float getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(float loyalty) {
        this.loyalty = loyalty;
    }

    public float getCompetency() {
        return competency;
    }

    public void setCompetency(float competency) {
        this.competency = competency;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Loyalty: " + loyalty + "\n" +
                "Competency: " + competency;
    }
}
