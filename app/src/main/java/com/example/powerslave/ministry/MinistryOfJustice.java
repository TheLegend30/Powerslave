package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

import java.util.Random;

public class MinistryOfJustice extends Ministry {

    private int judges;
    private int judgesLimit;
    private int judgesNeed;
    private float judgesSalary;
    private float judgesSalaryNeed;

    private int prisons;
    private int prisonsLimit;
    private int prisonsNeed;

    private int levelOfJudgesLiberty;

    private boolean deathPenalty;

    private MinistryOfEconomy economy;
    private MinistryOfInternalAffairs internalAffairs;

    public MinistryOfJustice(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);
        this.name = "Ministry of Justice";
        this.economy = country.getMinistryOfEconomy();
        this.internalAffairs = country.getMinistryOfInternalAffairs();

        statsRandomizer();

    }

    @Override
    public String toString() {
        String string;
        string = "Level of judges' decisions liberty (1 - minimum, 6 - maximum): " + this.levelOfJudgesLiberty + "\n";
        string += "Judges: " + this.judges + "\n";
        string += "Judges limit: " + this.judges + "\n";
        string += "Judges salary: " + this.judgesSalary + " ƒ" + "\n";
        string += "Judges salary need: " + this.judgesSalaryNeed + " ƒ" + "\n";
        string += "Prisons: " + this.prisons + "\n";
        string += "Judges need: " + this.judgesNeed + "\n";
        string += "Prisons need: " + this.prisonsNeed + "\n";
        string += "Death penalty: " + (this.deathPenalty ? "Yes" : "No") + "\n";
        return super.toString()  + string;
    }

    @Override
    public void updateMinistry() {
        super.updateMinistry();
        this.efficiency *= (float) judges / (float) judgesNeed;
        this.efficiency *= (float) prisons / (float) prisonsNeed;
        this.efficiency *= judgesSalary / judgesSalaryNeed;
        this.efficiency *= levelOfJudgesLiberty / 4.5;
        this.efficiency *= deathPenalty ? 1.05f : 0.95f;
    }

    @Override
    public void statsRandomizer() {
        super.statsRandomizer();
        Random random = new Random();

        float modifierJudges = 0f;
        float modifierJudgesSalary = 0f;

        float modifierPrisons = 0f;

        int modifierLevelOfJudgesLiberty = 0;

        switch (this.country.getContinent()) {
            case EY:
                modifierJudges = 0.0015f;
                modifierJudgesSalary = 1.15f;

                modifierPrisons = 0.6f;

                modifierLevelOfJudgesLiberty = 2;
                break;
            case NY:
                modifierJudges = 0.002f;
                modifierJudgesSalary = 1.25f;

                modifierPrisons = 0.7f;

                modifierLevelOfJudgesLiberty = 4;
                break;
            case SY:
                modifierJudges = 0.0013f;
                modifierJudgesSalary = 1.05f;

                modifierPrisons = 0.55f;
                modifierLevelOfJudgesLiberty = 2;
                break;
            case WY:
                modifierJudges = 0.0021f;
                modifierJudgesSalary = 1.35f;

                modifierPrisons = 0.75f;
                modifierLevelOfJudgesLiberty = 5;
                break;
            case IB:
                modifierJudges = 0.001f;
                modifierJudgesSalary = 1f;

                modifierPrisons = 0.5f;
                modifierLevelOfJudgesLiberty = 2;
                break;
            case OB:
                modifierJudges = 0.0011f;
                modifierJudgesSalary = 0.95f;

                modifierPrisons = 0.45f;
                modifierLevelOfJudgesLiberty = 3;
                break;
            case CA:
                modifierJudges = 0.00105f;
                modifierJudgesSalary = 0.9f;

                modifierPrisons = 0.4f;
                modifierLevelOfJudgesLiberty = 3;
                break;
            case FA:
                modifierJudges = 0.0009f;
                modifierJudgesSalary = 0.85f;

                modifierPrisons = 0.35f;
                modifierLevelOfJudgesLiberty = 2;
                break;
            case ME:
                modifierJudges = 0.0007f;
                modifierJudgesSalary = 0.75f;

                modifierPrisons = 0.25f;
                modifierLevelOfJudgesLiberty = 2;
                break;
            case GE:
                modifierJudges = 0.0018f;
                modifierJudgesSalary = 1.18f;

                modifierPrisons = 0.8f;
                modifierLevelOfJudgesLiberty = 2;
                break;
        }

        this.judgesNeed = (int) (economy.getPopulation() / 1050);
        this.judgesSalaryNeed = (int) (economy.getGdpPerPerson() * 1.25);
        this.prisonsNeed = (int) ((internalAffairs.getCrime() * (economy.getPopulation() / 100000)) / 2500);

        this.judgesLimit = (int) (economy.getLabor_force() * (modifierJudges + (random.nextFloat() * (0.0003 - (-0.0003)) + (-0.0003))));
        this.judges = this.judgesLimit;

        this.judgesSalary = (float) (this.economy.getGdpPerPerson() * (modifierJudgesSalary + (random.nextFloat() * (0.05 - (-0.05)) + (-0.05))));

        this.prisonsLimit = (int) (prisonsNeed * (modifierPrisons + (random.nextFloat() * (0.05 - (-0.05)) + (-0.05))));
        this.prisons = this.prisonsLimit;

        this.levelOfJudgesLiberty = (modifierLevelOfJudgesLiberty + (random.nextInt((1 - (-1))) + (-1)));

        this.deathPenalty = random.nextBoolean();
    }
}
