package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

import java.util.Random;

public class MinistryOfJustice extends Ministry {

    private int judges;
    private int judgesLimit;
    private int maximumJudgesLimit;
    private int judgesNeed;
    private float judgesSalary;
    private float judgesSalaryNeed;

    private int prisons;
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

    public int getLevelOfJudgesLiberty() {
        return levelOfJudgesLiberty;
    }

    public boolean isDeathPenalty() {
        return deathPenalty;
    }

    public void setDeathPenalty(boolean deathPenalty) {
        this.deathPenalty = deathPenalty;
    }

    public int getJudgesLimit() {
        return judgesLimit;
    }

    public void setJudgesLimit(int judgesLimit) {
        this.judgesLimit = judgesLimit;
    }

    public int getMaximumJudgesLimit() {
        return maximumJudgesLimit;
    }

    public void setMaximumJudgesLimit(int maximumJudgesLimit) {
        this.maximumJudgesLimit = maximumJudgesLimit;
    }

    public float getJudgesSalary() {
        return judgesSalary;
    }

    public void setJudgesSalary(float judgesSalary) {
        this.judgesSalary = judgesSalary;
    }

    public float getJudgesSalaryNeed() {
        return judgesSalaryNeed;
    }

    public void setJudgesSalaryNeed(float judgesSalaryNeed) {
        this.judgesSalaryNeed = judgesSalaryNeed;
    }

    public int getPrisons() {
        return prisons;
    }

    public void setPrisons(int prisons) {
        this.prisons = prisons;
    }

    public int getPrisonsNeed() {
        return prisonsNeed;
    }

    public void setPrisonsNeed(int prisonsNeed) {
        this.prisonsNeed = prisonsNeed;
    }

    public void setLevelOfJudgesLiberty(int levelOfJudgesLiberty) {
        this.levelOfJudgesLiberty = levelOfJudgesLiberty;
    }

    @Override
    public String toString() {
        String string;
        string = "Level of judges' decisions liberty (1 - minimum, 6 - maximum): " + levelOfJudgesLiberty + "\n";
        string += "Judges: " + judges + "\n";
        string += "Judges limit: " + judges + " (Maximum - " + maximumJudgesLimit + ")" + "\n";
        string += "Judges salary: " + String.format("%.2f", judgesSalary) + " ƒ" + "\n";
        string += "Judges salary need: " + String.format("%.2f", judgesSalaryNeed) + " ƒ" + "\n";
        string += "Judges need: " + judgesNeed + "\n";
        string += "Prisons: " + prisons + "\n";
        string += "Prisons need: " + prisonsNeed + "\n";
        string += "General budget: " + String.format("%.2f", generalBudget) + " ƒ" + "\n";
        string += "General need budget: " + String.format("%.2f", generalBudgetNeed) + " ƒ" + "\n";
        string += "Death penalty: " + (deathPenalty ? "Yes" : "No") + "\n";
        return super.toString() + string;
    }

    @Override
    public void updateMinistry() {
        super.updateMinistry();
        efficiency *= generalBudget / generalBudgetNeed;
        efficiency *= (float) judges / (float) judgesNeed;
        efficiency *= (float) prisons / (float) prisonsNeed;
        efficiency *= levelOfJudgesLiberty / 4.5;
        efficiency *= deathPenalty ? 1.05f : 0.95f;
    }

    @Override
    public void statsRandomizer() {
        super.statsRandomizer();
        Random random = new Random();

        float modifierJudges = 0f;
        float modifierJudgesSalary = 0f;

        float modifierPrisons = 0f;

        int modifierLevelOfJudgesLiberty = 0;

        float modifierGeneralBudget = 0f;

        switch (country.getContinent()) {
            case EY:
                modifierJudges = 0.0015f;
                modifierJudgesSalary = 1.15f;

                modifierPrisons = 0.6f;

                modifierLevelOfJudgesLiberty = 2;

                modifierGeneralBudget = 0.55f;
                break;
            case NY:
                modifierJudges = 0.002f;
                modifierJudgesSalary = 1.25f;

                modifierPrisons = 0.7f;

                modifierLevelOfJudgesLiberty = 4;

                modifierGeneralBudget = 0.75f;
                break;
            case SY:
                modifierJudges = 0.0013f;
                modifierJudgesSalary = 1.05f;

                modifierPrisons = 0.55f;
                modifierLevelOfJudgesLiberty = 2;

                modifierGeneralBudget = 0.5f;
                break;
            case WY:
                modifierJudges = 0.0021f;
                modifierJudgesSalary = 1.35f;

                modifierPrisons = 0.75f;
                modifierLevelOfJudgesLiberty = 5;

                modifierGeneralBudget = 0.8f;
                break;
            case IB:
                modifierJudges = 0.001f;
                modifierJudgesSalary = 1f;

                modifierPrisons = 0.5f;
                modifierLevelOfJudgesLiberty = 2;

                modifierGeneralBudget = 0.45f;
                break;
            case OB:
                modifierJudges = 0.0011f;
                modifierJudgesSalary = 0.95f;

                modifierPrisons = 0.45f;
                modifierLevelOfJudgesLiberty = 3;

                modifierGeneralBudget = 0.4f;
                break;
            case CA:
                modifierJudges = 0.00105f;
                modifierJudgesSalary = 0.9f;

                modifierPrisons = 0.4f;
                modifierLevelOfJudgesLiberty = 3;

                modifierGeneralBudget = 0.4f;
                break;
            case FA:
                modifierJudges = 0.0009f;
                modifierJudgesSalary = 0.85f;

                modifierPrisons = 0.35f;
                modifierLevelOfJudgesLiberty = 2;

                modifierGeneralBudget = 0.35f;
                break;
            case ME:
                modifierJudges = 0.0007f;
                modifierJudgesSalary = 0.75f;

                modifierPrisons = 0.25f;
                modifierLevelOfJudgesLiberty = 2;

                modifierGeneralBudget = 0.25f;
                break;
            case GE:
                modifierJudges = 0.0018f;
                modifierJudgesSalary = 1.18f;

                modifierPrisons = 0.8f;
                modifierLevelOfJudgesLiberty = 2;

                modifierGeneralBudget = 0.7f;
                break;
        }

        judgesNeed = (int) (economy.getPopulation() / 1050);
        judgesSalaryNeed = (int) (economy.getGdpPerPerson() * 1.25);
        prisonsNeed = (int) ((internalAffairs.getCrime() * (economy.getPopulation() / 100000)) / 2500);

        judgesLimit = (int) (economy.getLabor_force() * (modifierJudges + (random.nextFloat() * (0.0003 - (-0.0003)) + (-0.0003))));
        judges = judgesLimit;

        judgesSalary = (float) (economy.getGdpPerPerson() * (modifierJudgesSalary + (random.nextFloat() * (0.05 - (-0.05)) + (-0.05))));

        prisons = (int) (prisonsNeed * (modifierPrisons + (random.nextFloat() * (0.05 - (-0.05)) + (-0.05))));

        levelOfJudgesLiberty = (modifierLevelOfJudgesLiberty + (random.nextInt((1 - (-1))) + (-1)));

        deathPenalty = random.nextBoolean();

        generalBudgetNeed = (judges * 1.85f) + (judges * judgesSalary) + (prisons * 850f);
        generalBudget = generalBudgetNeed * (modifierGeneralBudget + (random.nextFloat() * (0.05f - (-0.05f)) + (-0.05f)));
        maximumJudgesLimit = judgesNeed * 9;
    }
}
