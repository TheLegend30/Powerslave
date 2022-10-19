package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.government.Ideology;
import com.example.powerslave.person.Minister;

import java.util.HashMap;
import java.util.Random;

public class Parliament extends Ministry {
    // TODO: Rework Parliament
    private Ideology yourIdeology;
    private int parliamentCount;
    private int parliamentCountMax;
    private HashMap<Ideology, Integer> parties = new HashMap<>();

    public Parliament(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);
        this.name = "Parliament of " + this.country.getName();

        this.yourIdeology = this.country.getRuler().getIdeology();
        statsRandomizer();
    }

    @Override
    public String toString() {
        String string;
        string = "Your ideology: " + this.yourIdeology + "\n";
        string += "Parliament size: " + this.parliamentCountMax + "\n";
        string += "Party for Great Future (Plycism): " + parties.get(Ideology.PLYCISM) + "\n";
        string += "Party for everyone (Vyhucism): " + parties.get(Ideology.VYHUCISM) + "\n";
        string += "Pious party (Arewosm): " + parties.get(Ideology.AREWOSM) + "\n";
        string += "Equal party (Hycrocism): " + parties.get(Ideology.HYCROCISM) + "\n";
        string += "Labour party (Zowotism): " + parties.get(Ideology.ZOWOTISM) + "\n";
        string += "Prosperity party (Beclysm): " + parties.get(Ideology.BECLYSM) + "\n";
        string += "Traditional party (Neo-Leeism): " + parties.get(Ideology.NEOLEEISM) + "\n";
        string += "Progress party (Fokritism): " + parties.get(Ideology.FOKRITISM) + "\n";
        string += "Nonpartisan (No Ideology): " + parties.get(Ideology.NONPARTISAN) + "\n";
        return super.toString() + string;
    }

    @Override
    public void updateMinistry() {
        super.updateMinistry();
        this.yourIdeology = this.country.getRuler().getIdeology();
    }

    @Override
    public void statsRandomizer() {
        super.statsRandomizer();
        Random random = new Random();

        float modifierParliamentCount = 0f;

        float modifierPly = 0f;
        float modifierVyh = 0f;
        float modifierAre = 0f;
        float modifierHyc = 0f;
        float modifierZow = 0f;
        float modifierBec = 0f;
        float modifierNeo = 0f;
        float modifierFok = 0f;

        switch (this.country.getContinent()) {
            case EY:
                modifierParliamentCount = 0.75f;

                modifierPly = 0.1f;
                modifierVyh = 0.05f;
                modifierAre = 0.15f;
                modifierHyc = 0.2f;
                modifierZow = 0.2f;
                modifierBec = 0.05f;
                modifierNeo = 0.05f;
                modifierFok = 0.05f;
                break;
            case NY:
                modifierParliamentCount = 0.85f;

                modifierPly = 0.1f;
                modifierVyh = 0.05f;
                modifierAre = 0.1f;
                modifierHyc = 0.05f;
                modifierZow = 0.25f;
                modifierBec = 0.25f;
                modifierNeo = 0.05f;
                modifierFok = 0.05f;
                break;
            case SY:
                modifierParliamentCount = 0.65f;

                modifierPly = 0.1f;
                modifierVyh = 0.05f;
                modifierAre = 0.05f;
                modifierHyc = 0.05f;
                modifierZow = 0.3f;
                modifierBec = 0.25f;
                modifierNeo = 0.05f;
                modifierFok = 0.05f;
                break;
            case WY:
                modifierParliamentCount = 0.95f;

                modifierPly = 0.1f;
                modifierVyh = 0.05f;
                modifierAre = 0.15f;
                modifierHyc = 0.05f;
                modifierZow = 0.05f;
                modifierBec = 0.3f;
                modifierNeo = 0.15f;
                modifierFok = 0.05f;
                break;
            case IB:
                modifierParliamentCount = 0.55f;

                modifierPly = 0.1f;
                modifierVyh = 0.05f;
                modifierAre = 0.15f;
                modifierHyc = 0.1f;
                modifierZow = 0.15f;
                modifierBec = 0.15f;
                modifierNeo = 0.15f;
                modifierFok = 0.05f;
                break;
            case OB:
                modifierParliamentCount = 0.65f;

                modifierPly = 0.1f;
                modifierVyh = 0.4f;
                modifierAre = 0.05f;
                modifierHyc = 0.15f;
                modifierZow = 0.05f;
                modifierBec = 0.05f;
                modifierNeo = 0.05f;
                modifierFok = 0.05f;
                break;
            case CA:
                modifierParliamentCount = 0.55f;

                modifierPly = 0.1f;
                modifierVyh = 0.1f;
                modifierAre = 0.15f;
                modifierHyc = 0.2f;
                modifierZow = 0.05f;
                modifierBec = 0.05f;
                modifierNeo = 0.05f;
                modifierFok = 0.05f;
                break;
            case FA:
                modifierParliamentCount = 0.45f;

                modifierPly = 0.05f;
                modifierVyh = 0.25f;
                modifierAre = 0.15f;
                modifierHyc = 0.05f;
                modifierZow = 0.05f;
                modifierBec = 0.05f;
                modifierNeo = 0.05f;
                modifierFok = 0.25f;
                break;
            case ME:
                modifierParliamentCount = 0.35f;

                modifierPly = 0.1f;
                modifierVyh = 0.05f;
                modifierAre = 0.15f;
                modifierHyc = 0.15f;
                modifierZow = 0.1f;
                modifierBec = 0.15f;
                modifierNeo = 0.05f;
                modifierFok = 0.15f;
                break;
            case GE:
                modifierParliamentCount = 0.65f;

                modifierPly = 0.55f;
                modifierVyh = 0.05f;
                modifierAre = 0.05f;
                modifierHyc = 0.05f;
                modifierZow = 0.05f;
                modifierBec = 0.05f;
                modifierNeo = 0.05f;
                modifierFok = 0.05f;
                break;
        }

        switch (this.yourIdeology) {
            case PLYCISM:
                modifierPly += 0.05;
                break;
            case VYHUCISM:
                modifierVyh += 0.05;
                break;
            case AREWOSM:
                modifierAre += 0.05;
                break;
            case HYCROCISM:
                modifierHyc += 0.05;
                break;
            case ZOWOTISM:
                modifierZow += 0.05;
                break;
            case BECLYSM:
                modifierBec += 0.05;
                break;
            case NEOLEEISM:
                modifierNeo += 0.05;
                break;
            case FOKRITISM:
                modifierFok += 0.05;
                break;
        }

        this.parliamentCount = (int) (Math.ceil(500 * modifierParliamentCount));
        this.parliamentCountMax = this.parliamentCount;

        parties.put(Ideology.PLYCISM, (int) (parliamentCountMax * (modifierPly + (random.nextFloat() * (0.005 - (-0.0005)) + (-0.0005)))));
        this.parliamentCount -= parties.get(Ideology.PLYCISM);

        parties.put(Ideology.VYHUCISM, (int) (parliamentCountMax * (modifierVyh + (random.nextFloat() * (0.005 - (-0.0005)) + (-0.0005)))));
        this.parliamentCount -= parties.get(Ideology.VYHUCISM);

        parties.put(Ideology.AREWOSM, (int) (parliamentCountMax * (modifierAre + (random.nextFloat() * (0.005 - (-0.0005)) + (-0.0005)))));
        this.parliamentCount -= parties.get(Ideology.AREWOSM);

        parties.put(Ideology.HYCROCISM, (int) (parliamentCountMax * (modifierHyc + (random.nextFloat() * (0.005 - (-0.0005)) + (-0.0005)))));
        this.parliamentCount -= parties.get(Ideology.HYCROCISM);

        parties.put(Ideology.ZOWOTISM, (int) (parliamentCountMax * (modifierZow + (random.nextFloat() * (0.005 - (-0.0005)) + (-0.0005)))));
        this.parliamentCount -= parties.get(Ideology.ZOWOTISM);

        parties.put(Ideology.BECLYSM, (int) (parliamentCountMax * (modifierBec + (random.nextFloat() * (0.005 - (-0.0005)) + (-0.0005)))));
        this.parliamentCount -= parties.get(Ideology.BECLYSM);

        parties.put(Ideology.NEOLEEISM, (int) (parliamentCountMax * (modifierNeo + (random.nextFloat() * (0.005 - (-0.0005)) + (-0.0005)))));
        this.parliamentCount -= parties.get(Ideology.NEOLEEISM);

        parties.put(Ideology.FOKRITISM, (int) (parliamentCountMax * (modifierFok + (random.nextFloat() * (0.005 - (-0.0005)) + (-0.0005)))));
        this.parliamentCount -= parties.get(Ideology.FOKRITISM);

        parties.put(Ideology.NONPARTISAN, parliamentCount);

    }
}
