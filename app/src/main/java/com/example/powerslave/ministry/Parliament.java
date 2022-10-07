package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.government.Ideology;
import com.example.powerslave.person.Minister;
import com.example.powerslave.person.Ruler;

import java.util.HashMap;
import java.util.Random;

public class Parliament extends Ministry {

    private Ideology your_ideology;
    private int parliament_count;
    private int parliament_count_max;
    private HashMap<Ideology, Integer> parties = new HashMap<>();

    public Parliament(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);
        this.name = "Parliament of " + this.country.getName();

        this.your_ideology = this.country.getRuler().getIdeology();
        statsRandomizer();
    }

    @Override
    public String toString() {
        String string;
        string = "Your ideology: " + this.your_ideology + "\n";
        string += "Parliament size: " + this.parliament_count_max + "\n";
        string += "Party for Great Future (Plycism): " + parties.get(Ideology.Plycism) + "\n";
        string += "Party for everyone (Vyhucism): " + parties.get(Ideology.Vyhucism) + "\n";
        string += "Pious party (Arewosm): " + parties.get(Ideology.Arewosm) + "\n";
        string += "Equal party (Hycrocism): " + parties.get(Ideology.Hycrocism) + "\n";
        string += "Labour party (Zowotism): " + parties.get(Ideology.Zowotism) + "\n";
        string += "Prosperity party (Beclysm): " + parties.get(Ideology.Beclysm) + "\n";
        string += "Traditional party (Neo-leeism): " + parties.get(Ideology.Neoleeism) + "\n";
        string += "Progress party (Fokritism): " + parties.get(Ideology.Fokritism) + "\n";
        string += "Nonpartisan (No Ideology): " + parties.get(Ideology.Nonpartisan) + "\n";
        return string;
    }

    @Override
    public void updateMinistry() {
        super.updateMinistry();
        this.your_ideology = this.country.getRuler().getIdeology();
    }

    @Override
    public void statsRandomizer() {
        super.statsRandomizer();
        Random random = new Random();

        float modifier_parliament_count = 0f;

        float modifier_ply = 0f;
        float modifier_vyh = 0f;
        float modifier_are = 0f;
        float modifier_hyc = 0f;
        float modifier_zow = 0f;
        float modifier_bec = 0f;
        float modifier_neo = 0f;
        float modifier_fok = 0f;

        switch (this.country.getContinent()) {
            case EY:
                modifier_parliament_count = 0.75f;

                modifier_ply = 0.1f;
                modifier_vyh = 0.05f;
                modifier_are = 0.15f;
                modifier_hyc = 0.2f;
                modifier_zow = 0.2f;
                modifier_bec = 0.05f;
                modifier_neo = 0.05f;
                modifier_fok = 0.05f;
                break;
            case NY:
                modifier_parliament_count = 0.85f;

                modifier_ply = 0.1f;
                modifier_vyh = 0.05f;
                modifier_are = 0.1f;
                modifier_hyc = 0.05f;
                modifier_zow = 0.25f;
                modifier_bec = 0.25f;
                modifier_neo = 0.05f;
                modifier_fok = 0.05f;
                break;
            case SY:
                modifier_parliament_count = 0.65f;

                modifier_ply = 0.1f;
                modifier_vyh = 0.05f;
                modifier_are = 0.05f;
                modifier_hyc = 0.05f;
                modifier_zow = 0.3f;
                modifier_bec = 0.25f;
                modifier_neo = 0.05f;
                modifier_fok = 0.05f;
                break;
            case WY:
                modifier_parliament_count = 0.95f;

                modifier_ply = 0.1f;
                modifier_vyh = 0.05f;
                modifier_are = 0.15f;
                modifier_hyc = 0.05f;
                modifier_zow = 0.05f;
                modifier_bec = 0.3f;
                modifier_neo = 0.15f;
                modifier_fok = 0.05f;
                break;
            case IB:
                modifier_parliament_count = 0.55f;

                modifier_ply = 0.1f;
                modifier_vyh = 0.05f;
                modifier_are = 0.15f;
                modifier_hyc = 0.1f;
                modifier_zow = 0.15f;
                modifier_bec = 0.15f;
                modifier_neo = 0.15f;
                modifier_fok = 0.05f;
                break;
            case OB:
                modifier_parliament_count = 0.65f;

                modifier_ply = 0.1f;
                modifier_vyh = 0.4f;
                modifier_are = 0.05f;
                modifier_hyc = 0.15f;
                modifier_zow = 0.05f;
                modifier_bec = 0.05f;
                modifier_neo = 0.05f;
                modifier_fok = 0.05f;
                break;
            case CA:
                modifier_parliament_count = 0.55f;

                modifier_ply = 0.1f;
                modifier_vyh = 0.1f;
                modifier_are = 0.15f;
                modifier_hyc = 0.2f;
                modifier_zow = 0.05f;
                modifier_bec = 0.05f;
                modifier_neo = 0.05f;
                modifier_fok = 0.05f;
                break;
            case FA:
                modifier_parliament_count = 0.45f;

                modifier_ply = 0.05f;
                modifier_vyh = 0.3f;
                modifier_are = 0.15f;
                modifier_hyc = 0.05f;
                modifier_zow = 0.05f;
                modifier_bec = 0.05f;
                modifier_neo = 0.05f;
                modifier_fok = 0.25f;
                break;
            case ME:
                modifier_parliament_count = 0.35f;

                modifier_ply = 0.1f;
                modifier_vyh = 0.05f;
                modifier_are = 0.15f;
                modifier_hyc = 0.15f;
                modifier_zow = 0.1f;
                modifier_bec = 0.15f;
                modifier_neo = 0.05f;
                modifier_fok = 0.15f;
                break;
            case GE:
                modifier_parliament_count = 0.65f;

                modifier_ply = 0.55f;
                modifier_vyh = 0.05f;
                modifier_are = 0.05f;
                modifier_hyc = 0.05f;
                modifier_zow = 0.05f;
                modifier_bec = 0.05f;
                modifier_neo = 0.05f;
                modifier_fok = 0.05f;
                break;
        }

        switch (this.your_ideology) {
            case Plycism:
                modifier_ply += 0.05;
                break;
            case Vyhucism:
                modifier_vyh += 0.05;
                break;
            case Arewosm:
                modifier_are += 0.05;
                break;
            case Hycrocism:
                modifier_hyc += 0.05;
                break;
            case Zowotism:
                modifier_zow += 0.05;
                break;
            case Beclysm:
                modifier_bec += 0.05;
                break;
            case Neoleeism:
                modifier_neo += 0.05;
                break;
            case Fokritism:
                modifier_fok += 0.05;
                break;
        }

        this.parliament_count = (int) (Math.ceil(500 * modifier_parliament_count));
        this.parliament_count_max = this.parliament_count;

        parties.put(Ideology.Plycism, (int) (parliament_count_max * (modifier_ply + (random.nextFloat() * (0.005 - (-0.0005)) + (-0.0005)))));
        this.parliament_count -= parties.get(Ideology.Plycism);

        parties.put(Ideology.Vyhucism, (int) (parliament_count_max * (modifier_vyh + (random.nextFloat() * (0.005 - (-0.0005)) + (-0.0005)))));
        this.parliament_count -= parties.get(Ideology.Vyhucism);

        parties.put(Ideology.Arewosm, (int) (parliament_count_max * (modifier_are + (random.nextFloat() * (0.005 - (-0.0005)) + (-0.0005)))));
        this.parliament_count -= parties.get(Ideology.Arewosm);

        parties.put(Ideology.Hycrocism, (int) (parliament_count_max * (modifier_hyc + (random.nextFloat() * (0.005 - (-0.0005)) + (-0.0005)))));
        this.parliament_count -= parties.get(Ideology.Hycrocism);

        parties.put(Ideology.Zowotism, (int) (parliament_count_max * (modifier_zow + (random.nextFloat() * (0.005 - (-0.0005)) + (-0.0005)))));
        this.parliament_count -= parties.get(Ideology.Zowotism);

        parties.put(Ideology.Beclysm, (int) (parliament_count_max * (modifier_bec + (random.nextFloat() * (0.005 - (-0.0005)) + (-0.0005)))));
        this.parliament_count -= parties.get(Ideology.Beclysm);

        parties.put(Ideology.Neoleeism, (int) (parliament_count_max * (modifier_neo + (random.nextFloat() * (0.005 - (-0.0005)) + (-0.0005)))));
        this.parliament_count -= parties.get(Ideology.Neoleeism);

        parties.put(Ideology.Fokritism, (int) (parliament_count_max * (modifier_fok + (random.nextFloat() * (0.005 - (-0.0005)) + (-0.0005)))));
        this.parliament_count -= parties.get(Ideology.Fokritism);

        parties.put(Ideology.Nonpartisan, parliament_count);

    }
}
